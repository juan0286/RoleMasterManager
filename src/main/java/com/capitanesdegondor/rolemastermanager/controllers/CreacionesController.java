/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capitanesdegondor.rolemastermanager.controllers;

import com.capitanesdegondor.rolemastermanager.modelos.BonoExpForm;
import com.capitanesdegondor.rolemastermanager.modelos.ObjetoTipoForm;
import com.capitanesdegondor.rolemastermanager.modelos.PremioPreguntaForm;
import com.capitanesdegondor.rolemastermanager.service.beans.SolicitudDTO;
import com.capitanesdegondor.rolemastermanager.validations.HiberRespuesta;
import com.capitanesdegondor.rolemastermanager.validations.Validations;
import com.websystique.spring.HibernateDao;
import com.websystique.spring.model.bono.BonoExp;
import com.websystique.spring.model.Campaign;
import com.websystique.spring.model.CampaignAccessRequest;
import com.websystique.spring.model.Master;
import com.websystique.spring.model.Personaje;
import com.websystique.spring.model.messaging.OpcionPremioPregunta;
import com.websystique.spring.model.messaging.PremioPregunta;
import com.websystique.spring.model.objetos.TipoObjeto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author TiranoJuan
 */
@SessionAttributes("usuarioLogueado")
@Controller
public class CreacionesController {

    @RequestMapping(value = "/m/{usuario}/{campaign}/crear", method = {RequestMethod.GET})
    public ModelAndView creadores(
            @PathVariable("usuario") String usuario,
            @PathVariable("campaign") String campaign,
            ModelMap mmap, HttpSession session) {

        ModelAndView model = new ModelAndView();
        boolean existe = URLgetter.usuarioCampaignValido(session, usuario, campaign);

        if (!existe) {
            model = new ModelAndView("error");
            model.addObject("errorNewCampaign", "No existe esta campaña");
            return model;
        }

        model.setViewName("crear");
        return model;
    }

    @RequestMapping(value = "/m/{usuario}/{campaign}/nuevoPersonaje", method = {RequestMethod.GET})
    public ModelAndView crearPersonaje(
            @PathVariable("usuario") String usuario,
            @PathVariable("campaign") String campaign,
            ModelMap mmap, HttpSession session) {

        ModelAndView model = new ModelAndView();
        boolean existe = URLgetter.usuarioCampaignValido(session, usuario, campaign);

        if (!existe) {
            model = new ModelAndView("error");
            model.addObject("errorNewCampaign", "No existe esta campaña");
            return model;
        }

        model.setViewName("crearPj");
        return model;
    }

    @RequestMapping(value = "/m/{usuario}/{campaign}/nuevoObjetoTipo", method = {RequestMethod.GET})
    public ModelAndView crearObjetoTipo(
            @PathVariable("usuario") String usuario,
            @PathVariable("campaign") String campaign,
            HttpSession session) {

        ModelAndView model = new ModelAndView();
        boolean existe = URLgetter.usuarioCampaignValido(session, usuario, campaign);

        if (!existe) {
            model = new ModelAndView("error");
            model.addObject("errorNewCampaign", "No existe esta campaña");
            return model;
        }
        model.addObject("ObjetoTipoForm", new ObjetoTipoForm());
        model.setViewName("forms/crearObjetoTipo");
        return model;
    }

    @RequestMapping(value = "/m/{usuario}/{campaign}/nuevoObjetoTipo", method = {RequestMethod.POST})
    public ModelAndView crearObjetoTipoAccion(
            @PathVariable("usuario") String usuario,
            @PathVariable("campaign") String campaign,
            @Valid @ModelAttribute("objetoTipoForm") ObjetoTipoForm otf,
            BindingResult result,
            HttpSession session) {

        ModelAndView model = new ModelAndView();
        boolean existe = URLgetter.usuarioCampaignValido(session, usuario, campaign);

        if (!existe) {
            model = new ModelAndView("error");
            model.addObject("errorNewCampaign", "No existe esta campaña");
            return model;
        }

        // Crear y Validar
        TipoObjeto to = new TipoObjeto();
        to.setDegradado(otf.getDegradado());
        to.setDurabilidad_stnd(otf.getDurabilidad_stnd());
        to.setNombre(otf.getNombre());
        to.setPeso_stnd(otf.getPeso_stnd());
        to.setTam_stnd(otf.getTam_stnd());

        try {
            HibernateDao.crearTipoDeObjeto(to);
        } catch (Exception e) {

        }
        model.addObject("to", to);
        model.setViewName("redirect:verObjetoTipo/" + to.getId_tipoObjeto());
        return model;
    }

