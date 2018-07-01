package com.example.hitman1337x.journalapp.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Date;
import java.util.function.ToDoubleBiFunction;

@Entity(tableName = "diary")
public class DiaryEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title, description;
    private Date createdOn;

    @Ignore
    public DiaryEntry (String title, String description, Date createdOn)
    {
        this.title = title;
        this.description = description;
        this.createdOn = createdOn;

    }

    public DiaryEntry (int id, String title, String description, Date createdOn)
    {
        this.title = title;
        this.description = description;
        this.createdOn = createdOn;

    }

    public void setId(int id) { this.id = id;}

    public int getId(){return id;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

}