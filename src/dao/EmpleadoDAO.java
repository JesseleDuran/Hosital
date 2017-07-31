/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.db4o.ObjectSet;
import connections.DB4OConnection;
import models.Empleado;
import models.Paciente;

/**
 *
 * @author Mota
 */
public class EmpleadoDAO extends AdapterDB4O<Empleado>
{
    private static EmpleadoDAO instance;

    private EmpleadoDAO()
    {
        super();
    }

    public static EmpleadoDAO getInstance()
    {
        if(instance == null)
            instance = new EmpleadoDAO();
        return instance;
    }

    public Object[][] dataMatrix(DB4OConnection db4o) {
        db4o.open();

        Empleado empleado = new Empleado(null, null, null);
        ObjectSet result = db4o.find(empleado);
        System.out.println(result);
        Object[][] data = new Object[result.size()][2];
        int j = 0;
        while (result.hasNext()) {

            empleado = (Empleado) result.next();
            for (int i = 0; i < 2; i++) {
                data[j][0] = empleado.getNombre();
                data[j][1] = empleado.getCedula();
            }
            j++;
        }

        db4o.close();
        return data;
    }
}
