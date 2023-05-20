package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.myapplication.databinding.FragmentFirstBinding;
import android.database.Cursor;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static android.content.Context.MODE_PRIVATE;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

public class FirstFragment extends Fragment {

    private SQLiteDatabase db;
    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        db = Activity2.db;
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            UpdateList();
        } catch (Exception e)
        {
        }
        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = binding.editTextTextPersonName2.getText().toString();
                String name = text.split(" ")[0];
                String surename = text.split(" ")[1];
                Activity2.InsertUser(name,surename);
            }
        });
    }
    private void UpdateList()
    {

        Cursor query = db.rawQuery("SELECT * FROM users;", null);
        Vector<String> users = new Vector<String>();
        while(query.moveToNext()){
            users.addElement(query.getString(1 ) + " " + query.getString(2));
        }
        String[] userArr =  users.toArray(new String[users.size()]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, userArr);

        binding.list.setAdapter(adapter);

        query.close();
        db.close();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}