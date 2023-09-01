package com.example.mayank;

import static com.example.mayank.R.id.top_menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.lifecycle.Observer;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // now we have to call the view model
    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
//        Menu menu=findViewById(top_menu);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView=findViewById(R.id.RecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        NoteAdapter adapter=new NoteAdapter();
        recyclerView.setAdapter(adapter);
        noteViewModel=new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                //update Recycle view
                adapter.setNoteList(notes);
            }
        });
    }

    // it is called when the menu button of the device is pressed
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //A MenuInflater is an object that is able to create Menu from xml resources
        getMenuInflater().inflate(R.menu.new_menu,menu);

        if(menu instanceof MenuBuilder)
        {
            MenuBuilder m= (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }
        return true;
    }
    // if a menu has many option then this is used

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();
        if(id==R.id.top_menu) {
//        switch (id)
//        {
//            case R.id.top_menu:
            Intent i = new Intent(MainActivity.this, AddNoteActivity.class);
            startActivity(i);
            return true;
        }
        else {
//
//            default:
            return super.onOptionsItemSelected(item);
        }
//        }

    }
}