package com.example.experiment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class TheListView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.the_list_view);
        ListView listOfNotes = (ListView) findViewById(R.id.listViewNotes);
        DBhelper theHelper = new DBhelper(TheListView.this);
        SimpleCursorAdapter simpleCursorAdapter = theHelper.populateListViewFromDB();
        listOfNotes.setAdapter(simpleCursorAdapter);

        listOfNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor cursor = (Cursor) simpleCursorAdapter.getItem(i);
//                String date = cursor.getString(1);
                String note = cursor.getString(2);
                Toast.makeText(TheListView.this, "Note"+(i+1)+"was clicked!", Toast.LENGTH_LONG).show();
            }
        });
    }

}