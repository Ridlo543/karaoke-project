
package Util;

import Model.TransaksiModel;
import Model.User;
import Model.UserModel;
import View.Transaksi;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {

    private static final String USER_DATA_PATH = "user_data.json";
    private static final String TRANSAKSI_PATH = "transaksi.json";

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

    public static <T> void writeData(String filePath, T data) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            new Gson().toJson(data, writer);
        } catch (IOException e) {
            throw new IOException("Failed to write data to file", e);
        }
    }

    // Metode khusus untuk user_data.json
    public static void createUserFile() throws IOException {
        createFile(USER_DATA_PATH);
    }

    public static UserModel readUser() throws IOException {
        return readData(USER_DATA_PATH, UserModel.class);
    }

    public static void writeUser(UserModel data) throws IOException {
        writeData(USER_DATA_PATH, data);
    }

    // Metode khusus untuk transaksi.json
    public static void createTransaksiFile() throws IOException {
        createFile(TRANSAKSI_PATH);
    }

    public static TransaksiModel readTransaksi() throws IOException {
        return readData(TRANSAKSI_PATH, TransaksiModel.class);
    }

    public static void writeTransaksi(TransaksiModel data) throws IOException {
        writeData(TRANSAKSI_PATH, data);
    }
}
