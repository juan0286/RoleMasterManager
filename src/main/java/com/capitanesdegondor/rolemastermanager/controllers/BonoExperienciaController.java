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
import com.websystique.spring.model.BonoExp;
import com.websystique.spring.model.Campaign;
import com.websystique.spring.model.Master;
import com.websystique.spring.model.Personaje;
import java.util.Set;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author TiranoJuan
 */
@Controller
@RequestMapping("/bonoExp.do")
public class BonoExperienciaController {

    @RequestMapping(method = {RequestMethod.GET})
    public String personsajeABonificar(
            @RequestParam("pj") long pj,
            Model model) {
        Personaje p = HibernateDao.obtenerPersonajePorId(pj);
        Set<BonoExp> bes = HibernateDao.todosLosBonosExpDeUnPj(p);

        model.addAttribute("bes", bes);
        model.addAttribute("pj", pj);
        model.addAttribute("bonoExpForm", new BonoExpForm());
        //model.addAttribute("pexp",pj);
        return "darExp";
    }

    @RequestMapping(method = {RequestMethod.POST})
    public String validarBonoExp(
            @Valid @ModelAttribute("bonoExpForm") BonoExpForm bonoExpForm,
            BindingResult result,
            Model model,
            HttpSession session) {

//        long pj = Long.parseLong((String) model.asMap().get("pj"));
        Personaje p = HibernateDao.obtenerPersonajePorId(1L);

        if (result.hasErrors()) {
            Set<BonoExp> bes = HibernateDao.todosLosBonosExpDeUnPj(p);
            model.addAttribute("bes", bes);
            return "darExp";
        }

        HiberRespuesta hr = Validations.validarBonoExperienca(bonoExpForm.getBono(), bonoExpForm.getMotivo(), p);

        if (!hr.isSuccess()) {
            Set<BonoExp> bes = HibernateDao.todosLosBonosExpDeUnPj(p);
            model.addAttribute("bes", bes);
            model.addAttribute("expError", hr.getResponse());
            return "darExp";
        }
        Set<BonoExp> bes = HibernateDao.todosLosBonosExpDeUnPj(p);
        model.addAttribute("bes", bes);
        Campaign c = (Campaign) session.getAttribute("campaActual");

        return "darExp";

    }
}
