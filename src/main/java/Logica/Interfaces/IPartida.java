/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Interfaces;

import Entities.Partida;
import Entities.Pregunta;
import Entities.Token;
import javax.ejb.Remote;

/**
 *
 * @author Kiwi
 */
@Remote
public interface IPartida {

    public void crearPartida(String nombrePartida, Token token, String dificultad) throws Exception;

    public void setPreguntas(Partida partida) throws IllegalArgumentException;

    public Pregunta asignaPregunta() throws Exception;

    public void iniciarTiempo();

    public void detenerTiempo();

    public void reiniciarTiempo();

    public int getTiempoRestante();

    public Float calculaPuntuacion(int tiempoRestante);
}
