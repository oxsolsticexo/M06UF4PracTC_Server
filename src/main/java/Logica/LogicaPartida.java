/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import DAO.DAOPartida;



/**
 *
 * @author Carlos
 */
public class LogicaPartida {
    
    public LogicaPartida(){
        
    }

    private final DAOPartida daoPartida = new DAOPartida();
   
    public void crearPartida(String nombre,String dificultad) {
        try {
            if(!nombre.isEmpty() && dificultad.isEmpty()){
               daoPartida.crearPartida(nombre, dificultad);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
