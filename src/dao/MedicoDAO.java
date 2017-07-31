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
import models.Medico;
import models.Paciente;

/**
 *
 * @author Mota
 */
public class MedicoDAO extends AdapterDB4O<Medico> {

    private static MedicoDAO instance;

    private MedicoDAO() {
        super();
    }

    public static MedicoDAO getInstance() {
        if (instance == null) {
            instance = new MedicoDAO();
        }
        return instance;
    }

    public void mostrarTodos(DB4OConnection db4o, DefaultTableModel model, JTable tabla) {
        db4o.open();

        Medico medico = new Medico(null, null, null, null, null, false);
        ObjectSet result = db4o.find(medico);
        System.out.println(result);
        Object[] filas = new Object[4];

        while (result.hasNext()) {
            medico = (Medico) result.next();
            for (int i = 0; i < 4; i++) {
                filas[0] = medico.getNombre();
                filas[1] = medico.getApellido();
                filas[2] = medico.getEspecialidad();
                filas[3] = medico.getLicencia();
            }
            model.addRow(filas);
        }
        tabla.setModel(model);
        tabla.updateUI();
        db4o.close();

    }

    public Object[][] dataMatrix(DB4OConnection db4o) {
        db4o.open();

        Medico medico = new Medico(null, null, null, null, null, false);
        ObjectSet result = db4o.find(medico);
        System.out.println(result);
        Object[][] data = new Object[result.size()][4];
        int j = 0;
        while (result.hasNext()) {

            medico = (Medico) result.next();
            for (int i = 0; i < 4; i++) {
                data[j][0] = medico.getNombre();
                data[j][1] = medico.getApellido();
                data[j][2] = medico.getEspecialidad();
                data[j][3] = medico.getLicencia();
            }
            j++;
        }

        db4o.close();
        return data;
    }

}
