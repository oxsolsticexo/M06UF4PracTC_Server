/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.EJB;


import Entities.Lookups;
import Logica.Interfaces.IFameInterface;
import Logica.Interfaces.IHallOfFame;
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
 * @author carlo
 */
@Stateful
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class HallOfFameEJB implements IHallOfFame {

    private IFameInterface hallOfFame;

    @Resource
    private SessionContext sessionContext;

    @Resource
    private EJBContext ejbContext;

    @PersistenceContext(unitName = "TrivialPersistenceUnit")
    private EntityManager entityManager;

    @Override
    public String getUsers() throws Exception {
        
        try {
            hallOfFame = Lookups.IFameLookup();
            return hallOfFame.getUsers();
        } catch (Exception e) {
            throw e;
        }
    }


}
