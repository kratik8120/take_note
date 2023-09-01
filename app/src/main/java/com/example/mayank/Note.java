package com.example.mayank;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// in this we basically define the table name
@Entity(tableName = "note_table")
public class Note {


    // by this we have tell that the id is the primary key
    // autogenerate means its value will be get incremented when we move to the next clmn
    @PrimaryKey(autoGenerate = true)
    public int id;

    // if we want to add the clmn name we can use
    //  @ColumnInfo (name="clmn name")
    public String title;

    public String description;

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
