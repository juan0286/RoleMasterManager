/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capitanesdegondor.rolemastermanager.modelos;

import com.websystique.spring.model.Jugador;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author TiranoJuan
 */
public class CrearPjForm {
    
    
    
    private String nombre;
   
    private Jugador jugador; 
    
    private int exp;
    
    private int nivel;
    
    private String raza;
    
    private int altura;
    
    private int peso;
    
    private String genero;
    
    private int edad;
    
    private String motivacion;
    
    private String alineamiento;
    
    private String estado_civico;
    
    private String familia;
    
    private String diosesAdorados;
    
    private String religion;
    
   // private Habilidades habilidades;    
    
    //private Caracteristicas caracteristicas;     


    public CrearPjForm() {
    }
//
//    @NotNull    
//    public int getBono() {
//        return bono;
//    }
//
//    public void setBono(int bono) {
//        this.bono = bono;
//    }
//    
//    @NotNull
//    @NotBlank
//    public String getMotivo() {
//        return Motivo;
//    }
//
//    public void setMotivo(String Motivo) {
//        this.Motivo = Motivo;
//    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
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

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getMotivacion() {
        return motivacion;
    }

    public void setMotivacion(String motivacion) {
        this.motivacion = motivacion;
    }

    public String getAlineamiento() {
        return alineamiento;
    }

    public void setAlineamiento(String alineamiento) {
        this.alineamiento = alineamiento;
    }

    public String getEstado_civico() {
        return estado_civico;
    }

    public void setEstado_civico(String estado_civico) {
        this.estado_civico = estado_civico;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getDiosesAdorados() {
        return diosesAdorados;
    }

    public void setDiosesAdorados(String diosesAdorados) {
        this.diosesAdorados = diosesAdorados;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }
       
    
}
