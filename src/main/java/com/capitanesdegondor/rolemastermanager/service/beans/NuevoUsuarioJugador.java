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
public class NuevoUsuarioJugador {
    
    private String id_firebase;
    private String email;
    private String name;
    private String urlImage;

    public NuevoUsuarioJugador() {
    }

    public String getId_firebase() {
        return id_firebase;
    }

    public void setId_firebase(String id_firebase) {
        this.id_firebase = id_firebase;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    @Override
    public String toString() {
        return "NuevoUsuarioJugador{" + "email=" + email + ", name=" + name + ", urlImage=" + urlImage + '}';
    }
    
    
    
}
