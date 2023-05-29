/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Interfaces;

import Entities.Partida;
import Entities.Sesion;
import Logica.Exceptions.SesionException;
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
     * @param token
     * @return id de sesi�n
     * @throws Logica.Exceptions.SesionException
     */
    public Sesion getSesion(Token token) throws SesionException;

    /**
     * Cierra la sesi�n del usuario.
     * @param token
     */
    public void cerrarSesion(Token token);

    /**
     * Une al jugador a la partida que ha generado.
     *
     * @param partidaAUnirse
     */
    public void unirsePartida(Partida partidaAUnirse);

    /**
     * Con este m�todo a�adimos un jugador a la base de datos para confirmar si
     * esto ocurre devolvemos un booleano
     *
     * @param nombre
     * @param correo
     * @return 
     * @throws javax.naming.NamingException
     */
    public Token registrarJugador(String nombre, String correo) throws NamingException;

    /**
     * m�todo para que el jugador inicie sesi�n
     * @param email 
     * @return  
     * @throws javax.naming.NamingException 
     */
    public Token loginJugador(String email) throws NamingException;

}
