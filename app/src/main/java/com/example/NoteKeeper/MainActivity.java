package com.example.NoteKeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView currentDate = findViewById(R.id.dateView);
        EditText currentNote = findViewById(R.id.editTextTextMultiLine);

        long date = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss Z");
        String dateString = simpleDateFormat.format(date);
        currentDate.setText(dateString.substring(0,10));

        Chip viewButton, insertButton;
        viewButton = (Chip) findViewById(R.id.chip5);
        insertButton = (Chip) findViewById(R.id.chip4);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAllNotes();
            }
        });

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (currentNote.length() == 0){
                        Toast.makeText(MainActivity.this, "Please write a note", Toast.LENGTH_LONG).show();
                    }else {
                        NoteModel note = new NoteModel(dateString, currentNote.getText().toString());
                        DBhelper database = new DBhelper(MainActivity.this);
                        database.onInsert(note);
                        Toast.makeText(MainActivity.this, "Note added successfully!", Toast.LENGTH_LONG).show();
                    }

                }
                catch (Exception e){
                    System.out.println(""+e);
                }

            }
        });

    }

    public void openAllNotes(){
        Intent intent = new Intent(this, TheListView.class);
        startActivity(intent);
    }

}