/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import models.Operacion;

/**
 *
 * @author Mota
 */
public class OperacionDAO extends AdapterDB4O<Operacion>
{
    private static OperacionDAO instance;

    private OperacionDAO()
    {
        super();
    }

    public static OperacionDAO getInstance()
    {
        if(instance == null)
            instance = new OperacionDAO();
        return instance;
    }

}
