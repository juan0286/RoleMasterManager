/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capitanesdegondor.rolemastermanager.service;

import com.capitanesdegondor.rolemastermanager.service.beans.NuevoUsuarioJugador;
import com.capitanesdegondor.rolemastermanager.validations.HiberRespuesta;
import com.capitanesdegondor.rolemastermanager.validations.Validations;
import com.websystique.spring.model.Jugador;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author TiranoJuan
 */
@Controller
@RequestMapping("/{solicitud}/nuevo")
public class Services {

//    @Autowired
//    IOfertasBO obo;
//    @RequestMapping(method = RequestMethod.PUT,
//            value = "{idOferta}",
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Jugador> obtenerOferta()
//            throws OfertaInexistenteException {
//
//        HiberRespuesta hr = Validations.crearNuevoJugador(nuj);
//
//        return new ResponseEntity<Jugador>(oferta, HttpStatus.OK);
//
//    }

    @RequestMapping(method = RequestMethod.PUT, value = "")
    public ResponseEntity<Void> nuevoJugador(
            @RequestBody NuevoUsuarioJugador nuj,
            HttpServletRequest peticion) {
        
        
        HiberRespuesta hr = Validations.crearNuevoJugador(nuj);
        Jugador j = null;
        if (hr.isSuccess()){
            j = (Jugador) hr.getResult();
        }
        
        HttpHeaders cabeceras = new HttpHeaders();
        
        try {
            cabeceras.setLocation(new URI(peticion.getRequestURL()
                    + Long.toString(j.getId_jugador())));
        } catch (URISyntaxException ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ResponseEntity<Void>(cabeceras, HttpStatus.CREATED);
    }
    
//    @RequestMapping(method = RequestMethod.GET, value = "")
//    public ResponseEntity<Void> campa(
//            @PathVariable String solicitud,
//            HttpServletRequest peticion) {
//        
//        
//        
//        return new ResponseEntity<Void>(cabeceras, HttpStatus.CREATED);
//    }
}
