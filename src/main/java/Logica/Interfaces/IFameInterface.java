/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Logica.Interfaces;

import Entities.Jugador;
import javafx.scene.control.TableView;
import javax.ejb.Remote;
import singleton.SerializableObject;

/**
 *
 * @author carlo
 */
@Remote
public interface IFameInterface {
    SerializableObject<Jugador> getUsers();
}
