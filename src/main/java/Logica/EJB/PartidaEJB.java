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
import Logica.TimerLogic;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.EJBContext;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    private TimerLogic timerLogic;

    public PartidaEJB() {
        DAOPregunta = new DAOPregunta();
        DAOejb = new DAOEJB();
        timerLogic = new TimerLogic();
    }

    private static final Logger log = Logger.getLogger(PartidaEJB.class.getName());

    @Resource
    private SessionContext sessionContext;

    @Resource
    private EJBContext ejbContext;

    @PersistenceContext(unitName = "TrivialPersistenceUnit")
    private EntityManager entityManager;

    @Override
    public Pregunta asignaPregunta(Partida partida) throws Exception {

        if (!preguntasLList.isEmpty()) {
            //Mezclamos las preguntas, para que de la "sensaci�n" de que son aleatorias y no siempre se repiten dada una partida
            Collections.shuffle(preguntasLList);
            //Retorna y elimina el primer elemento de la LinkedList.
            Pregunta pregunta = preguntasLList.removeFirst();

            return pregunta;
        } else {
            throw new Exception("Ha surgido un error al cargar la pregunta.");
        }
        // Obtener la primera pregunta de la lista
    }

    @Override
    public int startTimer() {
        if (timerLogic.startTimer() <= 0) {

        }
        return timerLogic.startTimer();
    }

    /**
     * Obtiene la partida cuando el jugador "Crea la partida". Cargamos las
     * preguntas en una linkedList.
     *
     * @param partida
     */
    @Override
    public void setPreguntas(Partida partida) {
        this.preguntasLList = new LinkedList<>(partida.getPreguntasList());
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
}
