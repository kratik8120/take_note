package com.example.mayank;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Note.class},version = 1)
// we always have to make the database abstract
//Abstraction is a process of hiding the implementation details
// and showing only functionality to the user.

public  abstract class NoteDatabase extends RoomDatabase{

    //we use instance (object of NoteDatabase) everyWhere in application
    private static NoteDatabase instance;

    //This is also mandatory for our Database class,
    // it will be able to interact with the database.
    // This method must also be abstract because Room will handle the implementations for us

    public abstract NoteDao noteDao();

    //we have make this method synchronized becz sometime there is the situation
    // in which same database is accessed by multiple users
    // and there is the chances is which two users update the same data
    // this lead to the ambiguity ,so to prevent this we have use the synchronized
    public static synchronized NoteDatabase getInstance(Context context)
    {
        if(instance==null)
        {
            // if it is null create the instance with the help of builder
            instance= Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class,
                    "note_database").fallbackToDestructiveMigration()
                    .allowMainThreadQueries().addCallback(roomCallback).build();
        }
        return instance;
    }

    // calling of the data manually
    public static RoomDatabase.Callback roomCallback=new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            NoteDao noteDao = instance.noteDao();
            ExecutorService executorService = Executors.newSingleThreadExecutor();

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    noteDao.insert(new Note("Title555", "Description1"));
                    noteDao.insert(new Note("Title2", "Description2"));
                    noteDao.insert(new Note("Title3", "Description3"));
//                    noteDao.insert(new Note("Title4", "Description4"));
//                    noteDao.insert(new Note("Title5", "Description5"));
//                    noteDao.insert(new Note("Title6", "Description6"));
                }
            });
            super.onCreate(db);
        }
    };

}
