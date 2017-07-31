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
public class Tratamiento {
    private String id;
    private String id_consulta;
    private String descripcion;

    public Tratamiento(String id_consulta, String descripcion) {
        this.id = UUID.randomUUID().toString();
        this.id_consulta = id_consulta;
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(String id_consulta) {
        this.id_consulta = id_consulta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Tratamiento{" + "id=" + id + ", id_consulta=" + id_consulta + ", descripcion=" + descripcion + '}';
    }
    
    
}
