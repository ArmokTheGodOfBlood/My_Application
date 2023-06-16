package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.databinding.Activity2Binding;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ClicableListActivity extends AppCompatActivity {
    public static SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicable_list);

        db = getBaseContext().openOrCreateDatabase("app.db", MODE_ENABLE_WRITE_AHEAD_LOGGING, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS GridData  (id INTEGER PRIMARY KEY AUTOINCREMENT, Caption  TEXT, Count INTEGER , Comment  TEXT);");

        ListView listView = (ListView) findViewById(R.id.list);

        final List<String> Captions = new ArrayList<String>();
        final ArrayList<Integer> Counts = new ArrayList<Integer>();

        final ArrayList<View> Containers = new ArrayList<>();

        Cursor query = db.rawQuery("SELECT Caption,Count FROM GridData;", null);
        while(query.moveToNext()){
            Captions.add(query.getString(0 ));
            Counts.add(query.getInt(1 ));
        }

        String[] capts = new String[Captions.size()];
        Integer[] cnts =new Integer[Counts.size()];

        Captions.toArray(capts);
        Counts.toArray(cnts);

        for (int i = 0; i < Captions.size(); i++)
        {
            TextView item = new TextView(this);
            item.setText(capts[i] + "\n" + cnts[i]);
            Containers.add(item);
            listView.addView(item);
        }
    }

    public void Insert(String caption, int count, String comment)
    {
        db = getBaseContext().openOrCreateDatabase("app.db", MODE_ENABLE_WRITE_AHEAD_LOGGING, null);
        db.execSQL("INSERT OR IGNORE INTO GridData (Caption, Count, Comment) VALUES ('" + caption + "', '" + count + "', '" + comment + "' )");
    }
}