/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import model.Usuaris;
import javafx.collections.ObservableList;

/**
 *
 * @author carlo
 */
public interface LogicaInterface {

    //CRUD for 1 user
    void createUser();

    void findUser();

    void updateUser();

    void deleteUser();

    //CRUD for multiple users
    void createUsers();

    ObservableList<Usuaris> findUsers();

    void updateUsers();

    void deleteUsers();
}
