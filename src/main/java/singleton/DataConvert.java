/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singleton;

import Entities.Jugador;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

/**
 *
 * @author carlo
 */
public class DataConvert {

    public static Document StringToDocument(String xmlContent) throws ParsingException, ValidityException, IOException {
        Builder builder = new Builder();
        return builder.build(xmlContent, null);
    }

    public static ObservableList<Jugador> getJugadores(String[] listado) {
        
        ObservableList<Jugador> newList = FXCollections.observableArrayList();
        for (String line : listado) {
            String[] parts = line.split(", ");
            String email = parts[0].substring(parts[0].indexOf("=") + 1);
            String nick = parts[1].substring(parts[1].indexOf("=") + 1);
            int maximapuntuacion = Integer.parseInt(parts[2].substring(parts[2].indexOf("=") + 1));
            int puntuaciontotal = Integer.parseInt(parts[3].substring(parts[3].indexOf("=") + 1, parts[3].length() - 1));
            Jugador jugador = new Jugador();
            jugador.setEmail(email);
            jugador.setNickJugador(nick);
            jugador.setMaxPuntuacionPartida(maximapuntuacion);
            jugador.setPuntuacionTotal(puntuaciontotal);
            newList.add(jugador);
        }

        return newList;
    }

}
