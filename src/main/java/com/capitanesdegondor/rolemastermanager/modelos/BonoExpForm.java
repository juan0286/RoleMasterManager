/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capitanesdegondor.rolemastermanager.modelos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author TiranoJuan
 */
public class BonoExpForm {
    
    
    private int bono;
        
    private String motivo;
    
    private long id_pj;    
    
    public BonoExpForm() {
    }

    @NotNull    
    public int getBono() {
        return bono;
    }

    public void setBono(int bono) {
        this.bono = bono;
    }
    
    @NotNull
    @NotBlank
    public String getMotivo() {
        return motivo;
    }

    @NotNull
    public long getId_pj() {
        return id_pj;
    }

    public void setId_pj(long id_pj) {
        this.id_pj = id_pj;
    }

    
    
    public void setMotivo(String Motivo) {
        this.motivo = Motivo;
    }
    
    
    
}
