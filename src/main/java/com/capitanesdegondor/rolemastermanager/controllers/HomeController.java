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
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Jerry on 4/5/14.
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
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
        
        String usuario = "usuarioLogueado";
        if (session.getAttribute(usuario) != null) {
            
            Master m = (Master) session.getAttribute(usuario);
            
            // Traigo el Master de la base de datos
            Master mActu = HibernateDao.obtenerMasterPorId(m.getId_master());
            
            session.setAttribute(usuario, mActu);
            
            Set<Campaign> l = mActu.getCampaigns();
            session.setAttribute("tieneCampas", (l.size() != 0));          
            return "home";
        } else
        return "redirect:/login.htm";
    }

}
