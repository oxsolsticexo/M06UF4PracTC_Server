/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Interfaces;

import Entities.Partida;
import Entities.Sesion;
import Logica.Exceptions.SesionJugException;
import Entities.Token;
import javax.ejb.Remote;
import javax.naming.NamingException;

/**
 *
 * @author dolorioUser
 */
@Remote
public interface ISessionManager {

    /**
     * *
     * Valida el cliente
     *
     * @param login
     * @return id de sesión
     * @throws Logica.Exceptions.SesionJugException
     */
    public Sesion getSesion(Token token) throws SesionJugException;

    /**
     * *
     * Retorna la puntuacion máxima del cliente
     *
     * @return
     */
    public Float getPuntuacionMax();

    /**
     * Cierra la sesión del usuario.
     */
    public void cerrarSesion(Token token);

    /**
     * Une al jugador a la partida que ha generado.
     *
     * @param partidaAUnirse
     */
    public void unirsePartida(Partida partidaAUnirse);

    /**
     * Con este método añadimos un jugador a la base de datos para confirmar si
     * esto ocurre devolvemos un booleano
     *
     * @param nombre
     * @param correo
     * @param contrasena
     * @throws javax.naming.NamingException
     */
    public Token registrarJugador(String nombre, String correo) throws NamingException;

    /**
     * método para que el jugador inicie sesión
     * @param email 
     * @return  
     * @throws javax.naming.NamingException 
     */
    public Token loginJugador(String email) throws NamingException;
    
    /**
     * Verificamos si el correo existe para posteriormente iniciar sesión
     *
     * @param correo
     * @return
     */
    public boolean verificarExistenciaCorreo(String correo);

}
