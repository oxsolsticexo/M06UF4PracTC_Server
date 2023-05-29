/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.EJB;

import Logica.Interfaces.IPregunta;
import Entities.Pregunta;
import java.util.ArrayList;
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
import nu.xom.Element;
import nu.xom.Elements;

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

    @Inject
    private UserTransaction userTransaction;

    @Inject
    private AppSingletonEJB singletonEJB;

    private static final Logger log = Logger.getLogger(PreguntaEJB.class.getName());

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

    @Override
    public ArrayList<Pregunta> xmlToArrayList(Document document) {

        //Generamos un ArrayList de "Report"
        ArrayList<Pregunta> preguntas = new ArrayList<>();

        //Obtenemos el elemento raíz del documento y lo guardamos en "root"
        Element root = document.getRootElement();

        //Obtenemos los elementos hijos de "root"
        Elements preguntaElements = root.getChildElements("pregunta");

        //Iteramos los elementos "children"
        for (int i = 0; i < preguntaElements.size(); i++) {

            Element preguntaElement = preguntaElements.get(i);

            // Obtener los valores de las etiquetas
            Long idPregunta = Long.parseLong(preguntaElement.getAttributeValue("idPregunta"));
            String pregunta = preguntaElement.getFirstChildElement("_pregunta").getValue();
            String respuestaA = preguntaElement.getFirstChildElement("respuestaA").getValue();
            String respuestaB = preguntaElement.getFirstChildElement("respuestaB").getValue();
            String respuestaC = preguntaElement.getFirstChildElement("respuestaC").getValue();
            String respuestaCorrecta = preguntaElement.getFirstChildElement("respuestaCorrecta").getValue();
            String dificultad = preguntaElement.getFirstChildElement("dificultad").getValue();

            // Crear un objeto Pregunta con los valores obtenidos
            Pregunta preguntaObj = new Pregunta();
            preguntaObj.setIdPregunta(idPregunta);
            preguntaObj.setPregunta(pregunta);
            preguntaObj.setRespuestaA(respuestaA);
            preguntaObj.setRespuestaB(respuestaB);
            preguntaObj.setRespuestaC(respuestaC);
            preguntaObj.setRespuestaCorrecta(respuestaCorrecta);
            preguntaObj.setDificultad(dificultad);

            preguntas.add(preguntaObj);
        }
        return preguntas;
    }

}
