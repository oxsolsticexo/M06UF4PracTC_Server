/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Kiwi
 */
@Entity
public class Jugador implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idJugador;

    @NotNull(message = "Debes introducir una dirección de correo electrónico.")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "La dirección de correo electrónica introducida no es válida.")
    private String email;

    @NotNull(message = "Debes introducir un Nickname")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Solo puedes introducir letras y números.")
    private String nickJugador;

    private Integer maxPuntuacionPartida;

    private Integer puntuacionTotal;

    public Jugador(String email, String nickJugador, Integer maxPuntuacionPartida, Integer puntuacionTotal) {
        this.email = email;
        this.nickJugador = nickJugador;
        this.maxPuntuacionPartida = maxPuntuacionPartida;
        this.puntuacionTotal = puntuacionTotal;
    }

    public Jugador() {
    }

    public Long getIdJugador() {
        return idJugador;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickJugador() {
        return nickJugador;
    }

    public void setNickJugador(String nickJugador) {
        this.nickJugador = nickJugador;
    }

    public Integer getMaxPuntuacionPartida() {
        return maxPuntuacionPartida;
    }

    public void setMaxPuntiacionPartida(Integer maxPuntuacionPartida) {
        this.maxPuntuacionPartida = maxPuntuacionPartida;
    }

    public Integer getPuntuacionTotal() {
        return puntuacionTotal;
    }

    public void setPuntuacionTotal(Integer puntuacionTotal) {
        this.puntuacionTotal = puntuacionTotal;
    }

}
