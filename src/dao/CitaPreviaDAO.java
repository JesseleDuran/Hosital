/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import models.CitaPrevia;

/**
 *
 * @author Mota
 */
public class CitaPreviaDAO extends AdapterDB4O<CitaPrevia> 
{
    private static CitaPreviaDAO instance;

    private CitaPreviaDAO()
    {
        super();
    }

    public static CitaPreviaDAO getInstance()
    {
        if(instance == null)
            instance = new CitaPreviaDAO();
        return instance;
    }

}
