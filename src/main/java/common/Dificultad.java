/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

/**
 *
 * @author Kiwi
 */
public enum Dificultad {
    
    DIFICULTAD1("F�cil"),
    DIFICULTAD2("Medio"),
    DIFICULTAD3("Dif�cil");
    
    private String dificultad;
    
    private Dificultad(String dificultad){
        this.dificultad = dificultad;
    }
    
    public String getDificultad(){
        return dificultad;
    }
}