    @RequestMapping(value = "/m/{usuario}/{campaign}/verObjetoTipo/{idto}", method = {RequestMethod.GET})
    public ModelAndView showObjetoTipoAccion(
            @PathVariable("usuario") String usuario,
            @PathVariable("campaign") String campaign,
            @PathVariable("idto") long idto,
            @Valid @ModelAttribute("objetoTipoForm") ObjetoTipoForm otf,
            BindingResult result,
            HttpSession session) {

        ModelAndView model = new ModelAndView();
        boolean existe = URLgetter.usuarioCampaignValido(session, usuario, campaign);

        if (!existe) {
            model = new ModelAndView("error");
            model.addObject("errorNewCampaign", "No existe esta campaña");
            return model;
        }

        // Crear y Validar
        TipoObjeto to = null;
        try {
            to = HibernateDao.obtenerTIpoObjetoPorId(idto);
        } catch (Exception e) {

        }

        model.addObject("to", to);
        model.setViewName("views/showObjetoTipo");
        return model;
    }

    @RequestMapping(value = "/m/{usuario}/{campaign}/nuevoObjeto", method = {RequestMethod.GET})
    public ModelAndView crearObjeto(
            @PathVariable("usuario") String usuario,
            @PathVariable("campaign") String campaign,
            ModelMap mmap, HttpSession session) {

        ModelAndView model = new ModelAndView();
        boolean existe = URLgetter.usuarioCampaignValido(session, usuario, campaign);

        if (!existe) {
            model = new ModelAndView("error");
            model.addObject("errorNewCampaign", "No existe esta campaña");
            return model;
        }

        model.setViewName("crearObjeto");
        return model;
    }

    @RequestMapping(value = "/m/{usuario}/{campaign}/nuevoPremioPregunta", method = {RequestMethod.GET})
    public ModelAndView crearPremioPregunta(
            @PathVariable("usuario") String usuario,
            @PathVariable("campaign") String campaign,
            ModelMap mmap, HttpSession session) {

        ModelAndView model = new ModelAndView();
        boolean existe = URLgetter.usuarioCampaignValido(session, usuario, campaign);

        if (!existe) {
            model = new ModelAndView("error");
            model.addObject("errorNewCampaign", "No existe esta campaña");
            return model;
        }
        PremioPreguntaForm ppm = new PremioPreguntaForm();
        List<OpcionPremioPregunta> lopp = new ArrayList<>();
        OpcionPremioPregunta opp = new OpcionPremioPregunta();
        opp.setCorrecta(false);
        opp.setSentencia("");
        lopp.add(opp);
        ppm.setOpciones(lopp);
        HashMap<String,Long> campas = (HashMap<String,Long>) session.getAttribute("idCampas");
        
        if (!campas.containsKey(campaign)){
            model.addObject("premioPreguntaForm", new PremioPreguntaForm());
            model.addObject("error", "no existe esta campaña");
        }
        long idCampaActual = campas.get(campaign);
        Set<Personaje> pjs = HibernateDao.todosLosPjsDeUnaCampaign(idCampaActual);
        List<Personaje>  listPjs = new ArrayList<Personaje>(pjs);
        ppm.setPersonajes( listPjs);
        model.addObject("premioPreguntaForm", ppm);

        model.setViewName("forms/crearPremioPregunta");
        return model;
    }

