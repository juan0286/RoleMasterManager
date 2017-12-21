/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capitanesdegondor.rolemastermanager.controllers;

import com.capitanesdegondor.rolemastermanager.modelos.BonoExpForm;
import com.capitanesdegondor.rolemastermanager.service.beans.SolicitudDTO;
import com.capitanesdegondor.rolemastermanager.validations.HiberRespuesta;
import com.capitanesdegondor.rolemastermanager.validations.Validations;
import com.websystique.spring.HibernateDao;
import com.websystique.spring.model.bono.BonoExp;
import com.websystique.spring.model.Campaign;
import com.websystique.spring.model.CampaignAccessRequest;
import com.websystique.spring.model.Master;
import com.websystique.spring.model.Personaje;
import java.util.HashMap;
import java.util.Set;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author TiranoJuan
 */
@SessionAttributes("usuarioLogueado")
@Controller
public class SolicitudesController {

    @RequestMapping(value = "/m/{usuario}/{campaign}/solicitudes", method = {RequestMethod.GET})
    public ModelAndView solicitudes(
            @PathVariable("usuario") String usuario,
            @PathVariable("campaign") String campaign,
            ModelMap mmap, HttpSession session) {

        ModelAndView model = new ModelAndView();
        boolean existe = URLgetter.usuarioCampaignValido(session, usuario, campaign);

        if (!existe) {
            model = new ModelAndView("error");
            model.addObject("errorNewCampaign", "No existe esta campaña");
            return model;
        }
        HashMap<String, Long> campas = (HashMap<String, Long>) session.getAttribute("idCampas");
        Campaign c = HibernateDao.obtenerCampaignPorId(campas.get(campaign));
        Set<CampaignAccessRequest> cars = HibernateDao.todosLosCarDeUnaCampaign(c);

        model.addObject("cars", cars);
        if (cars.size() > 0) {
            CampaignAccessRequest car = (CampaignAccessRequest) cars.toArray()[0];
            if (car.getStatus() == CampaignAccessRequest.estado.ESPERA) {
                System.out.println("");
            }
        }
        //model.setViewName(URLgetter.getUrl(session) + "/solicitudes");
        model.setViewName("solicitudes");
        return model;
    }

    @RequestMapping(value = "/solicitar", method = {RequestMethod.POST})
    public @ResponseBody
    ResponseEntity solicitudes_aceptar(
            @RequestBody SolicitudDTO s, HttpSession session
    ) {

        Master user = (Master) session.getAttribute("usuarioLogueado");
        long id_car = s.getId_car();
        String accion = s.getAccion();
        JSONObject json = new JSONObject();

        try {
            if (accion.equals("aceptar")) {
                HibernateDao.aceptarCar(id_car);
                json.put("statusRequest", CampaignAccessRequest.estado.ACEPTADA);
            } else if (accion.equals("cancelar")) {
                HibernateDao.rechazarCar(id_car);
                json.put("statusRequest", CampaignAccessRequest.estado.RECHAZADA);
            } else if (accion.equals("esperar")) {
                HibernateDao.esperarCar(id_car);
                json.put("statusRequest", CampaignAccessRequest.estado.ESPERA);
            }

        } catch (Exception ex) {
            System.out.println("error" + ex.toString());
            json.put("statusRequest", CampaignAccessRequest.estado.ESPERA);
            return new ResponseEntity(json, HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity(json, HttpStatus.CREATED);
    }

}
