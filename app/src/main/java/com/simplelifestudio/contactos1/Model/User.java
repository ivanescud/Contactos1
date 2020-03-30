package com.simplelifestudio.contactos1.Model;

public class User {

    private String nombre;
    private String apellido;
    private String numTelf;
    private String email;
    private String userID;


    public User() {
    }

    public User(String nombre, String apellido, String numTelf, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numTelf = numTelf;
        this.email = email;
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumTelf() {
        return numTelf;
    }

    public void setNumTelf(String numTelf) {
        this.numTelf = numTelf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
