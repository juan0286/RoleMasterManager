/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capitanesdegondor.rolemastermanager.modelos;

import com.websystique.spring.model.Jugador;
import com.websystique.spring.model.Personaje;
import com.websystique.spring.model.messaging.OpcionPremioPregunta;
import com.websystique.spring.model.messaging.Premio;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author TiranoJuan
 */
public class PremioPreguntaForm {

    private String titulo;
    
    private String fecha;

    private String consigna;

    private boolean disparada;
 
    private int timeResponse;
    
    private int cantGanadores;
    
    private boolean waitForAll;

    // PREMIO
    // Lista de opciones
    private List<OpcionPremioPregunta> opciones;
    
    private List<Personaje> personajes;

    public PremioPreguntaForm() {
    }

    @NotNull
    public String getFecha() {
        return fecha;
    }    
    
    @NotNull
    @NotBlank
    public String getTitulo() {
        return titulo;
    }

    @NotNull
    @NotBlank
    public String getConsigna() {
        return consigna;
    }

    public boolean isDisparada() {
        return disparada;
    }

    @NotNull
    @Min(0)
    public int getTimeResponse() {
        return timeResponse;
    }

    @NotNull
    @Min(0)
    public int getCantGanadores() {
        return cantGanadores;
    }

    public boolean isWaitForAll() {
        return waitForAll;
    }

    @NotNull
    @NotEmpty
    public List<OpcionPremioPregunta> getOpciones() {
        return opciones;
    }
    
    @NotNull
    @NotEmpty
    public List<Personaje> getPersonajes() {
        return personajes;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setConsigna(String consigna) {
        this.consigna = consigna;
    }

    public void setDisparada(boolean disparada) {
        this.disparada = disparada;
    }

    public void setTimeResponse(int timeResponse) {
        this.timeResponse = timeResponse;
    }

    public void setCantGanadores(int cantGanadores) {
        this.cantGanadores = cantGanadores;
    }

    public void setWaitForAll(boolean waitForAll) {
        this.waitForAll = waitForAll;
    }

    public void setOpciones(List<OpcionPremioPregunta> opciones) {
        this.opciones = opciones;
    }    

    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }

    
    
}
