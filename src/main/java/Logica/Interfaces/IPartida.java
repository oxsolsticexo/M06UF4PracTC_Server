/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Interfaces;

import Entities.Partida;
import Entities.Pregunta;
import Entities.Token;
import Logica.Exceptions.SesionJugException;
import java.io.IOException;
import javax.ejb.Remote;
import javax.naming.NamingException;
import nu.xom.ParsingException;

/**
 *
 * @author Kiwi
 */
@Remote
public interface IPartida {

    public void crearPartida(String nombrePartida, Token token, String dificultad) throws NamingException, ParsingException, IOException, SesionJugException;

    public void setPreguntas(Partida partida) throws IllegalArgumentException;

    public Pregunta asignaPregunta() throws Exception;

    public void iniciarTiempo();

    public void detenerTiempo();

    public void reiniciarTiempo();

    public int getTiempoRestante();

    public Float calculaPuntuacion(int tiempoRestante);

    public void persistirDatosPartida(Token token, Float puntuacionJugador) throws SesionJugException, NamingException;
}
