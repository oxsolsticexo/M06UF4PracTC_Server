package common;

import common.Pregunta;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Nodes;
import nu.xom.ParsingException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;

@Stateful
public class XMLProcessorImpl {
    public List<Pregunta> procesarXML() {
        List<Pregunta> preguntas = new ArrayList<>();

        try {
            File xmlFile = new File("/files/preguntas.xml");

            // Crear un objeto Builder de XOM para construir el árbol del documento XML
            Builder builder = new Builder();
            Document document = builder.build(xmlFile);

            // Obtener los elementos de las preguntas en el XML
            Nodes preguntaNodes = document.query("//pregunta");

            // Recorrer los nodos de preguntas y mapearlos a objetos Pregunta
            for (int i = 0; i < preguntaNodes.size(); i++) {
                Element preguntaElement = (Element) preguntaNodes.get(i);

                // Obtener los atributos y elementos de la pregunta
                String id = preguntaElement.getAttributeValue("respuestaA");
                String texto = preguntaElement.getAttributeValue("respuestaB");
                // Mapear otros atributos y elementos según la estructura del XML

                // Crear un objeto Pregunta y agregarlo a la lista
                //Pregunta pregunta = new Pregunta(texto, texto, texto, texto, texto, id, partida)
                //preguntas.add(pregunta);
            }
        } catch (ParsingException | IOException e) {
            e.printStackTrace();
        }

        return preguntas;
    }
}