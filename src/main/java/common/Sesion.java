/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.io.Serializable;

/**
 *
 * @author dolorioUser
 */
public class Sesion implements Serializable {

    public Sesion(Token token, String correo) {
        this.token = token;
        this.correo = correo;
    }
    
    private Token token;
    private String correo, nombre;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
