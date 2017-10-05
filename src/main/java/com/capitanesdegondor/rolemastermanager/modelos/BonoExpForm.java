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
        
    private String Motivo;

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
        return Motivo;
    }

    public void setMotivo(String Motivo) {
        this.Motivo = Motivo;
    }
    
    
    
}
