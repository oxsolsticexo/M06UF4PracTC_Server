/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Kiwi
 */
@Entity
public class Pregunta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPregunta;

    @NotNull(message = "El valor _pregunta no puede estar vacío.")
    private String _pregunta;
    @NotNull(message = "El valor respuesta A no puede estar vacío.")
    private String respuestaA;
    @NotNull(message = "El valor respuesta B no puede estar vacío.")
    private String respuestaB;
    @NotNull(message = "El valor respuesta C no puede estar vacío.")
    private String respuestaC;
    @NotNull(message = "El valor respuesta correcta no puede estar vacío.")
    private String respuestaCorrecta;
    @NotNull(message = "El valor dificultad no puede estar vacío.")
    private String dificultad;
    @ManyToOne
    private Partida partida;

    public Pregunta(String _pregunta, String respuestaA, String respuestaB, String respuestaC, String respuestaCorrecta, String dificultad, Partida partida) {
        this._pregunta = _pregunta;
        this.respuestaA = respuestaA;
        this.respuestaB = respuestaB;
        this.respuestaC = respuestaC;
        this.respuestaCorrecta = respuestaCorrecta;
        this.dificultad = dificultad;
        this.partida = partida;
    }

    public Pregunta() {
    }

    public void setIdPregunta(Long idPregunta) {
        this.idPregunta = idPregunta;
    }

    public Long getIdPregunta() {
        return idPregunta;
    }

    public String getPregunta() {
        return _pregunta;
    }

    public void setPregunta(String _pregunta) {
        this._pregunta = _pregunta;
    }

    public String getRespuestaA() {
        return respuestaA;
    }

    public void setRespuestaA(String respuestaA) {
        this.respuestaA = respuestaA;
    }

    public String getRespuestaB() {
        return respuestaB;
    }

    public void setRespuestaB(String respuestaB) {
        this.respuestaB = respuestaB;
    }

    public String getRespuestaC() {
        return respuestaC;
    }

    public void setRespuestaC(String respuestaC) {
        this.respuestaC = respuestaC;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }
}
