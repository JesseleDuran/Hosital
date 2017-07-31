/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import models.Tratamiento;

/**
 *
 * @author Mota
 */
public class TratamientoDAO extends AdapterDB4O<Tratamiento>
{
    private static TratamientoDAO instance;

    private TratamientoDAO()
    {
        super();
    }

    public static TratamientoDAO getInstance()
    {
        if(instance == null)
            instance = new TratamientoDAO();
        return instance;
    }

}
