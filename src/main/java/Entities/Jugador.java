/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Kiwi
 */
@Entity()
@Table(name = "jugador")
public class Jugador implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "nickJugador", nullable = false, unique = true)
    private String nickJugador;

    @Column(name = "maxPuntuacionPartida")
    private Float maxPuntuacionPartida;

    @Column(name = "puntuacionTotal")
    private Float puntuacionTotal;

    @OneToMany(mappedBy = "jugador")
    private List<Partida> partidaList;

    public List<Partida> getPartidaList() {
        return partidaList;
    }

    public void setPartidaList(List<Partida> partidaList) {
        this.partidaList = partidaList;
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

    public Float getMaxPuntuacionPartida() {
        return maxPuntuacionPartida;
    }

    public void setMaxPuntuacionPartida(Float maxPuntuacionPartida) {
        this.maxPuntuacionPartida = maxPuntuacionPartida;
    }

    public Float getPuntuacionTotal() {
        return puntuacionTotal;
    }

    public void setPuntuacionTotal(Float puntuacionTotal) {
        this.puntuacionTotal = puntuacionTotal;
    }

    @Override
    public Jugador clone() {
        Jugador jug = new Jugador();
        jug.setEmail(this.getEmail());
        jug.setNickJugador(this.nickJugador);
        if (jug.getPuntuacionTotal() != null) {
            jug.setMaxPuntuacionPartida(this.maxPuntuacionPartida);
        }
        if (jug.getPartidaList() != null) {
            jug.setPartidaList(this.partidaList);
        }
        if (jug.getMaxPuntuacionPartida() != null) {
            jug.setMaxPuntuacionPartida(this.maxPuntuacionPartida);
        }
        return jug;
    }
}
