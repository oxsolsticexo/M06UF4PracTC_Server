/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import nu.xom.Document;

/**
 *
 * @author Kiwi
 */
@Remote
public interface IPregunta {

    public List<Pregunta> getPreguntasBBDD(Partida p);

    public String readFile();
    
    public void setPreguntasBBDD(List<Pregunta> preguntas);

}
