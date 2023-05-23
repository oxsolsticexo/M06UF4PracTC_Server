/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Interfaces.DAOInterface;
import model.Usuaris;
//import singleton.SQLConnection;
//import java.sql.*;
import javafx.collections.*;

/**
 *
 * @author carlo
 */
public class DAO implements DAOInterface {
    

    //Connection connection = SQLConnection.getInstance();
    
    public DAO(){
        
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
    public void updateUser() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteUser() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void createUsers() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ObservableList<Usuaris> findUsers() {
        /*try {
            ObservableList result = FXCollections.observableArrayList();
            String query = "SELECT * FROM usuarios ORDER BY puntuacion DESC;";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                int id_usuario = rs.getInt("idusuario");
                String nick = rs.getString("nick");
                int puntuacion = rs.getInt("puntuacion");
                int puntuacionPartida = rs.getInt("puntuacion_partida");
                
                result.add(new Usuaris(id_usuario,nick,puntuacion,puntuacionPartida));
            }
            
            return result;
        } catch (Exception e) {
        }
        */return null;
    }

    @Override
    public void updateUsers() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteUsers() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
