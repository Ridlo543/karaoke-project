/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Util.Conexion;
import Model.Song;

/**
 *
 * @author ysaac
 */
public class SongDAO {
    
    List<Song> songs=null;
    private ObjectInputStream readConexion;
    private ObjectOutputStream writeConexion;
    private Conexion conn=null;
    
    //ObjectOutputStream
    
    public SongDAO(){
        conn = new Conexion();
        songs = new LinkedList<>();
    }
    
    public void deleteSong(List<Song> musics, int idSong){
        
        try {
            musics.remove(idSong);
            
            writeConexion = conn.getWriteConexion();
            
            writeConexion.writeObject(musics);
            
            conn.closeWriteConexion(writeConexion);
        } catch (IOException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteAllSongs(List<Song> musics){
        musics.removeAll(musics);
        try {
            FileOutputStream file =new FileOutputStream("playListData.dat");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Song.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertSongs(List<Song> s){
        try {
            writeConexion = conn.getWriteConexion();
            writeConexion.writeObject(s);
            
            conn.closeWriteConexion(writeConexion);
        } catch (IOException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Song> getSongs(){
        readConexion = conn.getReadConexion();
//----------------------------------------------------------- falta arreglar esto
        try {
            if(readConexion!=null){
                System.out.println("di songDAO, mendapatkan lagu.. terdapat lagu");
                songs = (List<Song>) readConexion.readObject();
                conn.closeReadConexion(readConexion);
               return songs;
            }else{
                System.out.println("di songDAO, mendapatkan lagu... tidak ada lagu");
                return null;
            }
            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
}
