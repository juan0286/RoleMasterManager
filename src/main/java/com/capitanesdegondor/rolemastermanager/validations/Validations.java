/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capitanesdegondor.rolemastermanager.validations;

import com.websystique.spring.HibernateDao;
import com.websystique.spring.model.BonoExp;
import com.websystique.spring.model.Campaign;
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
        String vacio = "Escriba un nombre de usuario y contrase�a.";
        String incorrecto = "El usuario y/o la contrase�a introducida no es correcta.";

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

    public static HiberRespuesta validarVerCampaign(String idCampaign) {
        long id = Long.parseLong(idCampaign, 10);
        
        HiberRespuesta hr = new HiberRespuesta();
        String vacio = "Esta Campa�a actualmente no existe.";        
        
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
        String vacio = "Llene todos los campos para crear una nueva Campa�a.";
        String nombreInvalido_c = "El nombre para esta campa�a es invalido";
        String nombreInvalido_m = "El nombre para est� Mundo es invalido";
        String nombreRepetido_c = "Ya existe una campa�a con este nombre";
        String errorGuardado = "Error guardando la Campa�a";
        String formatoFecha = "Formato de fecha incorrecto";

        // Validar campos vacios
        if (nombreCampaign.trim().equals("") || nombreMundo.trim().equals("") || fechaMundo.trim().equals("")) {
            hr.setResponse(vacio);
            hr.setSuccess(false);
            return hr;
        }

        // Validar que no exista campa�a con este nombre
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

        if (!HibernateDao.CrearCampaignYAddAlMaster(cNew,m)) {
            hr.setSuccess(false);
            hr.setResponse(errorGuardado);            
            return hr;
        }
        hr.setSuccess(true);
        hr.setResult(cNew);
        return hr;
    }

    public static HiberRespuesta validarBonoExperienca(Integer bono, String motivo, Personaje pj) {
     
        
        HiberRespuesta hr = new HiberRespuesta();
        String vacio = "Llene todos los campos para crear una nueva Campa�a.";
        String bonomal = "El n�mero del bono es invalido";        
        String pjmal = "El pesonaje no puede recibir experiencia o no existe";        

        // Validar campos vacios
        if (motivo.trim().equals("") || bono == null) {
            hr.setResponse(vacio);
            hr.setSuccess(false);
            return hr;
        }
        
        
       //Personaje pj = HibernateDao.obtenerPersonajePorId(Long.parseLong(idpj));
        
        if (pj == null){
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
        
        boolean succ = HibernateDao.crearBonoExp(be);
        if (!succ){
            hr.setResponse("Fallo guardado el Bono");
        }
        hr.setSuccess(succ);
        return hr;
    }
    
    public class BonoExpFormValidator implements Validator{	

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