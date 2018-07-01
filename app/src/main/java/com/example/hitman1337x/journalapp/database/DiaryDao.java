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
        void insertDiary(DiaryEntry diaryEntry);

        @Update(onConflict = OnConflictStrategy.REPLACE)
        void updateDiary(DiaryEntry diaryEntry);

        @Delete
        void deleteTask(DiaryEntry diaryEntry);

        @Query("SELECT * FROM diary WHERE id = :id")
        DiaryEntry loadDById(int id);
    }


