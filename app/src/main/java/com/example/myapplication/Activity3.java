package com.example.myapplication;

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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        ListView list = findViewById(R.id.list);;
        String[] vm = new String[10];

        EditText text = findViewById(R.id.editTextTextPersonName);

        for (int i = 0; i < 10; i++)
        {
            vm[i] = "";
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(Activity3.this,
                    android.R.layout.simple_list_item_1, vm);

            list.setAdapter(adapter);
        }



        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 10; i++)
                {
                    if (vm[i] == "")
                    {
                        vm[i] = text.getText().toString();
                        break;
                    }
                }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(Activity3.this,
                        android.R.layout.simple_list_item_1, vm);

            list.setAdapter(adapter);
            }

        });
        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 9; i >= 0; i--) {
                    if (vm[i] != "") {
                        vm[i] = "";
                        break;
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Activity3.this,
                        android.R.layout.simple_list_item_1, vm);

                list.setAdapter(adapter);
            }

        });
        button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (vm[0] == "")
                {
                    FileInputStream fis = null;
                    try {
                        fis = openFileInput("data.txt");
                        byte[] bytes = new byte[fis.available()];
                        fis.read(bytes);
                        String str = new String (bytes);
                        String[] strings = str.split(" ; ");
                        for (int i = 0; i < strings.length; i ++)
                        {
                            vm[i] = strings[i];
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Activity3.this,
                                android.R.layout.simple_list_item_1, vm);

                        list.setAdapter(adapter);
                    } catch(IOException ex) {

                    }
                }
                else
                {
                    FileOutputStream fos = null;
                    try {
                        fos = openFileOutput("data.txt", MODE_PRIVATE);
                        for (int i = 0; i < 10; i++)
                        {
                            if (vm[i] != "") {
                                fos.write(vm[i].getBytes());
                                fos.write(" ; ".getBytes());
                            }
                        }
                        FileInputStream fis = null;
                        fis = openFileInput("data.txt");
                        byte[] bytes = new byte[fis.available()];
                        fis.read(bytes);
                        Toast.makeText(Activity3.this, new String (bytes),Toast.LENGTH_LONG).show();
                    }
                    catch(IOException ex) {

                    }
                }
            }
        });
    }
}