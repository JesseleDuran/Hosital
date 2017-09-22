/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.db4o.ObjectSet;
import connections.DB4OConnection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.CitaPrevia;
import models.Paciente;

/**
 *
 * @author Mota
 */
public class CitaPreviaDAO extends AdapterDB4O<CitaPrevia> {

    private static CitaPreviaDAO instance;

    private CitaPreviaDAO() {
        super();
    }

    public static CitaPreviaDAO getInstance() {
        if (instance == null) {
            instance = new CitaPreviaDAO();
        }
        return instance;
    }

    public List<CitaPrevia> mostrarTodos(DB4OConnection db4o, DefaultTableModel model, JTable tabla, String licencia) 
    {
        db4o.open();

        
        List<CitaPrevia> result = CitaPreviaDAO.getInstance().getAll(db4o);
        System.out.println(result);
        
        List<CitaPrevia> citas = new ArrayList<>();
        for (CitaPrevia cita : result) {
            if(cita.getId_medico().equals(licencia))
            {
                            Object[] data = new Object[3];
            data[0] = cita.getFecha();
            data[1] = cita.getHora();
            data[2] = cita.getId_paciente();
            citas.add(cita);
            model.addRow(data);
            }
            
        }


        
;
        tabla.setModel(model);
        tabla.updateUI();
        db4o.close();
        return citas;
    }

}
