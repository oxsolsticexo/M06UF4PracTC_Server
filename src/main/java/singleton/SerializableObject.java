/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singleton;

import Entities.Jugador;
import java.io.Serializable;
import javafx.collections.ObservableList;

/**
 *
 * @author carlo
 */
public class SerializableObject<Jugador> implements Serializable {

    private ObservableList<Jugador> lista;

    public SerializableObject(ObservableList<Jugador> list) {
        this.lista = list;
    }

    public ObservableList<Jugador> getList() {
        return lista;
    }
}
