/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.db4o.ObjectSet;
import connections.DB4OConnection;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Paciente;

/**
 *
 * @author Mota
 */
public class PacienteDAO extends AdapterDB4O<Paciente> 
{
    private static PacienteDAO instance;

    private PacienteDAO()
    {
        super();
    }

    public static PacienteDAO getInstance()
    {
        if(instance == null)
            instance = new PacienteDAO();
        return instance;
    }

    public void mostrarTodos(DB4OConnection db4o, DefaultTableModel model, JTable tabla) {
        db4o.open();
       
        Paciente paciente = new Paciente(null,null,null,null);
        ObjectSet result = db4o.find(paciente);
        System.out.println(result);
        Object[] filas = new Object[4];
        
        while(result.hasNext())
        {
            paciente = (Paciente) result.next();
            for (int i = 0; i < 4; i++) 
            {
                filas[0]= paciente.getNombre();
                filas[1]=paciente.getApellido();
                filas[2]=paciente.getTelefono();
                filas[3]=paciente.getCi();
            }
            model.addRow(filas);
        }
        tabla.setModel(model);
        tabla.updateUI();
        db4o.close();
    }
    
    public Object[][] dataMatrix(DB4OConnection db4o) {
        db4o.open();

        Paciente paciente = new Paciente(null, null, null, null);
        ObjectSet result = db4o.find(paciente);
        System.out.println(result);
        Object[][] data = new Object[result.size()][4];
        int j = 0;
        while (result.hasNext()) {
            
            paciente = (Paciente) result.next();
            for (int i = 0; i < 4; i++) {
                data[j][0] = paciente.getNombre();
                data[j][1] = paciente.getApellido();
                data[j][2] = paciente.getTelefono();
                data[j][3] = paciente.getCi();
            }
            j++;
        }

        db4o.close();
        return data;
    }

}
