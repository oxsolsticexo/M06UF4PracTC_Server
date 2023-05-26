/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Interfaces.DAOInterface;
import common.Jugador;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import main.Validadors;

/**
 *
 * @author carlo
 */
@Stateful
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class DAOEJB implements DAOInterface {

    private static final Logger log = Logger.getLogger(DAOEJB.class.getName());

    @PersistenceContext(unitName = "TrivialPersistenceUnit", type = PersistenceContextType.EXTENDED)
    private EntityManager em;
    /**
     * Creamos un jugador que nos pasan por parámetro
     *
     * @param jug
     */
    @Override
    @Lock(LockType.WRITE)
    public void createUser(Jugador jug) {
        String msg;
        if (em != null) { // comprobamos que el entity manager no es null
            if (em.find(jug.getClass(), jug.getEmail()) == null) { // comprobamos que el jugador no existe en la BD
                validPersist(jug);
            } else {
                msg = "El jugador ya existe";
                log.log(Level.SEVERE, msg);
            }
        } else {
            msg = "ERROR: El entity manager es nulo";
            log.log(Level.SEVERE, msg);
        }
    }
    /**
     * Comprueba si un usuario está en la base de datos
     *
     * @param email
     * @return
     */
    @Override
    @Lock(LockType.READ)
    public Jugador findUser(String email) {
        Jugador jug = null;
        String msg;

        if (email == null) {
            msg = "ERROR: El email es nulo";
            log.log(Level.SEVERE, msg);
        }
        List<Jugador> listaJug = em.createQuery("SELECT Jugador FROM Jugador WHERE Jugador.email = :emailDAO")
                .setParameter("emailDAO", email)
                .getResultList();
        if (listaJug != null) {
            if (!listaJug.isEmpty()) { // comprobamos que la lista no esta vacia y clonamos le jugador
                jug = listaJug.get(0).clone();
            } else {
                msg = "ERROR: No se encuentra el email ";
                log.log(Level.SEVERE, msg);
            }
        } else {
            msg = "La consulta devuelve NULL";
            log.log(Level.SEVERE, msg);
        }
        
        return jug;
    }
    /**
     * Método que recibe un objeto lo valida haciendo uso de la clase validadors
     * y en caso de ser valido se persiste en la BD
     *
     * @param ob
     */
    @Lock(LockType.WRITE)
    public void validPersist(Object ob) {
        List<String> errors = Validadors.validaBean(ob);

        if (errors == null || errors.isEmpty()) { //si no hay errores persistimos 
            log.log(Level.FINE, "!!!--- PERSISTIENDO al jugador ---!!!");
            em.persist(ob);
        } else {
            String msg = "Errores de la validación : " + errors.toString();
            log.log(Level.SEVERE, msg);
        }
    }

}
