/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import javax.ejb.Remote;

/**
 *
 * @author Kiwi
 */
@Remote
public interface IJugador {
    /***
     * Valida el cliente
     * @param login
     * @return id de sesi�n
     * @throws common.CompraException si el cliente no existe
     */
    public String getSesion(String login) throws SesionJugException;

    /***
     * Retorna la puntuacion m�xima del cliente
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
     * Con este m�todo a�adimos un jugador a la base de datos
     * para confirmar si esto ocurre devolvemos un booleano
     * @param nombre
     * @param correo
     * @param contrasena
     * @return 
     */
    public void registrarJugador(Jugador jugador);
    
    /**
     * Verificamos si el correo existe para posteriormente iniciar sesi�n
     * @param correo
     * @return 
     */
    public boolean verificarExistenciaCorreo(String correo);
    /**
     * Retorna el nombre del jugador
     * @return 
     */
    public String getName();

}
