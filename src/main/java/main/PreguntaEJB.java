/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import Logica.PreguntaLogic;
import common.IPregunta;
import common.Partida;
import common.Pregunta;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.ParsingException;

/**
 *
 * @author Kiwi
 */
@Stateful
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@TransactionManagement(value = TransactionManagementType.BEAN)
public class PreguntaEJB implements IPregunta {

    @PersistenceContext(unitName = "TrivialPersistenceUnit")
    private EntityManager entityManager;

    private Document doc;

    @Inject
    private UserTransaction userTransaction;

    @Inject
    private AppSingletonEJB singletonEJB;

    private static final Logger log = Logger.getLogger(PreguntaEJB.class.getName());

    @Override
    public List<Pregunta> getPreguntasBBDD(Partida partida) {

        List<Pregunta> preguntasList = entityManager
                .createQuery("SELECT p FROM Pregunta p WHERE p.dificultad = :dificultad", Pregunta.class)
                .setParameter("dificultad", partida.getDificultad())
                .getResultList();

        return preguntasList;
    }

    @Override
    public void setPreguntasBBDD(List<Pregunta> preguntasList) {

        try {
            userTransaction.begin();
            for (Pregunta p : preguntasList) {

                entityManager.merge(p);

            }
            userTransaction.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            log.log(Level.SEVERE, "Error a la hora de persistir las preguntas.");
            
        }
    }

    @Override
    public String readFile() {
        return singletonEJB.getDocument();
    }
}
