/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Interfaces;

import Entities.Partida;
import Entities.Pregunta;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Kiwi
 */
@Remote
public interface IDAOPregunta {

    public List<Pregunta> getPreguntasBBDD(Partida partida);
}
