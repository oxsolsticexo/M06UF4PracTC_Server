/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Entities.Pregunta;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import nu.xom.*;

/**
 *
 * @author Kiwi
 */
public class PreguntaLogic {

    /**
     * Buildea un archivo y retorna un Document
     *
     * @return
     */
    public Document getDocument() {

        try {
            
            File file = new File("classes/files/preguntas.xml");
            System.out.println(Files.size(file.toPath().toAbsolutePath()));
            Builder builder = new Builder();
            
            return builder.build(file);

        } catch (ParsingException | IOException ex) {
            Logger.getLogger(PreguntaLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    }

    /**
     * *
     * Recibe un documento XML y lo itera convirtiendo las diferentes etiquetas
     * a objetos.
     *
     * @return retorna un ArrayList de "Pregunta"
     */
    public ArrayList<Pregunta> xmlToArrayList(Document document) {
        
        //Document document = getDocument();

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

    private Document createXMLDocument(InputStream inputStream) throws ParsingException, ValidityException, IOException {
        Builder builder = new Builder();
        Document doc = builder.build(inputStream);
        return doc;
    }

}
