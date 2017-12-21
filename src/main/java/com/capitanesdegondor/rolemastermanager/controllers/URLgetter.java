/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capitanesdegondor.rolemastermanager.controllers;

import com.websystique.spring.model.Campaign;
import com.websystique.spring.model.Master;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TiranoJuan
 */
public class URLgetter {

    public static String getUrl(HttpSession session) {
        Master m = (Master) session.getAttribute("usuarioLogueado");
        Campaign c = (Campaign) session.getAttribute("campaActual");
        java.lang.String re = "redirect:/m/";
        try {
            re += URLEncoder.encode(m.getUsuario().replace(" ", "_"), "UTF-8");

            if (c != null) {
                re += "/" + URLEncoder.encode(c.getNombre().replace(" ", "_"), "UTF-8");;
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CampaignController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return re;
    }

    public static boolean usuarioValido(HttpSession session,String usuario) {
        boolean resp = true;
        Master m = (Master) session.getAttribute("usuarioLogueado");
        if (m == null){
            return false;
        }
        return m.getUsuario().equals(usuario);
    }
    
    public static boolean usuarioCampaignValido(HttpSession session,String usuario, String campaign) {
        boolean resp = usuarioValido(session, usuario);
        HashMap<String,Long> campas = (HashMap<String,Long>) session.getAttribute("idCampas");
        if (campas == null){
            return false;
        }
        return resp || campas.containsKey(campaign);
    }
}
