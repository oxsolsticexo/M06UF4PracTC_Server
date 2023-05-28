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
     * @return id de sesión
     * @throws common.CompraException si el cliente no existe
     */
    public String getSesion(String login) throws SesionJugException;

    /***
     * Retorna la puntuacion máxima del cliente
     * @return 
     */
    public Float getPuntuacionMax();
    /**
     * Cierra la sesión del usuario.
     */
    public void cerrarSesion();

    /**
     * Une al jugador a la partida que ha generado.
     *
     * @param partidaAUnirse
     */
    public void unirsePartida(Partida partidaAUnirse);
    
    /**
     * Con este método añadimos un jugador a la base de datos
     * para confirmar si esto ocurre devolvemos un booleano
     * @param nombre
     * @param correo
     * @param contrasena
     * @return 
     */
    public void registrarJugador(Jugador jugador);
    
    /**
     * Verificamos si el correo existe para posteriormente iniciar sesión
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
