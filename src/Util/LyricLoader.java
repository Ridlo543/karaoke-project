/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

/**
 *
 * @author LENOVO
 */
import Model.Lyric;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LyricLoader {
    public static Map<String, Lyric> loadLyrics(String jsonFilePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> jsonData = objectMapper.readValue(new File(jsonFilePath), Map.class);

            Map<String, Lyric> lyricsMap = new HashMap<>();

            // Iterasi melalui data JSON dan buat objek Lyric
            for (Map.Entry<String, Object> entry : jsonData.entrySet()) {
                String title = entry.getKey();
                String content = (String) entry.getValue();

                Lyric lyric = new Lyric(title, content);
                lyricsMap.put(title, lyric);
            }

            return lyricsMap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

