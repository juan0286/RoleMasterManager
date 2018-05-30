/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capitanesdegondor.rolemastermanager.validations;

import com.capitanesdegondor.rolemastermanager.service.beans.NuevoUsuarioJugador;
import com.websystique.spring.HibernateDao;
import com.websystique.spring.model.bono.BonoExp;
import com.websystique.spring.model.Campaign;
import com.websystique.spring.model.Jugador;
import com.websystique.spring.model.Master;
import com.websystique.spring.model.Mundo;
import com.websystique.spring.model.Personaje;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author TiranoJuan
 */
public class Validations {

    public static HiberRespuesta validarUsuarioMaster(String user, String pass) {

        HiberRespuesta hr = new HiberRespuesta();
        String vacio = "Escriba un nombre de usuario y contraseña.";
        String incorrecto = "El usuario y/o la contraseña introducida no es correcta.";

        if (user.trim().equals("") || pass.trim().equals("")) {
            hr.setResponse(vacio);
            hr.setSuccess(false);
            return hr;
        }

        Master m = HibernateDao.obtenerMasterPorUsuario(user);
        if (m == null) {
            hr.setSuccess(false);
            hr.setResponse(incorrecto);
            return hr;
        }

        if (!m.getPassword().equals(pass)) {
            hr.setSuccess(false);
            hr.setResponse(incorrecto);
            return hr;
        }

        hr.setSuccess(true);
        hr.setResponse("Success");
        hr.setResult(m);
        return hr;
    }

    public static HiberRespuesta validarVerCampaign(long id) {
       // long id = Long.parseLong(idCampaign, 10);

        HiberRespuesta hr = new HiberRespuesta();
        String vacio = "Esta Campaña actualmente no existe.";

        Campaign c = HibernateDao.obtenerCampaignPorId(id);

        if (c == null) {
            hr.setSuccess(false);
            hr.setResponse(vacio);
            return hr;
        }
        hr.setResult(c);
        hr.setSuccess(true);
        return hr;
    }

    public static HiberRespuesta validarNuevaCampaign(String nombreCampaign, String nombreMundo, String fechaMundo, Master m) {

        HiberRespuesta hr = new HiberRespuesta();
        String vacio = "Llene todos los campos para crear una nueva Campaña.";
        String nombreInvalido_c = "El nombre para esta campaña es invalido";
        String nombreInvalido_m = "El nombre para estè Mundo es invalido";
        String nombreRepetido_c = "Ya existe una campaña con este nombre";
        String errorGuardado = "Error guardando la Campaña";
        String formatoFecha = "Formato de fecha incorrecto";

        // Validar campos vacios
        if (nombreCampaign.trim().equals("") || nombreMundo.trim().equals("") || fechaMundo.trim().equals("")) {
            hr.setResponse(vacio);
            hr.setSuccess(false);
            return hr;
        }

        // Validar que no exista campaña con este nombre
        Campaign c = HibernateDao.obtenerCampaignPorNombre(nombreCampaign);
        if (c != null) {
            hr.setSuccess(false);
            hr.setResponse(nombreRepetido_c);
            return hr;
        }

        // validar que la fecha sea formatee correctamente
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {

            date = formatter.parse(fechaMundo);
            System.out.println(date);
            System.out.println(formatter.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
            hr.setSuccess(false);
            hr.setResponse(formatoFecha);
            return hr;
        }

        Mundo mundo = new Mundo();
        mundo.setNombre(nombreMundo);
        mundo.setFecha_rol(date);

        Campaign cNew = new Campaign();
        cNew.setNombre(nombreCampaign);
        cNew.setMundo(mundo);

        if (!HibernateDao.crearCampaignYAddAlMaster(cNew, m)) {
            hr.setSuccess(false);
            hr.setResponse(errorGuardado);
            return hr;
        }
        hr.setSuccess(true);
        hr.setResult(cNew);
        return hr;
    }

    public static HiberRespuesta crearNuevoJugador(NuevoUsuarioJugador nuj) {
        HiberRespuesta hr = new HiberRespuesta();
        String sinID = "no tiene Id";
        String sinEmail = "no tiene Email";

        // Validar Id de firebase
        if (nuj.getId_firebase().trim().equals("")) {
            hr.setResponse(sinID);
            hr.setSuccess(false);
            return hr;
        }

        // Validar Email
        if (nuj.getEmail().trim().equals("")) {
            hr.setResponse(sinEmail);
            hr.setSuccess(false);
            return hr;
        }

        //Personaje pj = HibernateDao.obtenerPersonajePorId(Long.parseLong(idpj));
        if (nuj == null) {
            hr.setResponse("");
            hr.setSuccess(false);
            return hr;
        }
        
        // creo jugador
        
        Jugador j = new Jugador();
        j.setNombre_usuario(nuj.getEmail());
        j.setId_firebase(nuj.getId_firebase());
        j.setPassword("pass123");
        
        Date d = new Date();
        j.setCumple(d);
        if (nuj.getName().trim().length() > 0) {
            j.setNombre(nuj.getName());
        }
                
        if (nuj.getName().trim().length() > 0) {
            j.setPerfil(Jugador.PERFIL_JUGADOR);
        }

        boolean succ = HibernateDao.crearJugador(j);
        if (!succ) {
            hr.setResponse("Fallo guardado del Jugador");
        }
        hr.setResult(j);
        hr.setSuccess(succ);
        return hr;

    }

    public static HiberRespuesta validarBonoExperienca(Integer bono, String motivo, Personaje pj) {

        HiberRespuesta hr = new HiberRespuesta();
        String vacio = "Llene todos los campos para crear una nueva Campaña.";
        String bonomal = "El nùmero del bono es invalido";
        String pjmal = "El pesonaje no puede recibir experiencia o no existe";

        // Validar campos vacios
        if (motivo.trim().equals("") || bono == null) {
            hr.setResponse(vacio);
            hr.setSuccess(false);
            return hr;
        }

        //Personaje pj = HibernateDao.obtenerPersonajePorId(Long.parseLong(idpj));
        if (pj == null) {
            hr.setResponse(pjmal);
            hr.setSuccess(false);
            return hr;
        }

        BonoExp be = new BonoExp();
        be.setAplicado(false);
        be.setBono(bono);
        be.setFecha(new Date());
        be.setMotivo(motivo);
        be.setPj(pj);

        long succ = HibernateDao.crearBonoExp(be);
        if (succ!=0l) {
            hr.setResponse("Fallo guardado el Bono");
        }
        hr.setSuccess(succ!=0l);
        return hr;
    }

    public class BonoExpFormValidator implements Validator {

        @Override
        public boolean supports(Class<?> type) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void validate(Object o, Errors errors) {
            ValidationUtils.rejectIfEmptyOrWhitespace(
                    errors, "bono", "mal el bono");
            ValidationUtils.rejectIfEmptyOrWhitespace(
                    errors, "motivo", "mal el motivo");
        }
    }
}
