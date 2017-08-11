package com.ptit.trongthien.vnexpress_v1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by TrongThien on 7/21/2017.
 */

public class MyDatabase {
    public static final String PATH = Environment.getDataDirectory() + "/data/com.ptit.trongthien.vnexpress/databases/vnexpress.sqlite";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String ID = "id";
    public static final String TABLE_NAME = "tblAccount";

    private Context context;
    protected SQLiteDatabase database;

    public MyDatabase(Context context) {
        this.context = context;
        try {
            copyData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void openData() {
        database = context.openOrCreateDatabase(PATH, context.MODE_PRIVATE, null);
    }

    protected void closeData() {
        database.close();
    }

    private void copyData() throws IOException {
        File file = new File(PATH);
        if (!file.exists()) {
            File parent = file.getParentFile();
            parent.mkdirs();
            file.createNewFile();
            InputStream inputStream = context.getAssets().open("vnexpress.sqlite");
            FileOutputStream outputStream = new FileOutputStream(file);
            byte[] b = new byte[1024];
            int count = inputStream.read(b);
            while (count != -1) {
                outputStream.write(b, 0, count);
                count = inputStream.read(b);
            }
            outputStream.close();
            inputStream.close();
        }
    }
}
