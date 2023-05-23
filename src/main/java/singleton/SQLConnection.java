package singleton;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author carlo
 */
public class SQLConnection {
    
   // Instancia privada estática de la conexión
    private static Connection instancia = null;
    
    // Constructor privado para prevenir instanciación externa
    private SQLConnection() {}
    
    // Método público estático para obtener la instancia de la conexión
    public static Connection getInstance() {
        if (instancia == null) {
            // Si no existe la instancia, se crea
            String url = "jdbc:mysql://localhost:3306/mi_base_de_datos";
            String usuario = "root";
            String contrasena = "1234";
            
            try {
                instancia = DriverManager.getConnection(url, usuario, contrasena);
            } catch (SQLException e) {
                System.out.println("Error al conectar con la base de datos: " + e.getMessage());
            }
        }
        
        return instancia;
    }
}
