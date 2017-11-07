/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capitanesdegondor.rolemastermanager.service.beans;

import com.websystique.spring.model.Campaign;

/**
 *
 * @author TiranoJuan
 */
public class CampaignDTO {
    
    private long id_campaign;
    
    private String nombre;

    private String nombreMaster;

    public CampaignDTO() {
    }
    
    public CampaignDTO(Campaign c) {
        this.nombre = c.getNombre();
        this.nombreMaster = c.getMaster().getUsuario();  
        this.id_campaign = c.getId_campaign();
    }

    public long getId_campaign() {
        return id_campaign;
    }

    public void setId_campaign(long id_campaign) {
        this.id_campaign = id_campaign;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreMaster() {
        return nombreMaster;
    }

    public void setNombreMaster(String nombreMaster) {
        this.nombreMaster = nombreMaster;
    }

    
}
