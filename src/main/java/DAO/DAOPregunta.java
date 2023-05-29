/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entities.Partida;
import Entities.Pregunta;
import Logica.Interfaces.IDAOPregunta;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kiwi
 */
@Stateful
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class DAOPregunta implements IDAOPregunta {

    @PersistenceContext(unitName = "TrivialPersistenceUnit")
    private EntityManager entityManager;

    @Override
    public List<Pregunta> getPreguntasBBDD(Partida partida) {

        List<Pregunta> preguntasList = entityManager
                .createQuery("SELECT p FROM Pregunta p WHERE p.dificultad = :dificultad", Pregunta.class)
                .setParameter("dificultad", partida.getDificultad())
                .getResultList();

        Collections.shuffle(preguntasList);

        List<Pregunta> preguntas = new ArrayList<>(preguntasList.subList(0, 10));

        return preguntas;
    }

}
