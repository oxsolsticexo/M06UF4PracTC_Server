/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import DAO.DAO;
import common.IJugador;
import common.Jugador;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import common.Partida;
import common.SesionJugException;
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
import javax.persistence.PersistenceContextType;

/**
 *
 * @author Kiwi
 */
@Stateful
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class JugadorEJB implements IJugador {

    @Resource
    private SessionContext sessionContext;

    @Resource
    private EJBContext ejbContext;

    @PersistenceContext(unitName = "TrivialPersistenceUnit", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    private static final Logger log = Logger.getLogger(JugadorEJB.class.getName());

    Jugador jugador;

    String idSesionJugador = null;

    @PostConstruct
    public void init() {

    }

    @Override
    public String getSesion(String login) throws SesionJugException {
        if (login == null || login.isBlank() || login.isEmpty()) {
            String msg = "El usuario no es válido o está vacio";
            log.log(Level.WARNING, msg);
            throw new SesionJugException(msg);
        }

        Jugador jug = entityManager.find(Jugador.class, login);

        if (jug == null) {
            String msg = "Cliente no identificado : " + login + ".Imposible encontrar la sesión.";
            log.log(Level.WARNING, msg);
            throw new SesionJugException(msg);
        }

        return null;
    }

    @PreDestroy
    public void destroy() {
        log.info("EJB finalitzant...");
    }

    @Override
    public Float getPuntuacionMax() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void cerrarSesion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void unirsePartida(Partida partidaAUnirse) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void registrarJugador(Jugador jugador) {
        DAO dao = null;
        entityManager.persist(jugador);
        //boolean bool = dao.createUser(jugador);
        //return bool;
    }

    @Override
    public boolean verificarExistenciaCorreo(String correo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getName() {
        return jugador.getNickJugador();
    }

}
