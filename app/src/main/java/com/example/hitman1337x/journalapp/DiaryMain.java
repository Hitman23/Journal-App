package com.example.hitman1337x.journalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.hitman1337x.journalapp.database.AppDatabase;
import com.example.hitman1337x.journalapp.database.DiaryEntry;

import static android.widget.LinearLayout.VERTICAL;

public class DiaryMain extends AppCompatActivity implements DiaryAdapter.ItemClickListener{

    //logging
    private static final String TAG = DiaryEntry.class.getSimpleName();

    private DiaryAdapter mAdapter;

    private AppDatabase mADB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_main);


        // Set the RecyclerView to its corresponding view
        RecyclerView mRecyclerView = findViewById(R.id.recyclerViewTasks);

        // Set the layout for the RecyclerView to be a linear layout, which measures and
        // positions items within a RecyclerView into a linear list
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter and attach it to the RecyclerView
        mAdapter = new DiaryAdapter(this, this);
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), VERTICAL);
        mRecyclerView.addItemDecoration(decoration);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addEntryIntent = new Intent(DiaryMain.this, AddDiaryEntry.class);
                startActivity(addEntryIntent);
            }
        });

        mADB = AppDatabase.getInstance(getApplicationContext());
    }

    @Override
    public void onResume(){
        super.onResume();
        mAdapter.setmDiaryEntries(mADB.diaryDao().loadAllDiaryEntries());
    }


    @Override
    public void onItemClickListener(int itemId) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
