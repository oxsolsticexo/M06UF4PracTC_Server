/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import common.XMLProcessorImpl;
import Logica.PreguntaLogic;
import common.IJugador;
import common.IPartida;
import common.IPregunta;
import common.Jugador;
import common.Lookups;
import common.Partida;
import common.Pregunta;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.naming.NamingException;
import main.AppSingletonEJB;
import nu.xom.Document;
import singleton.DataConvert;

/**
 *
 * @author Carlos
 */
public class DAOPartida {

    PreguntaLogic preguntas = new PreguntaLogic();

    public DAOPartida() {

    }

    public void crearPartida(String nombre, String jugador, String dificultad) throws IOException, NamingException {

        
        try {
            IPartida partidas = Lookups.partidaEJBRemoteLookup();
            IPregunta pregunta = Lookups.preguntaEJBRemoteLookup();

            Jugador j = new Jugador("julian@gmail.com", "julian", 0, 0);
            System.out.println("Ha entrado en dao");
            //List<Pregunta> v = g.procesarXML();
            //Crear jugador
            Jugador jug = new Jugador("Juan", dificultad, 0, 0);
            ArrayList<Jugador> listado = new ArrayList<>(Arrays.asList(jug));
            Document b = DataConvert.StringToDocument(pregunta.readFile());
            List<Pregunta> pre = preguntas.xmlToArrayList(b);
            pregunta.setPreguntasBBDD(pre);
            //System.out.println(b.getValue());
            //Crear partida
            Partida partida = new Partida();
            partida.setNombre(nombre);
            partida.setDificultad(dificultad);
            partida.setJugadoresList(listado);
            pregunta.readFile();
            //Document d = pregunta.obtenerPreguntas();
            List<Pregunta> p = pregunta.getPreguntasBBDD(partida);
            System.out.println("");
            
            partidas.setPregunta(partida);
            //partida.setPreguntasList(preguntas.xmlToArrayList());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
