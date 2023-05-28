/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Interfaces;

import Entities.Partida;
import Entities.Pregunta;
import javax.ejb.Remote;

/**
 *
 * @author Kiwi
 */
@Remote
public interface IPartida {
    
    public void crearPartida(String nombrePartida, String dificultad);
    
    public void setPreguntas(Partida p);

    public Pregunta asignaPregunta(Partida p) throws Exception;
    
    public int startTimer();

}
