package com.example.myapplication;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import static android.app.PendingIntent.getActivity;

public class Activity3 extends AppCompatActivity {
    public static SQLiteDatabase db;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        db = getBaseContext().openOrCreateDatabase("app.db", MODE_ENABLE_WRITE_AHEAD_LOGGING, null);

        Cursor query = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        Vector<String> users = new Vector<String>();
        if (query.moveToFirst()) {
            while (!query.isAfterLast()) {
                @SuppressLint("Range") String tableName = query.getString(query.getColumnIndex("name"));

                users.addElement(query.getString(query.getColumnIndex("name")));

                query.moveToNext();
            }
        }
        String[] userArr =  users.toArray(new String[users.size()]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, userArr);
        ListView list = (ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        query.close();
        db.close();
    }
}