/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.EJB;

import DAO.DAOEJB;
import DAO.DAOPregunta;
import Entities.Jugador;
import Logica.Interfaces.IPartida;
import Entities.Partida;
import Entities.Pregunta;
import Logica.Exceptions.PartidaExceptions;
import Logica.TimerLogic;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.logging.Logger;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

/**
 *
 * @author Kiwi
 */
@Stateful
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class PartidaEJB implements IPartida {

    private DAOEJB DAOejb;
    private DAOPregunta DAOPregunta;
    private LinkedList<Pregunta> preguntasLList;
    private TimerLogic temporizador;

    public PartidaEJB() {
        DAOPregunta = new DAOPregunta();
        DAOejb = new DAOEJB();
    }

    private static final Logger log = Logger.getLogger(PartidaEJB.class.getName());

    @Override
    public Pregunta asignaPregunta() throws Exception {

        if (preguntasLList.isEmpty()) {
            System.out.println("Estoy vac�a");
        }

        if (!preguntasLList.isEmpty()) {
            System.out.println("He entrado en preguntasList");
            //Mezclamos las preguntas, para que de la "sensaci�n" de que son aleatorias y no siempre se repiten dada una partida
            Collections.shuffle(preguntasLList);
            //Retorna y elimina el primer elemento de la LinkedList.
            Pregunta pregunta = preguntasLList.removeFirst();

            return pregunta;
        } else {
            throw new PartidaExceptions("Partida finalizada");
        }
    }

    /**
     * Obtiene la partida cuando el jugador "Crea la partida". Cargamos las
     * preguntas en una linkedList.
     *
     * @param partida
     */
    @Override
    public void setPreguntas(Partida partida) {
        if (partida != null && partida.getPreguntasList() != null) {
            this.preguntasLList = new LinkedList<>(partida.getPreguntasList());
        } else {
            throw new IllegalArgumentException("La partida o la lista de preguntas es nula.");
        }
    }

    @Override
    public void crearPartida(String nombrePartida, String dificultad) {

        Jugador jug = new Jugador();

        ArrayList<Jugador> listado = new ArrayList<>(Arrays.asList(jug));

        Partida partida = new Partida();
        partida.setNombre(nombrePartida);
        partida.setDificultad(dificultad);
        partida.setJugadoresList(listado);
        partida.setPreguntasList(DAOPregunta.getPreguntasBBDD(partida));

        DAOejb.validPersist(partida);
    }

    @Override
    public void iniciarTiempo() {
        temporizador = new TimerLogic();
        temporizador.iniciarTemporizador();
    }

    @Override
    public void detenerTiempo() {
        temporizador.detenerTemporizador();
    }

    @Override
    public void reiniciarTiempo() {
        temporizador.reiniciarTemporizador();
    }

    @Override
    public int getTiempoRestante() {
        return temporizador.getTiempoRestante();
    }
}
