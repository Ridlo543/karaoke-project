/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ysaac
 */
public class Conexion {
    
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
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeWriteConexion(ObjectOutputStream c){
        
        try {
            WriteConexion.close();
            c.close();
        } catch (IOException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
