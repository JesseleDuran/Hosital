/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Mota
 */
public class Empleado {
    private String nombre; 
    private String cedula;
    private String password;

    public Empleado(String nombre, String cedula, String password) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.password = password;
    }
    
    public Empleado()
    {
        this.nombre = "";
        this.cedula = "";
        this.password = "";
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Empleado{" + "nombre=" + nombre + ", cedula=" + cedula + ", password=" + password + '}';
    }
    
    
    
}
