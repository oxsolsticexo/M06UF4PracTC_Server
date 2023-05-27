/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Logica.PreguntaLogic;
import common.Jugador;
import common.Partida;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Carlos
 */
public class DAOPartida {

    PreguntaLogic preguntas = new PreguntaLogic();

    public DAOPartida() {

    }

    public void crearPartida(String nombre, String jugador, String dificultad) throws IOException {


        
        
       
        try {
            System.out.println("Ha entrado en dao");
            Jugador jug = new Jugador("Juan", dificultad, 0, 0);
            ArrayList<Jugador> listado = new ArrayList<>(Arrays.asList(jug));
            Partida partida = new Partida();
            partida.setNombre(nombre);
            partida.setDificultad(dificultad);
            partida.setJugadoresList(listado);
            
            partida.setPreguntasList(preguntas.xmlToArrayList());
            
            
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
