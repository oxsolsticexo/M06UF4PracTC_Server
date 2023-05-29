/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Logica.Interfaces;

import javax.ejb.Remote;

/**
 *
 * @author carlo
 */
@Remote
public interface IHallOfFame {
    
    
    public String getUsers();
    
}
