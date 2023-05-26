/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import DAO.DAOEJB;
import Interfaces.LogicaInterface;
import model.Usuaris;
import javafx.collections.ObservableList;



/**
 *
 * @author carlo
 */
public class Logica implements LogicaInterface {

    
    public Logica(){
        
    }

    @Override
    public void createUser() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void findUser() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ObservableList<Usuaris> findUsers() {

//        try {
//            return daoGame.findUsers();
//        } catch (Exception e) {
//            
//        }
        return null;
    }
}
