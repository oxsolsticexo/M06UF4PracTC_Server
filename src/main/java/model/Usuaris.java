/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author carlo
 */
public class Usuaris {
 
    private int posicion;
    private String nick;
    private int puntuacion;
    private int puntuacion_partida;

    public Usuaris() {
        
    }

    public Usuaris(int posicion, String nick, int puntuacion, int puntuacion_partida) {
        this.posicion = posicion;
        this.nick = nick;
        this.puntuacion = puntuacion;
        this.puntuacion_partida = puntuacion_partida;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getPuntuacion_partida() {
        return puntuacion_partida;
    }

    public void setPuntuacion_partida(int puntuacion_partida) {
        this.puntuacion_partida = puntuacion_partida;
    }

    @Override
    public String toString() {
        return "Usuaris{" + "posicion=" + posicion + ", nick=" + nick + ", puntuacion=" + puntuacion + ", puntuacion_partida=" + puntuacion_partida + '}';
    }
    
    
}
