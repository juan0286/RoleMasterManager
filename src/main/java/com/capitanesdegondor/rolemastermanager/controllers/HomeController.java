package com.capitanesdegondor.rolemastermanager.controllers;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.websystique.spring.HibernateDao;
import com.websystique.spring.model.Campaign;
import com.websystique.spring.model.Master;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Jerry on 4/5/14.
 */
@Controller
public class HomeController {

    String usuario = "usuarioLogueado";
    
    @RequestMapping(value = "m/{usuario}", method = RequestMethod.GET)
    public String viewHome(ModelMap mmap, HttpSession session) {

//        FileInputStream serviceAccount = null;
//        try {
//            serviceAccount = new FileInputStream("path/to/serviceAccountKey.json");
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//FirebaseOptions options = null;
//        try {
//            options = new FirebaseOptions.Builder()
//                    .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
//                    .setDatabaseUrl("https://rolmanager-5b4df.firebaseio.com/")
//                    .build();
//        } catch (IOException ex) {
//            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//FirebaseApp.initializeApp(options);
        
        
        
        if (session.getAttribute(usuario) != null) {
            
            Master m = (Master) session.getAttribute(usuario);
            
            // Traigo el Master de la base de datos
            Master mActu = HibernateDao.obtenerMasterPorId(m.getId_master());
            
            session.setAttribute(usuario, mActu);
            
            Set<Campaign> l = mActu.getCampaigns();
            session.setAttribute("tieneCampas", (l.size() != 0));          
            
            return "home" ;
        } else
        return "login";
    }
    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String viewHomeFinal(ModelMap mmap, HttpSession session) {
        
        if (session.getAttribute(usuario) != null) {
            
            Master m = (Master) session.getAttribute(usuario);
            
            // Traigo el Master de la base de datos
            Master mActu = HibernateDao.obtenerMasterPorId(m.getId_master());
            
            session.setAttribute(usuario, mActu);
            
            Set<Campaign> l = mActu.getCampaigns();
            session.setAttribute("tieneCampas", (l.size() != 0));          
            
            return "redirect:m/" + mActu.getUsuario() ;
        } else
        return "redirect:login.do";
    }

//    @RequestMapping(value = "m/{usuario}", method = {RequestMethod.GET, RequestMethod.POST})
//    public String viewHomeFinal(@PathVariable("usuario") String name, ModelMap mmap, HttpSession session) {
//        
//        if (session.getAttribute(usuario) == null) {        
//            return "redirect:/login.do";
//        } else
//        return "home";
//    }
    

    @RequestMapping(value = "/login.do", method = {RequestMethod.GET, RequestMethod.POST})
    public String viewLoginFinal( ModelMap mmap, HttpSession session) {
        
        if (session.getAttribute(usuario) == null) {        
            return "login";
        } else
        return "home";
    }
    
//    @RequestMapping(value = "/m/{usuario}/{campaign}", method = {RequestMethod.GET, RequestMethod.POST})
//    public String viewCampaignFinal(ModelMap mmap, HttpSession session) {
//    
//        if (session.getAttribute(usuario) == null) {        
//            return "redirect:/login.htm";
//        }
//        if (session.getAttribute("campaActual") == null) {        
//            return "redirect:/home";
//        }
//        return "campaign";
//    }
    
    @RequestMapping(value = "/m/{usuario}/nuevaCampaign", method = {RequestMethod.GET, RequestMethod.POST})
    public String viewNuevaCampaignFinal(ModelMap mmap, HttpSession session) {    
        return "nuevaCampaign";
    }
    
//    @RequestMapping(value = "/m/{usuario}/{campaign}/solicitudes", method = {RequestMethod.GET, RequestMethod.POST})
//    public String viewSolicitudesFinal(ModelMap mmap, HttpSession session) {    
//        return "solicitudes";
//    }
//    
//    @RequestMapping(value = "/m/{usuario}/{campaign}/bonosExp", method = {RequestMethod.GET, RequestMethod.POST})
//    public String viewBonosExpFinal(ModelMap mmap, HttpSession session) {    
//        return "darExp";
//    }
       
    
}
