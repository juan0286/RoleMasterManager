/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capitanesdegondor.rolemastermanager.service.beans;

import com.websystique.spring.model.bono.BonoExp;
import java.util.Date;

/**
 *
 * @author TiranoJuan
 */
public class BonoExpDTO {
    
    long idpj;
    String motivo;
    int bono;
    Date fecha;

    public BonoExpDTO() {
    }

    
    public BonoExpDTO(BonoExp be) {
        this.bono = be.getBono();
        this.idpj = be.getPj().getId_pj();
        this.motivo = be.getMotivo();
        this.fecha = be.getFecha();
    }

    
    public long getIdpj() {
        return idpj;
    }

    public void setIdpj(long idpj) {
        this.idpj = idpj;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getBono() {
        return bono;
    }

    public void setBono(int bono) {
        this.bono = bono;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    
}
