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

        Cursor query = db.rawQuery("SELECT * FROM sqlite_master WHERE type='table'", null);

        Vector<String> users = new Vector<String>();
        users.addElement("db path is '" + db.getPath() + "'");
        users.addElement("db name is '" + db.getPath().split("/")[db.getPath().split("/").length - 1] + "'");

        if (query.moveToFirst()) {
            int nameIndex = query.getColumnIndex();
            
            while (!query.isAfterLast()) {
                String tableName = query.getString(query.getColumnIndex("name"));
                String comm = "SELECT count(1), rowcount FROM pragma_table_info('" + query.getString(query.getColumnIndex("name") + "'");
                Cursor subquery = db.rawQuery(comm, null);
                subquery.moveToFirst();

                users.addElement(query.getString(query.getColumnIndex("name")) + " - " +
                        subquery.getInt(0) + " - " +
                        subquery.getInt(1));

                subquery.Close();
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
