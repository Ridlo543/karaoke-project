
package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Connection {
    
    File fileName=new File("playListData.txt");
    ObjectInputStream ReadConexion=null;
    ObjectOutputStream WriteConexion=null;
    FileOutputStream file;
    
    public ObjectInputStream getReadConexion(){
        

        try{
          
            if (!fileName.exists()) {

                file = new FileOutputStream("playListData.txt");
                
                return null;

            } 
           
            ReadConexion = new ObjectInputStream(new FileInputStream("playListData.txt"));

            return ReadConexion;
                
         } catch(IOException e){

                System.out.printf("error "+e);

         }
        
        return null;
    }
    
    public ObjectOutputStream getWriteConexion(){
        try{
            
           WriteConexion=new ObjectOutputStream(new FileOutputStream("playListData.txt"));

           return WriteConexion;
           //fichero.writeObject(v);
//WriteConexion.wri
           //conn.close();
        } catch(IOException e){

            System.out.printf("error "+e);
      
        }
        
        return null;
    }
    
    public void closeReadConexion(ObjectInputStream c){
        try {
            ReadConexion.close();
            c.close();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeWriteConexion(ObjectOutputStream c){
        
        try {
            WriteConexion.close();
            c.close();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
