/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capitanesdegondor.rolemastermanager.service.beans;

/**
 *
 * @author TiranoJuan
 */
public class SolicitudDTO {
    
    long id_car;
    String accion;

    public SolicitudDTO() {
    }

    public long getId_car() {
        return id_car;
    }

    public void setId_car(long id_car) {
        this.id_car = id_car;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String Accion) {
        this.accion = Accion;
    }
    
    
}
