/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Logica.Interfaces.DAOInterface;
import Entities.Jugador;
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
import main.Validadors;

/**
 * Data Access object utilizado para la creación y busqueda de jugadores,
 * también podemos validar y persistir con 
 * 
 * @author carlo
 */
@Stateful
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class DAOEJB implements DAOInterface {

    private static final Logger log = Logger.getLogger(DAOEJB.class.getName());

    @PersistenceContext(unitName = "TrivialPersistenceUnit")
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
     * Comprueba si un usuario está en la base de datos y lo devuelve, para ello
     * recibe un email por parametro
     *
     * @param email
     * @return
     */
    @Override
    @Lock(LockType.READ)
    public Jugador findUser(String email) {
        Jugador jugFind = null;
        String msg;
        //en caso de que el email que llega por parametro sea nulo 
        //devolveremos la variable jugFind como null dando así un caso fallido
        if (email == null) {
            msg = "ERROR: El email es nulo";
            log.log(Level.SEVERE, msg);
            return jugFind;
        }
        //creamos una lista de Jugadores y le asignamos la busqueda de los jugadores
        //que tengan el mismo email que el email que nos llega por parametro 
        List<Jugador> listaJug = em.createQuery("SELECT j FROM Jugador j WHERE j.email = :emailDAO", Jugador.class)
                .setParameter("emailDAO", email)
                .getResultList();
        //en caso de que la lista no sea nula y no esté vacía clonamos el jugador en la posición 0
        if (listaJug != null && !listaJug.isEmpty()) {
            jugFind = listaJug.get(0).clone();
        } else {
            msg = "ERROR: No se encuentra al jugador ";
            log.log(Level.SEVERE, msg);
            return jugFind;
        }

        return jugFind;
    }

    /**
     * Método que recibe un objeto lo valida haciendo uso de la clase validadors
     * y en caso de ser valido se persiste en la BD
     *
     * @param ob
     */
    @Lock(LockType.WRITE)
    @Override
    public void validPersist(Object ob) {
        List<String> errors = Validadors.validaBean(ob);
        //si no hay errores persistimos 
        if (errors == null || errors.isEmpty()) {
            log.log(Level.FINE, "!!!--- PERSISTIENDO al jugador ---!!!");
            em.persist(ob);
        } else {
            String msg = "Errores de la validación : " + errors.toString();
            log.log(Level.SEVERE, msg);
        }
    }

}
