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
     * Cierra la sesi�n del jugador
     */
    public void cierraSesion();

    /***
     * Retorna la puntuacion m�xima del cliente
     * @return 
     */
    public Float getPuntuacionMax();
}
