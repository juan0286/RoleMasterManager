/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capitanesdegondor.rolemastermanager.controllers;

import com.capitanesdegondor.rolemastermanager.modelos.BonoExpForm;
import com.capitanesdegondor.rolemastermanager.validations.HiberRespuesta;
import com.capitanesdegondor.rolemastermanager.validations.Validations;
import com.websystique.spring.HibernateDao;
import com.websystique.spring.model.bono.BonoExp;
import com.websystique.spring.model.Campaign;
import com.websystique.spring.model.Master;
import com.websystique.spring.model.Personaje;
import java.util.Set;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
//@RequestMapping("/m/bonoExp.do")
public class BonoExperienciaController {

    @RequestMapping(value = "/m/{usuario}/{campaign}/bonoExp/{pj}", method = {RequestMethod.GET})
    public ModelAndView personsajeABonificar(            
            @PathVariable("campaign") String campaign, 
            @PathVariable("usuario") String usuario,
            @PathVariable("pj") Long idpj,
            HttpSession session) {
        
        ModelAndView model = new ModelAndView();
        Personaje p = HibernateDao.obtenerPersonajePorId(idpj);
        Set<BonoExp> bes = HibernateDao.todosLosBonosExpDeUnPj(p);

        model.addObject("bes", bes);
        model.addObject("pj", p);
        model.addObject("bonoExpForm", new BonoExpForm());
        //model.addAttribute("pexp",pj);
        //return URLgetter.getUrl(session) + "/bonosExp";
        //return "darExp";
        model.setViewName("forms/ngCrearBonoExp");
        return model;
    }

    @RequestMapping(value = "/m/{usuario}/{campaign}/bonoExp/{pj}",method = {RequestMethod.POST})
    public String validarBonoExp(
            @Valid @ModelAttribute("bonoExpForm") BonoExpForm bonoExpForm,
            @PathVariable("campaign") String campaign, 
            @PathVariable("usuario") String usuario,
            @PathVariable("pj") Long idpj,
            BindingResult result,
            Model model,
            HttpSession session) {

//        long pj = Long.parseLong((String) model.asMap().get("pj"));
        Personaje p = HibernateDao.obtenerPersonajePorId(idpj);

        if (result.hasErrors()) {
            Set<BonoExp> bes = HibernateDao.todosLosBonosExpDeUnPj(p);
            model.addAttribute("bes", bes);
            return URLgetter.getUrl(session) + "/bonosExp";
        }

        HiberRespuesta hr = Validations.validarBonoExperienca(bonoExpForm.getBono(), bonoExpForm.getMotivo(), p);

        if (!hr.isSuccess()) {
            Set<BonoExp> bes = HibernateDao.todosLosBonosExpDeUnPj(p);
            model.addAttribute("bes", bes);
            model.addAttribute("expError", hr.getResponse());
            return URLgetter.getUrl(session) + "/bonosExp";
        }
        Set<BonoExp> bes = HibernateDao.todosLosBonosExpDeUnPj(p);
        model.addAttribute("bes", bes);
        Campaign c = (Campaign) session.getAttribute("campaActual");

        return URLgetter.getUrl(session) + "/bonosExp";

    }
    
}
