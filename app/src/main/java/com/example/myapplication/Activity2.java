package com.example.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.myapplication.databinding.Activity2Binding;

public class Activity2 extends AppCompatActivity {
    public static Activity2 instance;
    public static  SQLiteDatabase db;
    private AppBarConfiguration appBarConfiguration;
    private Activity2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = getBaseContext().openOrCreateDatabase("app.db", MODE_ENABLE_WRITE_AHEAD_LOGGING, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, surename TEXT);");
        instance = this;

        binding = Activity2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_2);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public static void InsertUser(String name, String surename)
    {
        instance._InsertUser(name, surename);
    }
    public void _InsertUser(String name, String surename)
    {
        db = getBaseContext().openOrCreateDatabase("app.db", MODE_ENABLE_WRITE_AHEAD_LOGGING, null);
        db.execSQL("INSERT OR IGNORE INTO users (name, surename) VALUES ('" + name + "', '" + surename + "')");
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_2);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}