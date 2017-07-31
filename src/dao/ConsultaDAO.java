/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import models.Consulta;

/**
 *
 * @author Mota
 */
public class ConsultaDAO extends AdapterDB4O<Consulta>
{
    private static ConsultaDAO instance;

    private ConsultaDAO()
    {
        super();
    }

    public static ConsultaDAO getInstance()
    {
        if(instance == null)
            instance = new ConsultaDAO();
        return instance;
    }

}
