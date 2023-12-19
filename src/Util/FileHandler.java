package Util;

import Model.TransaksiModel;
import Model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private static final String USER_DATA_PATH = "user_data.json";
    private static final String TRANSAKSI_PATH = "transaksi.json";

    
    ////////////////////////////////////// PROTOTYPE
    public static void createFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new IOException("Failed to create file");
            }
        }
    }

    public static <T> T readData(String filePath, Class<T> clazz) throws IOException {
        try (FileReader reader = new FileReader(filePath)) {
            return new Gson().fromJson(reader, clazz);
        } catch (IOException e) {
            throw new IOException("Failed to read data from file", e);
        }
    }
    
    public static <T> T readData(String filePath, Type clazz) throws IOException {
        try (FileReader reader = new FileReader(filePath)) {
            return new Gson().fromJson(reader, clazz);
        } catch (IOException e) {
            throw new IOException("Failed to read data from file", e);
        }
    }

    public static <T> void writeData(String filePath, T data) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            new Gson().toJson(data, writer);
        } catch (IOException e) {
            throw new IOException("Failed to write data to file", e);
        }
    }
    
    //////////////////////////////////////////// method calling 

    // Metode khusus untuk user_data.json
    public static void createUserFile() throws IOException {
        createFile(USER_DATA_PATH);
    }

    public static List<User> readUser() throws IOException {
        return readData(USER_DATA_PATH, new TypeToken<List<User>>() {
        }.getType());
    }

    public static void writeUser(List<User> data) throws IOException {
        writeData(USER_DATA_PATH, data);
    }

    // Metode khusus untuk transaksi.json
    public static void createTransaksiFile() throws IOException {
        createFile(TRANSAKSI_PATH);
    }

    public static List<TransaksiModel> readTransaksiList() throws IOException {
        return readData(TRANSAKSI_PATH, new TypeToken<List<TransaksiModel>>() {
        }.getType());
    }

    public static void writeTransaksiList(List<TransaksiModel> data) throws IOException {
        writeData(TRANSAKSI_PATH, data);
    }

//    public static TransaksiModel readTransaksi() throws IOException {
//        return readData(TRANSAKSI_PATH, TransaksiModel.class);
//    }
//
//    public static void writeTransaksi(TransaksiModel data) throws IOException {
//        writeData(TRANSAKSI_PATH, data);
//    }
//    // Metode untuk membaca seluruh transaksi dari file JSON
//    public static List<TransaksiModel> readAllTransaksi() throws IOException {
//        try (FileReader reader = new FileReader(TRANSAKSI_PATH)) {
//            Type type = new TypeToken<List<TransaksiModel>>() {}.getType();
//            return new Gson().fromJson(reader, type);
//        } catch (IOException e) {
//            throw new IOException("Failed to read transaksi data from file", e);
//        }
//    }
//
//    // Metode untuk menambahkan transaksi ke dalam file JSON
//    public static void addTransaksi(TransaksiModel transaksi) throws IOException {
//        List<TransaksiModel> transaksiList = readAllTransaksi();
//
//        if (transaksiList == null) {
//            transaksiList = new ArrayList<>();
//        }
//
//        transaksiList.add(transaksi);
//
//        try (FileWriter writer = new FileWriter(TRANSAKSI_PATH)) {
//            new Gson().toJson(transaksiList, writer);
//        } catch (IOException e) {
//            throw new IOException("Failed to write transaksi data to file", e);
//        }
//    }
}
