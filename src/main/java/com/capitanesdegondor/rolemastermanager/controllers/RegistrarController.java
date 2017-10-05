/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capitanesdegondor.rolemastermanager.controllers;

import com.websystique.spring.HibernateDao;
import com.websystique.spring.model.Master;
import org.springframework.stereotype.Controller;
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
public class RegistrarController {

    @RequestMapping(value = "/registrar", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView registrarMaster(
            @RequestParam("username") String usuario,
            @RequestParam("nombre") String nombre,
            @RequestParam("password") String pass1,
            @RequestParam("passwordDos") String pass2,
            @RequestParam("email") String email) {
        ModelAndView model = null;

        String vacio = "Hay campos sin rellenar.";
        String incorrecto = "Las contraseñas deben coincidir.";

        if (usuario.trim().equals("")
                || nombre.trim().equals("")
                || pass1.trim().equals("")
                || pass2.trim().equals("")
                || email.trim().equals("")) {
            model = new ModelAndView("login");
            model.addObject("errorDetail", vacio);
            //.addAttribute("errorDetail", vacio);
            model.setViewName("errorLogin");
            return model;
        }

        Master m = new Master();
        m.setNombre(nombre);
        m.setUsuario(usuario);
        m.setPassword(pass1);

        HibernateDao.crearMaster(m);
        model = new ModelAndView("home");
        model.addObject("usuarioLogueado", m);
        return model;
    }
}
