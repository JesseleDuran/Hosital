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
public class CitaPrevia {
    private String id;
    private String fecha;
    private String hora;
    private String id_medico;
    private String id_paciente;

    public CitaPrevia(String fecha, String hora, String id_medico, String id_paciente) {
        this.id = UUID.randomUUID().toString();
        this.fecha = fecha;
        this.hora = hora;
        this.id_medico = id_medico;
        this.id_paciente = id_paciente;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getId_medico() {
        return id_medico;
    }

    public void setId_medico(String id_medico) {
        this.id_medico = id_medico;
    }

    public String getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(String id_paciente) {
        this.id_paciente = id_paciente;
    }

    @Override
    public String toString() {
        return "CitaPrevia{" + "id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", id_medico=" + id_medico + ", id_paciente=" + id_paciente + '}';
    }
    
    
    
}
