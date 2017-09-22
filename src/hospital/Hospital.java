/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import connections.DB4OConnection;
import controlador.Control;


/**
 *
 * @author Mota
 */
public class Hospital {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DB4OConnection db = new DB4OConnection("BD.hospital");
        Control controlador = Control.getInstance();
        controlador.setDatabase(db);
        controlador.abrirVentana(1, "");
        
    }     
    
}
