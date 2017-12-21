/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capitanesdegondor.rolemastermanager.modelos;

import com.websystique.spring.model.Jugador;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 *
 * @author TiranoJuan
 */
public class ObjetoTipoForm {

    private String nombre;

    private float tam_stnd; // cm    

    private float peso_stnd; // gramos    

    private float degradado;  // pts de vida que pierde por asalto    

    private float durabilidad_stnd;  // ptos de vida del objeto > 100 integro. < a 100 el porcentaje de integridad

    //private Set<Info> informacion;
    // private Habilidades habilidades;    
    //private Caracteristicas caracteristicas;     
    public ObjetoTipoForm() {
    }

    @NotNull
    @NotBlank
    public String getNombre() {
        return nombre;
    }

    @NotNull
    @DecimalMin("0.00")
    public float getTam_stnd() {
        return tam_stnd;
    }

    @NotNull
    @DecimalMin("0.00")
    public float getPeso_stnd() {
        return peso_stnd;
    }

    @NotNull
    @DecimalMin("0.00")
    public float getDegradado() {
        return degradado;
    }

    @NotNull 
    @DecimalMin("0.00")
    public float getDurabilidad_stnd() {
        return durabilidad_stnd;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTam_stnd(float tam_stnd) {
        this.tam_stnd = tam_stnd;
    }

    public void setPeso_stnd(float peso_stnd) {
        this.peso_stnd = peso_stnd;
    }

    public void setDegradado(float degradado) {
        this.degradado = degradado;
    }

    public void setDurabilidad_stnd(float durabilidad_stnd) {
        this.durabilidad_stnd = durabilidad_stnd;
    }

}
