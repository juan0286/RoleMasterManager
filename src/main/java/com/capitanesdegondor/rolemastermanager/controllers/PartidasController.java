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
public class PartidasController {

    @RequestMapping(value = "/m/{usuario}/{campaign}/partidas", method = {RequestMethod.GET})
    public ModelAndView getAllPartidas(
            @PathVariable("usuario") String usuario,
            @PathVariable("campaign") String campaign,
            ModelMap mmap, HttpSession session) {

        ModelAndView model = new ModelAndView();
        Master m = (Master) session.getAttribute("usuarioLogueado");
       
        if (m == null) {
            model = new ModelAndView("redirect:/login.do");
            return model;
        } 
        
        boolean existe = URLgetter.usuarioCampaignValido(session, usuario, campaign);

        if (!existe) {
            model = new ModelAndView("error");
            model.addObject("errorNewCampaign", "No existe esta campaña");
            return model;
        }
        
        
        //model.setViewName(URLgetter.getUrl(session) + "/solicitudes");
        model.setViewName("partidas");
        return model;
    }
    
    @RequestMapping(value = "/m/{usuario}/{campaign}/partida/{idpartida}", method = {RequestMethod.GET})
    public ModelAndView getPartida(
            @PathVariable("usuario") String usuario,
            @PathVariable("campaign") String campaign,
            ModelMap mmap, HttpSession session) {

        ModelAndView model = new ModelAndView();
        Master m = (Master) session.getAttribute("usuarioLogueado");
       
        if (m == null) {
            model = new ModelAndView("redirect:/login.do");
            return model;
        } 
        
        boolean existe = URLgetter.usuarioCampaignValido(session, usuario, campaign);

        if (!existe) {
            model = new ModelAndView("error");
            model.addObject("errorNewCampaign", "No existe esta campaña");
            return model;
        }
        
        model.setViewName("views/showPartida");
        return model;
    }
    
    @RequestMapping(value = "/m/{usuario}/{campaign}/crearPartida", method = {RequestMethod.GET})
    public ModelAndView formCrearPartida(
            @PathVariable("usuario") String usuario,
            @PathVariable("campaign") String campaign,
            ModelMap mmap, HttpSession session) {

        ModelAndView model = new ModelAndView();
        Master m = (Master) session.getAttribute("usuarioLogueado");
       
        if (m == null) {
            model = new ModelAndView("redirect:/login.do.do");
            return model;
        } 
        
        boolean existe = URLgetter.usuarioCampaignValido(session, usuario, campaign);

        if (!existe) {
            model = new ModelAndView("error");
            model.addObject("errorNewCampaign", "No existe esta campaña");
            return model;
        }
        long idPartida = 1l;
        model.setViewName("forms/crearPartida");
        return model;
    }
    
    @RequestMapping(value = "/m/{usuario}/{campaign}/crearPartida", method = {RequestMethod.POST})
    public ModelAndView crearPartida(
            @PathVariable("usuario") String usuario,
            @PathVariable("campaign") String campaign,
            ModelMap mmap, HttpSession session) {

        ModelAndView model = new ModelAndView();
        Master m = (Master) session.getAttribute("usuarioLogueado");
       
        if (m == null) {
            model = new ModelAndView("redirect:/login.do");
            return model;
        } 
        
        boolean existe = URLgetter.usuarioCampaignValido(session, usuario, campaign);

        if (!existe) {
            model = new ModelAndView("error");
            model.addObject("errorNewCampaign", "No existe esta campaña");
            return model;
        }
        long idPartida = 1l;
        model.setViewName("redirect:partida/" + idPartida);
        return model;
    }
    
    @RequestMapping(value = "/m/{usuario}/{campaign}/partida/{id_partida}/play", method = {RequestMethod.POST})
    public ModelAndView crearPartida(
            @PathVariable("usuario") String usuario,
            @PathVariable("campaign") String campaign,
            @PathVariable("id_partida") long idPartida,
            ModelMap mmap, HttpSession session) {

        ModelAndView model = new ModelAndView();
        Master m = (Master) session.getAttribute("usuarioLogueado");
       
        if (m == null) {
            model = new ModelAndView("redirect:/login.do");
            return model;
        } 
        
        boolean existe = URLgetter.usuarioCampaignValido(session, usuario, campaign);

        if (!existe) {
            model = new ModelAndView("error");
            model.addObject("errorNewCampaign", "No existe esta campaña");
            return model;
        }
        
        model.setViewName("partidaEnJuego");
        return model;
    }

    
}
