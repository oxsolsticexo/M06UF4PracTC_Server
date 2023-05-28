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

    public int startTimer() {
        tiempoRestante = TIEMPO_TOTAL;
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                tiempoRestante--;
                if (tiempoRestante <= 0) {
                    timer.cancel();
                }
            }
        }, 0, 1000); // El temporizador se actualiza cada segundo (1000 ms)

        return tiempoRestante;
    }
}
