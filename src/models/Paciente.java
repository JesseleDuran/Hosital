/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.UUID;

/**
 *
 * @author Mota
 */
public class Paciente {
    private String ci;
    private String nombre;
    private String apellido;
    private String telefono;

    public Paciente(String nombre, String apellido, String telefono, String ci) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.ci = ci;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombre() {
        if(nombre == null)
            return "";
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        if(apellido == null)
            return "";
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        if(telefono == null)
            return "";
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Paciente{  nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono + ", ci=" + ci + '}';
    }

  

}
