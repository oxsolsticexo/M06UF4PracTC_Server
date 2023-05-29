/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.EJB;

import Entities.Jugador;
import Entities.Lookups;
import Logica.Interfaces.IPartida;
import Entities.Partida;
import Entities.Pregunta;
import Entities.Token;
import Logica.Exceptions.PartidaExceptions;
import Logica.Exceptions.SesionException;
import Logica.Interfaces.DAOInterface;
import Logica.Interfaces.IDAOPregunta;
import Logica.Interfaces.IPregunta;
import Logica.Interfaces.ISessionManager;
import Logica.PreguntaLogic;
import Logica.TimerLogic;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.NamingException;
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
    private TimerLogic temporizador;
    private IPregunta preguntaEJB;
    private ISessionManager sessionManager;
    private PreguntaLogic logic;

    public PartidaEJB() {
        temporizador = new TimerLogic();
        logic = new PreguntaLogic();
        preguntasLList = new LinkedList<>();
    }

    private static final Logger log = Logger.getLogger(PartidaEJB.class.getName());

    /**
     * Retorna una pregunta a la capa de presentaci�n.
     *
     * @return
     * @throws Exception
     */
    @Override
    public Pregunta asignaPregunta() throws Exception {

        if (preguntasLList != null && !preguntasLList.isEmpty()) {

            System.out.println("No estoy vac�a");
            //Mezclamos las preguntas, para que de la "sensaci�n" de que son aleatorias y no siempre se repiten dada una partida
            Collections.shuffle(preguntasLList);
            //Retorna y elimina el primer elemento de la LinkedList.
            Pregunta pregunta = preguntasLList.removeFirst();

            return pregunta;
        } else {
            throw new PartidaExceptions("Partida finalizada debido a un error, la lista est� vac�a o es nula.");
        }
    }

    /**
     * Obtiene la partida cuando el jugador "Crea la partida".Cargamos las
     * preguntas en una linkedList.
     *
     * @param partida
     */
    @Override
    public void setPreguntas(Partida partida) throws IllegalArgumentException {

        if (partida.getPreguntasList() != null && !partida.getPreguntasList().isEmpty()) {

            this.preguntasLList = new LinkedList<>(partida.getPreguntasList());

        } else {
            throw new IllegalArgumentException("La partida o la lista de preguntas es nula.");
        }
    }

    /**
     * Recibe el nombre de la partida, la dificultad y el Token del usuario que
     * genera la partida.
     *
     * @param nombrePartida
     * @param token
     * @param dificultad
     * @throws NamingException
     * @throws ParsingException
     * @throws IOException
     * @throws SesionException
     */
    @Override
    public void crearPartida(String nombrePartida, Token token, String dificultad) throws Exception {

        try {

            if (!nombrePartida.isEmpty() && !dificultad.isEmpty()) {
                
                DAOPregunta = Lookups.DAOPreguntaLocalLookup();
                persistirPreguntas();

                Partida partida = new Partida();
                partida.setNombre(nombrePartida);
                partida.setDificultad(dificultad);
                partida.setJugador(buscarJugadorPartida(token));
                partida.setPreguntasList(DAOPregunta.getPreguntasBBDD(partida));

                setPreguntas(partida);

                persistirPartida(partida);
            } else if (nombrePartida.isEmpty() && !dificultad.isEmpty()) {
                throw new PartidaExceptions("Debes introducir un nombre");
            } else if (!nombrePartida.isEmpty() && dificultad.isEmpty()) {
                throw new PartidaExceptions("Debes seleccionar una dificultad");
            } else {
                throw new PartidaExceptions("Debes introducir un nombre y seleccionar una dificultad");
            }

        }catch (NullPointerException e) {
            System.out.println("Estoy Nulo");
            throw e;
        } catch (Exception e) {
            System.out.println("Estoy aqui");
            throw e;
        }

    }

    /**
     * Recibe el Token y busca al jugador en base al token, retorna un jugador
     * una vez localizado el usuario
     *
     * @param token
     * @return
     * @throws NamingException
     * @throws SesionException
     */
    private Jugador buscarJugadorPartida(Token token) throws NamingException, SesionException {

        sessionManager = Lookups.sessionManagerEJBRemoteLookup();
        DAOejb = Lookups.DAOEJBLocalLookup();

        Jugador jug = DAOejb.obtenerJugador(sessionManager.getSesion(token).getCorreo());

        return jug;
    }

    /**
     * Recibe la partida y la persiste.
     *
     * @param partida
     * @throws NamingException
     */
    private void persistirPartida(Partida partida) throws NamingException {

        DAOejb = Lookups.DAOEJBLocalLookup();
        DAOejb.validPersist(partida);
    }

    /**
     * Procesa el XML de preguntas y las persiste en la BBDD.
     *
     * @throws NamingException
     * @throws ParsingException
     * @throws IOException
     */
    private void persistirPreguntas() throws NamingException, ParsingException, IOException {

        preguntaEJB = Lookups.preguntaEJBRemoteLookup();

        Document b = DataConvert.StringToDocument(preguntaEJB.readFile());
        List<Pregunta> pre = logic.xmlToArrayList(b);

        preguntaEJB.setPreguntasBBDD(pre);
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

    /**
     * Calculamos la puntuaci�n obtenida en base al tiempo restante
     *
     * @param tiempoRestante
     * @return
     */
    @Override
    public Float calculaPuntuacion(int tiempoRestante) {

        if (tiempoRestante >= 25) {
            return 10f;
        }
        if (tiempoRestante < 25 && tiempoRestante >= 15) {
            return 7.5f;
        }
        if (tiempoRestante < 15 && tiempoRestante >= 10) {
            return 5f;
        }
        if (tiempoRestante < 10 && tiempoRestante >= 5) {
            return 2.5f;
        }
        if (tiempoRestante < 5 && tiempoRestante > 0) {
            return 1f;
        }
        return 0f;
    }

    /**
     * Obtenemos el token y la puntuaci�n del jugador una vez finalizada la
     * partida
     *
     * @param token
     * @param puntuacionJugador
     * @throws NamingException
     * @throws SesionException
     */
    @Override
    public void persistirDatosPartida(Token token, Float puntuacionJugador) throws NamingException, SesionException {

        DAOejb = Lookups.DAOEJBLocalLookup();

        Jugador jugador = buscarJugadorPartida(token);

        System.out.println(jugador.toString());

        if (jugador.getPuntuacionTotal() == null) {
            jugador.setPuntuacionTotal(0f);
        }

        Float puntuacionTotalJugador = jugador.getPuntuacionTotal();

        System.out.println("Esta es la que ten�a: " + jugador.getPuntuacionTotal());

        if (jugador.getMaxPuntuacionPartida() == null || jugador.getMaxPuntuacionPartida() < puntuacionJugador) {
            jugador.setMaxPuntuacionPartida(puntuacionJugador);
        }

        puntuacionTotalJugador += puntuacionJugador;

        System.out.println("Esta es la suma de la anterior m�s la nueva: " + puntuacionTotalJugador);

        jugador.setPuntuacionTotal(puntuacionTotalJugador);

        DAOejb.validPersist(jugador);
    }
}
