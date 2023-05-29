/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Kiwi
 */
public class TimerLogic {

    private static final int TIEMPO_TOTAL = 30;
    private int tiempoRestante;
    private final Timer timer;

    public TimerLogic() {
        timer = new Timer();
    }

    public void iniciarTemporizador() {
        tiempoRestante = TIEMPO_TOTAL;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                tiempoRestante--;
                if (tiempoRestante <= 0) {
                    // Lógica adicional cuando el temporizador llega a cero
                }
            }
        }, 0, 1000); // El temporizador se actualiza cada segundo (1000 ms)
    }

    public void detenerTemporizador() {
        timer.cancel();
    }

    public void reiniciarTemporizador() {
        iniciarTemporizador();
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }
}
