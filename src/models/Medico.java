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
public class Medico {
    private String licencia;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String password;
    private boolean especialista;

    public Medico(String licencia, String nombre, String apellido, String especialidad, String password, boolean especialista) {
        this.licencia = licencia;
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.password = password;
        this.especialista = especialista;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEspecialista() {
        return especialista;
    }

    public void setEspecialista(boolean especialista) {
        this.especialista = especialista;
    }


    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "Medico{" + "licencia=" + licencia + ", nombre=" + nombre + ", apellido=" + apellido + ", especialidad=" + especialidad + ", password=" + password + ", especialista=" + especialista + '}';
    }


 
    
    
}
