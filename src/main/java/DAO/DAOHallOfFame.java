/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entities.Jugador;
import Logica.Interfaces.IFameInterface;
import java.util.List;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import singleton.SerializableObject;

/**
 *
 * @author carlo
 */
@Stateful
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class DAOHallOfFame implements IFameInterface {

    private static final Logger log = Logger.getLogger(DAOHallOfFame.class.getName());

    @PersistenceContext(unitName = "TrivialPersistenceUnit")
    private EntityManager em;

    public SerializableObject<Jugador> getUsers() {
        ObservableList<Jugador> lista = FXCollections.observableArrayList();
        List<Jugador> listaJug = em.createQuery("SELECT j FROM Jugador j ORDER BY j.puntuacionTotal DESC")
                .setMaxResults(5)
                .getResultList();
        
        SerializableObject<Jugador> listado = new SerializableObject<>(lista);
        return listado;
        
       
    }

}
