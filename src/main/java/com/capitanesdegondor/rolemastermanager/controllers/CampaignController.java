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
import java.util.Set;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
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
    
    @RequestMapping(value = "/salvarcampaign", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView validarLogin(
            @RequestParam("nombreCampaign") String nombreCampaign,
            @RequestParam("nombreMundo") String nombreMundo,
            @RequestParam("fechaMundo") String fechaMundo,
            HttpSession session){
        
        
        ModelAndView model = null;
        
        Master m = (Master) session.getAttribute("usuarioLogueado");
        
        HiberRespuesta hr = Validations.validarNuevaCampaign(nombreCampaign,nombreMundo,fechaMundo, m);
       
        if (!hr.isSuccess()){
            model = new ModelAndView("nuevaCampaign");
            model.addObject("errorNewCampaign",hr.getResponse());
            return model;
        }
        
        Campaign c = (Campaign) hr.getResult();
        
        //c.setMaster(m);
        Set<Campaign> l = m.getCampaigns();
        l.add(c);
        session.setAttribute("tieneCampas", true);
        session.setAttribute("campas", l);
        
        model = new ModelAndView("redirect:/home");
        return model;
    }
    
    @RequestMapping(value = "/verCampaign", method = {RequestMethod.GET})
    public ModelAndView verCampàign(
            @RequestParam("id") String id,            
            HttpSession session){
        
        
        ModelAndView model = new ModelAndView("campaign");
        
        HiberRespuesta hr = Validations.validarVerCampaign(id);
        
        if (!hr.isSuccess()){
            //model = new ModelAndView("home");
            model.addObject("errorNewCampaign",hr.getResponse());
            return model;
        }
        
        Campaign c = (Campaign) hr.getResult();
        Jugador j = c.getJugadores().iterator().next();
        Personaje pj = j.getPj();
        pj.getHabilidades().getResistencias().getEnfermedades();
        Hab_secundaria_desarrollo hab_secundaria_desarrollo = pj.getHabilidades().getHabSecundarias().get(2);
       
        session.setAttribute("campaActual", c);
        return model;
    }
        
}
