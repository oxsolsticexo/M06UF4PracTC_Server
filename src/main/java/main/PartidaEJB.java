/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import common.IPartida;
import common.Partida;
import common.Pregunta;
import java.util.List;
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

    @Resource
    private SessionContext sessionContext;

    @Resource
    private EJBContext ejbContext;

    @PersistenceContext(unitName = "TrivialPersistenceUnit")
    private EntityManager entityManager;

    @Override
    public List<Pregunta> asignaPreguntas(Partida p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
