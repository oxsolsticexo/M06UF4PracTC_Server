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
    
    public void crearPartida(String nombrePartida, Token token, String dificultad);
    
    public void setPreguntas(Partida p);

    public Pregunta asignaPregunta() throws Exception;
    
    public int startTimer();

}
