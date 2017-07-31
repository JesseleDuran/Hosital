/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author Mota
 */
public class Consulta {
    private String id;
    private String diagnostico; 
    private Date fecha;
    private String id_paciente;
    private String id_medico;
    private String id_medicoRef;

    public Consulta(String diagnostico, Date fecha, String id_paciente, String id_medico, String id_medicoRef) {
        this.diagnostico = diagnostico;
        this.fecha = fecha;
        this.id_paciente = id_paciente;
        this.id_medico = id_medico;
        this.id_medicoRef = id_medicoRef;
    }

    public Consulta(String diagnostico, Date fecha, String id_paciente, String id_medico) {
        this.diagnostico = diagnostico;
        this.fecha = fecha;
        this.id_paciente = id_paciente;
        this.id_medico = id_medico;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(String id_paciente) {
        this.id_paciente = id_paciente;
    }

    public String getId_medico() {
        return id_medico;
    }

    public void setId_medico(String id_medico) {
        this.id_medico = id_medico;
    }

    public String getId_medicoRef() {
        return id_medicoRef;
    }

    public void setId_medicoRef(String id_medicoRef) {
        this.id_medicoRef = id_medicoRef;
    }

    @Override
    public String toString() {
        return "Consulta{" + "id=" + id + ", diagnostico=" + diagnostico + ", fecha=" + fecha + ", id_paciente=" + id_paciente + ", id_medico=" + id_medico + ", id_medicoRef=" + id_medicoRef + '}';
    }
    
    
    
    
}
