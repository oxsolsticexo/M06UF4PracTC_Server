/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @NotNull(message = "Debes introducir un nombre.")
    private String nombre;

    @NotNull(message = "Debes seleccionar una dificultad.")
    private String dificultad;

    @ManyToOne
    @JoinColumn(name = "jugador_email")
    private Jugador jugador;

    @OneToMany
    @Size(max = 10)
    private List<Pregunta> preguntasList;

    public Partida(String nombre, String dificultad, Jugador jugador, List<Pregunta> preguntasList) {
        this.nombre = nombre;
        this.dificultad = dificultad;
        this.jugador = jugador;
        this.preguntasList = preguntasList;
    }

    public Partida() {
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

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public List<Pregunta> getPreguntasList() {
        return preguntasList;
    }

    public void setPreguntasList(List<Pregunta> preguntasList) {
        this.preguntasList = preguntasList;
    }
}
