/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capitanesdegondor.rolemastermanager.controllers;

import com.capitanesdegondor.rolemastermanager.validations.HiberRespuesta;
import com.capitanesdegondor.rolemastermanager.validations.Validations;
import com.websystique.spring.model.Campaign;
import com.websystique.spring.model.Jugador;
import com.websystique.spring.model.Master;
import com.websystique.spring.model.Personaje;
import com.websystique.spring.model.caractPj.Hab_secundaria_desarrollo;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author TiranoJuan
 */
@Controller
public class CampaignController {

    @RequestMapping(value = "/m/{usuario}/salvarcampaign.do", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView salvarcampaign(
            @RequestParam("nombreCampaign") String nombreCampaign,
            @RequestParam("nombreMundo") String nombreMundo,
            @RequestParam("fechaMundo") String fechaMundo,
            HttpSession session) {

        ModelAndView model = null;
               
        Master m = (Master) session.getAttribute("usuarioLogueado");
       
        if (m == null) {
            model = new ModelAndView("redirect:/login");
            return model;
        }
        HiberRespuesta hr = Validations.validarNuevaCampaign(nombreCampaign, nombreMundo, fechaMundo, m);

        if (!hr.isSuccess()) {
            model = new ModelAndView(URLgetter.getUrl(session));
            model.addObject("errorNewCampaign", hr.getResponse());
            return model;
        }

        Campaign c = (Campaign) hr.getResult();

        //c.setMaster(m);
        Set<Campaign> l = m.getCampaigns();
        l.add(c);
        session.setAttribute("tieneCampas", true);
        session.setAttribute("campas", l);

        model = new ModelAndView("redirect:/m/" + m.getUsuario());
        return model;
    }

    @RequestMapping(value = "/m/{usuario}/{campaign}", method = {RequestMethod.GET})
    public ModelAndView verCampaign(
            @PathVariable("campaign") String campaign, 
            @PathVariable("usuario") String usuario,
            HttpSession session, ModelMap mmap) {

        ModelAndView model = null;        
        Master m = (Master) session.getAttribute("usuarioLogueado");
        if (m == null) {
            model = new ModelAndView("redirect:/login.do");
            return model;
        }
        boolean existe = URLgetter.usuarioValido(session, usuario);
        try {
            campaign = URLEncoder.encode(campaign, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CampaignController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Master usuarioLogueado = (Master) session.getAttribute("usuarioLogueado");        
        HashMap<String,Long> campas = (HashMap<String,Long>) session.getAttribute("idCampas");
        
        if (!existe || !campas.containsKey(campaign)) {
            model = new ModelAndView("error");
            model.addObject("errorNewCampaign", "No existe esta campaña");
            return model;
        }
        
        HiberRespuesta hr = Validations.validarVerCampaign(campas.get(campaign));

        if (!hr.isSuccess()) {
            model = new ModelAndView("error");
            model.addObject("errorNewCampaign", hr.getResponse());
            return model;
        }

        Campaign c = (Campaign) hr.getResult();

        session.setAttribute("campaActual", c);        

        model = new ModelAndView("campaign");
        return model;
    }

}
