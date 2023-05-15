/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import common.IJugador;
import java.util.Queue;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
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

    @PostConstruct
    public void init() {

    }
}