    @RequestMapping(value = "/m/{usuario}/{campaign}/nuevoPremioPregunta", method = {RequestMethod.POST})
    public ModelAndView salvarPremioPregunta(
            @PathVariable("usuario") String usuario,
            @PathVariable("campaign") String campaign,            
            BindingResult result,
            PremioPreguntaForm ppf,
            ModelMap mmap, HttpSession session) {

        ModelAndView model = new ModelAndView();

        if (session.getAttribute("usuarioLogueado") == null) {
            model = new ModelAndView("redirect:login.do");
            return model;
        }

        boolean existe = URLgetter.usuarioCampaignValido(session, usuario, campaign);

        if (!existe) {
            model = new ModelAndView("error");
            model.addObject("errorNewCampaign", "No existe esta campaña");
            return model;
        }

        if (result.hasErrors()) {
            populateConPersonajes(model);
            model.setViewName("forms/crearPremioPregunta");
        }

        PremioPregunta pp = new PremioPregunta();
        pp.setCantGanadores(ppf.getCantGanadores());
        pp.setChoices((Set<OpcionPremioPregunta>) ppf.getOpciones());
        pp.setConsigna(ppf.getConsigna());
        if (true) {  // si la fecha es YA, entonces se lanza ya missmo
            pp.setDisparada(false);
        }

        // validar que la fecha sea formatee correctamente
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Date date = null;
        try {

            date = formatter.parse(ppf.getFecha());
            System.out.println(date);
            System.out.println(formatter.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            HibernateDao.crearPremioPregunta(pp);
        } catch (Exception e) {

        }

        pp.setFecha(date);

        // Add message to flash scope
//        redirectAttributes.addFlashAttribute("css", "success");
//        redirectAttributes.addFlashAttribute("msg", "User added successfully!");
        
        HibernateDao.crearPremioPregunta(pp);

        model.setViewName("redirect:verPremioPregunta/" + pp.getId_pp());
        //model.setViewName("redirect:views/showPremioPregunta/"+pp.getId_pp());

        return model;

    }

    private void populateConPersonajes(ModelAndView model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @RequestMapping(value = "/m/{usuario}/{campaign}/verPremioPregunta/{idpp}", method = {RequestMethod.GET})
    public ModelAndView showPremioPregunta(
            @PathVariable("usuario") String usuario,
            @PathVariable("campaign") String campaign,
            @PathVariable("idpp") long idto,
            BindingResult result,
            HttpSession session) {

        ModelAndView model = new ModelAndView();
        boolean existe = URLgetter.usuarioCampaignValido(session, usuario, campaign);

        if (!existe) {
            model = new ModelAndView("error");
            model.addObject("errorNewCampaign", "No existe esta campaña");
            return model;
        }

        // Crear y Validar
        PremioPregunta pp = null;
        try {
            //pp = HibernateDao.obtenerCampaignPorId(idto)
        } catch (Exception e) {

        }

        model.addObject("pp", pp);
        model.setViewName("views/showPremioPregunta");
        return model;
    }

    @RequestMapping(value = "/m/{usuario}/{campaign}/nuevoArma", method = {RequestMethod.GET})
    public ModelAndView crearArma(
            @PathVariable("usuario") String usuario,
            @PathVariable("campaign") String campaign,
            ModelMap mmap, HttpSession session) {

        ModelAndView model = new ModelAndView();
        boolean existe = URLgetter.usuarioCampaignValido(session, usuario, campaign);

        if (!existe) {
            model = new ModelAndView("error");
            model.addObject("errorNewCampaign", "No existe esta campaña");
            return model;
        }

        model.setViewName("crearArma");
        return model;
    }

    @RequestMapping(value = "/m/{usuario}/{campaign}/nuevoArea", method = {RequestMethod.GET})
    public ModelAndView crearArea(
            @PathVariable("usuario") String usuario,
            @PathVariable("campaign") String campaign,
            ModelMap mmap, HttpSession session) {

        ModelAndView model = new ModelAndView();
        boolean existe = URLgetter.usuarioCampaignValido(session, usuario, campaign);

        if (!existe) {
            model = new ModelAndView("error");
            model.addObject("errorNewCampaign", "No existe esta campaña");
            return model;
        }

        model.setViewName("crearArea");
        return model;
    }

}
