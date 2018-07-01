package com.example.hitman1337x.journalapp.database;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import java.util.Date;



@Entity(tableName = "user")
public class DiaryUser {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name, email;
    private Date createdOn;

    @Ignore
    public DiaryUser(String name, String email, Date createdOn) {
        this.name = name;
        this.email = email;
        this.createdOn = createdOn;

    }

    public DiaryUser(int id, String name, String email, Date createdOn) {
        this.name = name;
        this.email = email;
        this.createdOn = createdOn;

    }

}
