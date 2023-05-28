/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.EJB;

import Logica.Interfaces.DAOInterface;
import Entities.Jugador;
import Entities.Lookups;
import Entities.Partida;
import Entities.Sesion;
import Logica.Exceptions.SesionJugException;
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
import Logica.Interfaces.ISessionManager;
import Entities.Token;
import java.util.logging.Level;

/**
 *
 * @author dolorioUser
 */
@Startup
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@TransactionManagement(value = TransactionManagementType.BEAN)
public class SessionManagerEJB implements ISessionManager {

    private final List<Sesion> sessions = new ArrayList();

    private static final Logger log = Logger.getLogger(SessionManagerEJB.class.getName());

    @Override
    public Sesion getSesion(Token token) throws SesionJugException {
        Sesion sesh = null;

        List<Sesion> sesionesCopia = new ArrayList(sessions);

        for (Sesion s : sesionesCopia) {
            if (token.equals(s.getToken())) {
                sesh = s;
                log.log(Level.INFO, "SESION ENCONTRADA");
            }
        }

        return sesh;
    }

    @Override
    public Float getPuntuacionMax() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void cerrarSesion(Token token) {
        try {
            Sesion sesh = getSesion(token);
            if (sesh != null) {
                sessions.remove(sesh);
                log.log(Level.INFO, "Sesion cerrada");
            }
        } catch (SesionJugException ex) {
            Logger.getLogger(SessionManagerEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void unirsePartida(Partida partidaAUnirse) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Token registrarJugador(String nombre, String correo) throws NamingException {
        DAOInterface dao = Lookups.DAOEJBLocalLookup();
        Token token = null;
        Jugador jug = new Jugador();
        jug.setEmail(correo);
        jug.setNickJugador(nombre);

        dao.createUser(jug);
        
        Jugador jug1 = dao.findUser(correo);
        if (jug1.getEmail().equals(correo)) {
            token = new Token(correo);
            sessions.add(new Sesion(token, correo));
            log.log(Level.FINE, "Nueva sesion creada");
            return token;
        }
        return token;
    }

    @Override
    public boolean verificarExistenciaCorreo(String correo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Token loginJugador(String email) throws NamingException {
        DAOInterface dao = Lookups.DAOEJBLocalLookup();
        Token token = null;
        Jugador jugSes;
        List<Sesion> sessionsLog = new ArrayList(sessions);

        jugSes = dao.findUser(email);
        if (jugSes.getEmail().equals(email)) {
            // si encontramos la sesión recuperamos el token
            for (Sesion sesh : sessionsLog) {
                if (email.equals(sesh.getCorreo())) {
                    token = sesh.getToken();
                    log.log(Level.FINE, "Token recuperado");
                }
            }

            if (token == null) {// si no existe una sesión la creamos
                token = new Token(email);

                sessions.add(new Sesion(token, email));
                log.log(Level.FINE, "Nueva sesion creada");
            }
        } else {
            log.log(Level.SEVERE, "ERROR JUGADOR NO ENCONTRADO");

        }
        return token;
    }
}
