package com.capitanesdegondor.rolemastermanager.controllers;

import com.websystique.spring.HibernateDao;
import com.websystique.spring.model.Campaign;
import com.websystique.spring.model.Master;
import java.util.List;
import java.util.Set;
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
