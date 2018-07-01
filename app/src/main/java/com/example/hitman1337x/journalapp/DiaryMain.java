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
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.hitman1337x.journalapp.database.AppDatabase;
import com.example.hitman1337x.journalapp.database.DiaryEntry;
import com.google.firebase.auth.FirebaseAuth;


import java.util.List;

import static android.widget.LinearLayout.VERTICAL;

public class DiaryMain extends AppCompatActivity implements DiaryAdapter.ItemClickListener{

    //logging
    private static final String TAG = DiaryEntry.class.getSimpleName();

    private DiaryAdapter mAdapter;

    private RecyclerView mRecyclerView;

    private AppDatabase mADB;

    private Intent intent;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        // Set the RecyclerView to its corresponding view
        mRecyclerView = findViewById(R.id.recyclerViewTasks);

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



        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            // Called when a user swipes left or right on a ViewHolder
            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                // Here is where you'll implement swipe to delete
                // COMPLETED (1) Get the diskIO Executor from the instance of AppExecutors and
                // call the diskIO execute method with a new Runnable and implement its run method
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        // COMPLETED (3) get the position from the viewHolder parameter
                        int position = viewHolder.getAdapterPosition();
                        List<DiaryEntry> entries = mAdapter.getmDiaryEntries();
                        // COMPLETED (4) Call deleteTask in the taskDao with the task at that position
                        mADB.diaryDao().deleteTask(entries.get(position));
                        // COMPLETED (6) Call retrieveTasks method to refresh the UI
                        retrieveEntries();
                    }
                });
            }
        }).attachToRecyclerView(mRecyclerView);

    }

    @Override
    public void onResume(){
        super.onResume();
        retrieveEntries();
    }

    private void retrieveEntries() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<DiaryEntry> entries = mADB.diaryDao().loadAllDiaryEntries();
                // We will be able to simplify this once we learn more
                // about Android Architecture Components
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.setmDiaryEntries(entries);
                    }
                });
            }
        });
    }


    @Override
    public void onItemClickListener(int itemId) {
        intent = new Intent(DiaryMain.this, AddDiaryEntry.class);
        intent.putExtra(AddDiaryEntry.EXTRA_DIARY_ID, itemId);
        startActivity(intent);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_signedin, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(DiaryMain.this, "This is not yet available", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.action_logout) {
            mAuth.signOut();
            intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }


}
