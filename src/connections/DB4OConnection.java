/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connections;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;


/**
 *
 * @author Slaush
 */
public class DB4OConnection {
    
    ObjectContainer db;
    boolean isOpen;
    String dbName;
    public DB4OConnection(String dbFileName)
    {
       dbName = dbFileName;
      
    }
    
    public void open()
    {
        db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), dbName);
        isOpen = true;
    }
    
    //se guardan todos los cambios cuando se cierra la conexion!
  
    public void close()
    {
        db.commit();
        db.close();
        isOpen = true;
    }
    
    public ObjectContainer getDb()
    {
        if(isOpen)
            return db;
        else
            return null;
    }
    
    public void save(Object record) 
    {
        if(isOpen)
            db.store(record);

    }

    public void delete(Object record) 
    {
        if(isOpen)
            db.delete(record);
    }
    
    public ObjectSet find(Object record)
    {      
        ObjectSet result = db.queryByExample(record);
        return result;
    
    }
    
}
