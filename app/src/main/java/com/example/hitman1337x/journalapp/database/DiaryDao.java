package com.example.hitman1337x.journalapp.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DiaryDao {

        @Query("SELECT * FROM diary ORDER BY createdOn")
        List<DiaryEntry> loadAllDiaryEntries();

        @Insert
        void insertTask(DiaryEntry diaryEntry);

        @Update(onConflict = OnConflictStrategy.REPLACE)
        void updateTask(DiaryEntry diaryEntry);

        @Delete
        void deleteTask(DiaryEntry diaryEntry);
    }


