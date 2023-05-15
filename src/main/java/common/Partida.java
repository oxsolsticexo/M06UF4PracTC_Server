/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Kiwi
 */
@Entity
public class Partida implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPartida;
    
    @NotNull (message = "Debes introducir un nombre.")
    private String nombre;
    
    @NotNull (message = "Debes seleccionar una dificultad.")
    private Dificultad dificultad;

    @OneToMany
    private List<Jugador> jugadoresList;
    
    @OneToMany
    private List<Pregunta> preguntasList;

    public Partida(String nombre, Dificultad dificultad, List<Jugador> jugadoresList, List<Pregunta> preguntasList) {
        this.nombre = nombre;
        this.dificultad = dificultad;
        this.jugadoresList = jugadoresList;
        this.preguntasList = preguntasList;
    }
    
    public Partida(){
    }

    public Long getIdPartida() {
        return idPartida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Dificultad getDificultad() {
        return dificultad;
    }

    public void setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
    }

    public List<Jugador> getJugadoresList() {
        return jugadoresList;
    }

    public void setJugadoresList(List<Jugador> jugadoresList) {
        this.jugadoresList = jugadoresList;
    }

    public List<Pregunta> getPreguntasList() {
        return preguntasList;
    }

    public void setPreguntasList(List<Pregunta> preguntasList) {
        this.preguntasList = preguntasList;
    }
}
