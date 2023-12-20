package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StoreSong {

    File fileName = new File("song_data.dat");
    ObjectInputStream ReadConnection = null;
    ObjectOutputStream WriteConnection = null;

    public ObjectInputStream getReadConnection() {
        try {
            if (!fileName.exists()) {
                new FileOutputStream("song_data.dat");
                return null;
            }
            ReadConnection = new ObjectInputStream(new FileInputStream("song_data.dat"));
            return ReadConnection;

        } catch (IOException e) {
            System.out.printf("error " + e);
        }
        return null;
    }

    public ObjectOutputStream getWriteConnection() {
        try {
            WriteConnection = new ObjectOutputStream(new FileOutputStream("song_data.dat"));
            return WriteConnection;
        } catch (IOException e) {
            System.out.printf("error " + e);
        }
        return null;
    }

    public void closeReadConnection(ObjectInputStream c) {
        try {
            ReadConnection.close();
            c.close();
        } catch (IOException ex) {
            Logger.getLogger(StoreSong.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeWriteConnection(ObjectOutputStream c) {
        try {
            WriteConnection.close();
            c.close();
        } catch (IOException ex) {
            Logger.getLogger(StoreSong.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
