/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author LENOVO
 */
public class FileHandler {

    private static final String FILE_PATH = "user_data.json";

    // Metode untuk membuat file jika tidak ada
    public static void createFile() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new IOException("Failed to create file");
            }
        }
    }

    public static <T> T readData(Class<T> clazz) throws IOException {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            return new Gson().fromJson(reader, clazz);
        } catch (IOException e) {
            throw new IOException("Failed to read data from file", e);
        }
    }

    public static <T> void writeData(T data) throws IOException {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            new Gson().toJson(data, writer);
        } catch (IOException e) {
            throw new IOException("Failed to write data to file", e);
        }
    }
}
