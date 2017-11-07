/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capitanesdegondor.rolemastermanager.service.beans;

import com.websystique.spring.model.Personaje;

/**
 *
 * @author TiranoJuan
 */
public class PersonajeDTO {

    private long id_pj;
    private String nombre;
    private int nivel;
    private String raza;

    public PersonajeDTO() {
    }

    public PersonajeDTO(Personaje p) {
        this.setNombre(p.getNombre());
        this.setId_pj(p.getId_pj());
        this.setNivel(p.getNivel());
        this.setRaza(p.getRaza());
    }

    public long getId_pj() {
        return id_pj;
    }

    public void setId_pj(long id_pj) {
        this.id_pj = id_pj;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }
}
