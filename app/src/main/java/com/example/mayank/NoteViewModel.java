package com.example.mayank;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {


    // now as in pic view model conatin repository
    // so in this we have to call it
    private NoteRepository noteRepository;

    // call teh live data
    private LiveData<List<Note>> notes;

    // use all these in constructor
    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository=new NoteRepository(application);
        notes= noteRepository.getAllNotes();

    }

    public void insert(Note note)
    {

        noteRepository.insert(note);
    }
    public void delete(Note note)
    {
        noteRepository.delete(note);
    }
    public void update(Note note)
    {
        noteRepository.update(note);
    }
    public LiveData<List<Note>>getAllNotes()
    {
        return notes;
    }

}
