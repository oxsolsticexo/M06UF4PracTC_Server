/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Logica.Interfaces;

import Entities.Jugador;
import javax.ejb.Remote;

/**
 *
 * @author carlo
 */

@Remote
public interface DAOInterface {

    /**
     * Creamos un jugador que nos pasan por parámetro
     *
     * @param jug
     */
    public void createUser(Jugador jug);

    /**
     * Comprueba si un usuario está en la base de datos
     *
     * @param email
     * @return
     */
    public Jugador findUser(String email);

    /**
     * Método que recibe un objeto lo valida haciendo uso de la clase validadors
     * y lo persiste 
     *
     * @param ob
     */
    public void validPersist(Object ob);
    
}
