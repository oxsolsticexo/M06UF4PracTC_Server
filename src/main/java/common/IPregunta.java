/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Kiwi
 */
@Remote
public interface IPregunta {

    public List<Pregunta> getPreguntasBBDD(Partida p);

}
