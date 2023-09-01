package com.example.mayank;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    //he selected on conflict strategy ignores
    // a new student if it's exactly the same as one already on the list

    @Insert
    public void insert(Note note);
    @Delete
    public void delete(Note note);
    @Update
    public void update(Note note);

    @Query("select * from note_table order by id asc")
    public LiveData<List<Note>> getAllNotes();
}
