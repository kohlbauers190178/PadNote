package net.htlgkr.kohlbauers190178.padnote;

import android.content.Context;

import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JsonManager {


    /**
     * @param object the object which gets written to the file
     */
    public static void writeToJson(Context context, JSONObject object) {

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = context.openFileOutput("note.json", Context.MODE_PRIVATE);
            fileOutputStream.write(object.toString().getBytes(StandardCharsets.UTF_8));
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the JSON-string that we can use for the JSONObject
     */
    public static String readFromJson(Context context) {
        FileInputStream inputStream = null;
        try {
            inputStream = context.openFileInput("note.json");
            int size = inputStream.available();

            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            return new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
