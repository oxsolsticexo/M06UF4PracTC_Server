/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;


import Interfaces.DAOInterface;
import common.ISessionManagerEJB;
import common.Jugador;
import common.Lookups;
import common.Partida;
import common.Sesion;
import common.SesionJugException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.NamingException;

/**
 *
 * @author dolorioUser
 */
@Startup
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@TransactionManagement(value = TransactionManagementType.BEAN)
public class SessionManagerEJB implements ISessionManagerEJB {

    private final List<Sesion> sessions = new ArrayList();

    private static final Logger log = Logger.getLogger(SessionManagerEJB.class.getName());

    @Override
    public String getSesion(String login) throws SesionJugException {
//        if (login == null || login.isBlank() || login.isEmpty()) {
//            String msg = "El usuario no es válido o está vacio";
//            log.log(Level.WARNING, msg);
//            throw new SesionJugException(msg);
//        }
//
//        Jugador jug = entityManager.find(Jugador.class, login);
//
//        if (jug == null) {
//            String msg = "Cliente no identificado : " + login + ".Imposible encontrar la sesión.";
//            log.log(Level.WARNING, msg);
//            throw new SesionJugException(msg);
//        }
//
//        return null;
        String msg = " ";
        return msg;
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
    public void registrarJugador(String nombre, String correo) throws NamingException {
        DAOInterface dao = Lookups.DAOEJBLocalLookup();
        Jugador jug = new Jugador();
        jug.setEmail(correo);
        jug.setNickJugador(nombre);

        dao.createUser(jug);
    }

    @Override
    public boolean verificarExistenciaCorreo(String correo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
