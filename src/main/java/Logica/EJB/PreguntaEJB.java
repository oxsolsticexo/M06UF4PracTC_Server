/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.EJB;

import Logica.Interfaces.IPregunta;
import Entities.Partida;
import Entities.Pregunta;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import nu.xom.Document;

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

        //TODO Limitar de alguna manera a que sean 10 preguntas.
        partida.setPreguntasList(preguntasList);

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
