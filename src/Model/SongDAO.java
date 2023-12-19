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
import Util.StoreSong;


public class SongDAO {
    
    List<Song> songs=null;
    private ObjectInputStream readConexion;
    private ObjectOutputStream writeConexion;
    private StoreSong conn=null;
    
    //ObjectOutputStream
    
    public SongDAO(){
        conn = new StoreSong();
        songs = new LinkedList<>();
    }
    
    public void deleteSong(List<Song> musics, int idSong){
        
        try {
            musics.remove(idSong);
            
            writeConexion = conn.getWriteConnection();
            
            writeConexion.writeObject(musics);
            
            conn.closeWriteConnection(writeConexion);
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
            writeConexion = conn.getWriteConnection();
            writeConexion.writeObject(s);
            
            conn.closeWriteConnection(writeConexion);
        } catch (IOException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Song> getSongs(){
        readConexion = conn.getReadConnection();

        try {
            if(readConexion!=null){
                System.out.println("di songDAO, mendapatkan lagu.. terdapat lagu");
                songs = (List<Song>) readConexion.readObject();
                conn.closeReadConnection(readConexion);
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
