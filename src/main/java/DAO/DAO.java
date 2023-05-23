/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Interfaces.DAOInterface;
import common.Jugador;
import model.Usuaris;
//import singleton.SQLConnection;
//import java.sql.*;
import javafx.collections.*;
import javax.jms.Session;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transaction;

/**
 *
 * @author carlo
 */
public class DAO implements DAOInterface {

    //Connection connection = SQLConnection.getInstance();
    public DAO() {

    }

    @PersistenceContext(unitName = "TrivialPersistenceUnit")
    private EntityManager entityManager;

    @Override
    public boolean createUser(String email, String usuario) {
        Jugador jugador = new Jugador();
        jugador.setEmail(email);
        jugador.setNickJugador(usuario);
        try {
            entityManager.persist(usuario);
        } catch (Exception e) {
            // Manejar el error, lanzar una excepción personalizada o realizar acciones adicionales si es necesario
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void findUser(String email) {
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
         */
        return null;
    }

}
