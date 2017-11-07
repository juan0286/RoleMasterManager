/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capitanesdegondor.rolemastermanager.service.beans;

import com.websystique.spring.model.CampaignAccessRequest;
import java.util.Date;

/**
 *
 * @author TiranoJuan
 */
public class CampaignAccessRequestDTO {

    private long id_car;

    private Date fecha;

    private CampaignDTO cdto;

    private long id_jugador;

    private int status;

    public CampaignAccessRequestDTO() {
    }

    public CampaignAccessRequestDTO(CampaignAccessRequest car) {
        this.id_car = car.getId_car();
        this.fecha = car.getFecha();
        CampaignDTO cto = new CampaignDTO(car.getCampaign());
        this.cdto = cto;
        this.id_jugador = car.getJugador().getId_jugador();
        this.status = car.getStatus();
    }

    public long getId_car() {
        return id_car;
    }

    public void setId_car(long id_car) {
        this.id_car = id_car;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public CampaignDTO getCdto() {
        return cdto;
    }

    public void setCdto(CampaignDTO cdto) {
        this.cdto = cdto;
    }

    public long getId_jugador() {
        return id_jugador;
    }

    public void setId_jugador(long id_jugador) {
        this.id_jugador = id_jugador;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    
    
}
