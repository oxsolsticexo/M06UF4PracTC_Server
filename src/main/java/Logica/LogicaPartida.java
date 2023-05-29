/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import DAO.DAOPartida;
import javax.naming.NamingException;

/**
 *
 * @author Carlos
 */
public class LogicaPartida {

    private DAOPartida daoPartida;

    public LogicaPartida() throws NamingException {
        this.daoPartida = new DAOPartida();

    }

//    public void crearPartida(String nombre, String jugador, String dificultad) {
//        try {
//            if (!nombre.isEmpty() && !dificultad.isEmpty()) {
//                daoPartida.crearPartida(nombre, jugador, dificultad);
//            }
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
}
