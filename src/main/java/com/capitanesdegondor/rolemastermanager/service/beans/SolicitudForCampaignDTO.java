/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capitanesdegondor.rolemastermanager.service.beans;

import com.websystique.spring.model.CampaignAccessRequest;
import com.websystique.spring.model.bono.BonoExp;
import java.util.Date;

/**
 *
 * @author TiranoJuan
 */
public class SolicitudForCampaignDTO {
    
    long id_solicitud;
    Date fecha;
    String nombre_jugador;
    String nombre_usuario;
    String status;
    String aceptable = "";
    String rechazable = "";   
    

    public SolicitudForCampaignDTO() {
    }

    public SolicitudForCampaignDTO(CampaignAccessRequest car) {
        this.id_solicitud = car.getId_car();
        this.fecha = car.getFecha();
        this.nombre_jugador = car.getJugador().getNombre();
        this.nombre_usuario = car.getJugador().getNombre_usuario();
        this.status = car.getStatus().name();
        if (car.getStatus() == CampaignAccessRequest.estado.ACEPTADA){
            this.aceptable = "disabled";            
        }else if (car.getStatus() == CampaignAccessRequest.estado.RECHAZADA){
            this.rechazable = "disabled";            
        }        
    }
    
    

    public long getId_solicitud() {
        return id_solicitud;
    }

    public void setId_solicitud(long id_solicitud) {
        this.id_solicitud = id_solicitud;
    }

    public Date getfecha() {
        return fecha;
    }

    public void setfecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombre_jugador() {
        return nombre_jugador;
    }

    public void setNombre_jugador(String nombre_jugador) {
        this.nombre_jugador = nombre_jugador;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getAceptable() {
        return aceptable;
    }

    public void setAceptable(String aceptable) {
        this.aceptable = aceptable;
    }

    public String getRechazable() {
        return rechazable;
    }

    public void setRechazable(String rechazable) {
        this.rechazable = rechazable;
    }

    
    
}
