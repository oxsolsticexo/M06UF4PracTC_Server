/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import Logica.TimerLogic;
import common.IPartida;
import common.Partida;
import common.Pregunta;
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

    private static final Logger log = Logger.getLogger(PartidaEJB.class.getName());

    LinkedList<Pregunta> preguntasLList;

    @Resource
    private SessionContext sessionContext;

    @Resource
    private EJBContext ejbContext;

    @PersistenceContext(unitName = "TrivialPersistenceUnit")
    private EntityManager entityManager;

    TimerLogic timerLogic;

    @Override
    public Pregunta asignaPregunta(Partida partida) throws Exception {

        if (!preguntasLList.isEmpty()) {
            //Mezclamos las preguntas, para que de la "sensación" de que son aleatorias y no siempre se repiten dada una partida
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

}
