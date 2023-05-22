/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javafx.scene.control.Alert;

/**
 *
 * @author carlo
 */
public class Alerts {

    Alert alert = new Alert(Alert.AlertType.NONE);

    public Alerts() {

    }

    public void Warning(String message) {
        alert.setAlertType(Alert.AlertType.WARNING);
        alert.setContentText(message);
        alert.setHeaderText("Warning");
        alert.showAndWait();

    }

    public void Error(String message) {
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.setHeaderText("Error");
        alert.showAndWait();

    }

    public void Info(String message) {
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.setHeaderText("Information");
        alert.showAndWait();

    }
}
