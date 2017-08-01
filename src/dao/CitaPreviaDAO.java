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

    public void mostrarTodos(DB4OConnection db4o, DefaultTableModel model, JTable tabla, String licencia) {
        db4o.open();

        CitaPrevia cita = new CitaPrevia(null, null, null, null);
        ObjectSet result = db4o.find(cita);
        System.out.println(result);
        Object[] data = new Object[3];

        while (result.hasNext()) {

            cita = (CitaPrevia) result.next();
            for (int i = 0; i < 3; i++) {
                data[0] = cita.getFecha();
                data[1] = cita.getHora();
                data[2] = cita.getId_paciente();
            }
            model.addRow(data);
        }
        
        data[0]="";
        data[1]="";
        data[2]="345";
        model.addRow(data);
        tabla.setModel(model);
        tabla.updateUI();
        db4o.close();
    }

}
