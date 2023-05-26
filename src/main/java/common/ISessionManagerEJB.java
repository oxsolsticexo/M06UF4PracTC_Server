/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import javax.ejb.Remote;
import javax.naming.NamingException;

/**
 *
 * @author dolorioUser
 */
@Remote
public interface ISessionManagerEJB {

    /**
     * *
     * Valida el cliente
     *
     * @param login
     * @return id de sesi�n
     * @throws common.CompraException si el cliente no existe
     */
    public String getSesion(String login) throws SesionJugException;

    /**
     * *
     * Retorna la puntuacion m�xima del cliente
     *
     * @return
     */
    public Float getPuntuacionMax();

    /**
     * Cierra la sesi�n del usuario.
     */
    public void cerrarSesion();

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
     * @param contrasena
     */
    public void registrarJugador(String nombre, String correo) throws NamingException;

    /**
     * Verificamos si el correo existe para posteriormente iniciar sesi�n
     *
     * @param correo
     * @return
     */
    public boolean verificarExistenciaCorreo(String correo);

}