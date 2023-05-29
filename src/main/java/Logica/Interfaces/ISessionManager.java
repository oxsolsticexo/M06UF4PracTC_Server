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
     * @return id de sesión
     * @throws Logica.Exceptions.SesionException
     */
    public Sesion getSesion(Token token) throws SesionException;

    /**
     * Cierra la sesión del usuario.
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
     * Con este método añadimos un jugador a la base de datos para confirmar si
     * esto ocurre devolvemos un booleano
     *
     * @param nombre
     * @param correo
     * @return 
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

}
