package com.example.mayank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddNoteActivity extends AppCompatActivity {

    EditText title;
    EditText desc;
    Button cancle;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        // if we want to show the add menu bar then
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        title=findViewById(R.id.title);
        desc=findViewById(R.id.desc);
        cancle=findViewById(R.id.button);
        save=findViewById(R.id.button2);

    }


}