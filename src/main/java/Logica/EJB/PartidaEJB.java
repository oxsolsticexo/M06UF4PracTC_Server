/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.EJB;

import DAO.DAOEJB;
import DAO.DAOPregunta;
import Entities.Jugador;
import Entities.Lookups;
import Logica.Interfaces.IPartida;
import Entities.Partida;
import Entities.Pregunta;
import Entities.Token;
import Logica.Exceptions.PartidaExceptions;
import Logica.Exceptions.SesionJugException;
import Logica.Interfaces.DAOInterface;
import Logica.Interfaces.IDAOPregunta;
import Logica.Interfaces.IPregunta;
import Logica.Interfaces.ISessionManager;
import Logica.Interfaces.LogicaInterface;
import Logica.PreguntaLogic;
import Logica.TimerLogic;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.EJBContext;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nu.xom.Document;
import nu.xom.ParsingException;
import singleton.DataConvert;

/**
 *
 * @author Kiwi
 */
@Stateful
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class PartidaEJB implements IPartida {

    private DAOInterface DAOejb;
    private IDAOPregunta DAOPregunta;
    private LinkedList<Pregunta> preguntasLList;
    private TimerLogic timerLogic;
    private IPregunta pregunta;
    private ISessionManager sessionManager;
    private PreguntaLogic logic;
    private LogicaInterface userLogic;
    
    public PartidaEJB() {
        DAOPregunta = new DAOPregunta();
        timerLogic = new TimerLogic();
        logic = new PreguntaLogic();
    }

    private static final Logger log = Logger.getLogger(PartidaEJB.class.getName());

    @Resource
    private SessionContext sessionContext;

    @Resource
    private EJBContext ejbContext;

    @PersistenceContext(unitName = "TrivialPersistenceUnit")
    private EntityManager entityManager;

    @Override
    public Pregunta asignaPregunta() throws Exception {

        if (!preguntasLList.isEmpty()) {
            //Mezclamos las preguntas, para que de la "sensaci�n" de que son aleatorias y no siempre se repiten dada una partida
            Collections.shuffle(preguntasLList);
            //Retorna y elimina el primer elemento de la LinkedList.
            Pregunta pregunta = preguntasLList.removeFirst();

            return pregunta;
        } else {
            throw new PartidaExceptions("Partida finalizada");
        }
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
        if (partida != null && partida.getPreguntasList() != null) {
            this.preguntasLList = new LinkedList<>(partida.getPreguntasList());
        } else {
            throw new IllegalArgumentException("La partida o la lista de preguntas es nula.");
        }
    }

    @Override
    public void crearPartida(String nombrePartida, Token token, String dificultad) {

        try {
            DAOejb = Lookups.DAOEJBLocalLookup();
            DAOPregunta = Lookups.DAOPreguntaLocalLookup();
            pregunta = Lookups.preguntaEJBRemoteLookup();
            sessionManager = Lookups.sessionManagerEJBRemoteLookup();
            //userInterface = Lookups.
            Jugador jug = DAOejb.findUser(sessionManager.getSesion(token).getCorreo());
            ArrayList<Jugador> listado = new ArrayList<>(Arrays.asList(jug));

            Partida partida = new Partida();
            partida.setNombre(nombrePartida);
            partida.setDificultad(dificultad);
            partida.setJugadoresList(listado);
            Document b = DataConvert.StringToDocument(pregunta.readFile());
            List<Pregunta> pre = logic.xmlToArrayList(b);
            System.out.println(pre);
            pregunta.setPreguntasBBDD(pre);
            System.out.println(partida.getNombre());
            partida.setPreguntasList(DAOPregunta.getPreguntasBBDD(partida));
        
            //entityManager.persist(partida);
            DAOejb.validPersist(partida);
        } catch (NamingException ex) {
            Logger.getLogger(PartidaEJB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParsingException ex) {
            Logger.getLogger(PartidaEJB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PartidaEJB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SesionJugException ex) {
            Logger.getLogger(PartidaEJB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
