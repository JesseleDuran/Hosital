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
public class Operacion {
    private String id;
    private String id_tratamiento;
    private String descripcion;

    public Operacion(String id_tratamiento, String descripcion) {
        this.id = UUID.randomUUID().toString();
        this.id_tratamiento = id_tratamiento;
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_tratamiento() {
        return id_tratamiento;
    }

    public void setId_tratamiento(String id_tratamiento) {
        this.id_tratamiento = id_tratamiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Operacion{" + "id=" + id + ", id_tratamiento=" + id_tratamiento + ", descripcion=" + descripcion + '}';
    }
    
    
    
}
