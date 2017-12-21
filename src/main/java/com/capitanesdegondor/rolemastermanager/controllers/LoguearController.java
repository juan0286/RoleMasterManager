/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capitanesdegondor.rolemastermanager.controllers;

import com.capitanesdegondor.rolemastermanager.validations.HiberRespuesta;
import com.capitanesdegondor.rolemastermanager.validations.Validations;
import com.websystique.spring.HibernateDao;
import com.websystique.spring.model.Campaign;
import com.websystique.spring.model.Jugador;
import com.websystique.spring.model.Master;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author TiranoJuan
 */
@SessionAttributes("usuarioLogueado")
@Controller
public class LoguearController {

    @RequestMapping(value = "/ingresar", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView validarLogin(
            @RequestParam("username") String usuario,
            @RequestParam("password") String password,
            HttpSession session) {

//        appContext =  MyContext.getApplicationContext();
        ModelAndView model = null;

        HiberRespuesta hr = Validations.validarUsuarioMaster(usuario, password);
        if (!hr.isSuccess()) {
            model = new ModelAndView("login");
            model.addObject("errorDetail", hr.getResponse());

            return model;
        }

        Master m = (Master) hr.getResult();
        HashMap<String,Long> campas = new HashMap<>();
        for(Campaign c : m.getCampaigns()){
            campas.put(c.getNombreURL(), c.getId_campaign());            
        }
        session.setAttribute("idCampas", campas);
        session.setAttribute("usuarioLogueado", m);
        model = new ModelAndView("redirect:/m/" + m.getUsuario());        
        
        return model;
    }

    @RequestMapping(value = "/desloguear", method = {RequestMethod.GET, RequestMethod.POST})
    public String desloguear(            
            HttpSession session) {
        session.setAttribute("usuarioLogueado", null);
        return "login";
    }
    
    
//    @RequestMapping(value = "/ingresar", method = {RequestMethod.GET, RequestMethod.POST})
//    public String validarLogin(
//            @RequestParam("username") String usuario,
//            @RequestParam("password") String password,
//            ModelAndView model,
//            HttpSession session) {
//
////        appContext =  MyContext.getApplicationContext();
//        // = null;
//
//        HiberRespuesta hr = Validations.validarUsuarioMaster(usuario, password);
//        if (!hr.isSuccess()) {
//            model = new ModelAndView("login");
//            model.addObject("errorDetail", hr.getResponse());
//            
//            return "login";
//        }
//
//        Master m = (Master) hr.getResult();
//        model = new ModelAndView("home");
//        model.setViewName("home");
//        session.setAttribute("usuarioLogueado", m); 
//        return "casa";
//    }
}
