package com.example.mayank;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


//A Repository manages queries and allows you to use multiple backends.
//it implements the logic for deciding whether to fetch data from a network
// or use results cached in a local database.
public class NoteRepository {

    // defining executors in place of Async
    ExecutorService executors= Executors.newSingleThreadExecutor();


    //we have made the variable of Notedao becz it is use to interact with the database
    private NoteDao noteDao;

    //we have made the variable of livedata also becz it will provide all the notes
    //by the method getallnotes()
    private LiveData<List<Note>> notes;


    // we have made the repo const
    //So first of all declare notedatabase and noteDao here
    //Then we have to get data of noteDao indirectly from notedatabase thats why we have
    // created the abstract method of notedao in database.
    //so to do all these we will create notedatabase object
    // inside the noteRepository constructor.

    public NoteRepository(Application application)
    {

        // in this we have pass the getdatabse becz in notedatabase we have created this
        // method
        NoteDatabase noteDatabase=NoteDatabase.getInstance(application);

        //in this we have indirectly access the data of notedao with the help of database

        noteDao=noteDatabase.noteDao();

        // in notedao we have created the method getallnotes from which we will get all notes
        // thats why we have use this here
        notes=noteDao.getAllNotes();
    }

    // this is the method that we will use to insert the data in the database
    public void insert(Note note)
    {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.insert(note);
            }
        });
    }
    public void delete(Note note)
    {

        executors.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.delete(note);
            }
        });
    }
    public void update(Note note)
    {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.update(note);
            }
        });
    }
    public LiveData<List<Note>>getAllNotes()
    {
        return notes;
    }

}
