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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Kiwi
 */
@Entity()
@Table(name = "Jugador")
public class Jugador implements Serializable {

    private static final long serialVersionUID = 1L;

//    @NotNull(message = "Debes introducir una direcci�n de correo electr�nico.")
//    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "La direcci�n de correo electr�nica introducida no es v�lida.")
    @Id
    @Column(name = "email", nullable = false, unique = true)
    private String email;

//    @NotNull(message = "Debes introducir un Nickname")
//    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Solo puedes introducir letras y n�meros.")
    @Column(name = "nickJugador", nullable = false, unique = true)
    private String nickJugador;
    @Column(name = "maxPuntuacionPartida")
    private Integer maxPuntuacionPartida;
    @Column(name = "puntuacionTotal")
    private Integer puntuacionTotal;
    @ManyToOne
    @JoinColumn(name = "idPartida")
    private Partida partida;

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
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

    public void setMaxPuntuacionPartida(Integer maxPuntuacionPartida) {
        this.maxPuntuacionPartida = maxPuntuacionPartida;
    }

    public Integer getPuntuacionTotal() {
        return puntuacionTotal;
    }

    public void setPuntuacionTotal(Integer puntuacionTotal) {
        this.puntuacionTotal = puntuacionTotal;
    }

    @Override
    public Jugador clone() {
        Jugador jug = new Jugador();
        jug.setEmail(this.getEmail());
        jug.setNickJugador(this.nickJugador);
        if(jug.getPuntuacionTotal() != null){
            jug.setMaxPuntuacionPartida(this.maxPuntuacionPartida);
        }
        if(jug.getPartida() != null){
            jug.setPartida(this.partida);
        }
        if(jug.getMaxPuntuacionPartida() != null){
            jug.setMaxPuntuacionPartida(this.maxPuntuacionPartida);
        }
        return jug;
    }
}