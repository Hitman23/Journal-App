package com.example.hitman1337x.journalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hitman1337x.journalapp.database.AppDatabase;
import com.example.hitman1337x.journalapp.database.DiaryEntry;

import java.util.Date;

public class AddDiaryEntry extends AppCompatActivity {

    //Handle intent
    public static final String EXTRA_DIARY_ID = "extraDiaryId";
    //Handle rotation
    public static final String INSTANCE_DIARY_ID = "instanceDiaryId";

    //Constant for default diary entry id to be used when not in update mode
    private static final int DEFAULT_DIARY_ENTRY_ID = -1;
    //logging
    private static final String TAG = AddDiaryEntry.class.getSimpleName();

    TextView mEditTitle, mEditDescription;
    Button mButton;

    private int mDiaryEntryId = DEFAULT_DIARY_ENTRY_ID;


    private AppDatabase mDb;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary_entry);

        intViews();

        mDb = AppDatabase.getInstance(getApplicationContext());

        if (savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_DIARY_ID)){
            mDiaryEntryId = savedInstanceState.getInt(INSTANCE_DIARY_ID, DEFAULT_DIARY_ENTRY_ID);
        }

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_DIARY_ID))
        {
            mButton.setText("Üpdate");
            if(mDiaryEntryId == DEFAULT_DIARY_ENTRY_ID)
            {
                //TODO
                //populate ui
            }
        }
    }

//    @Override
//    protected void onSavedInstanceState(Bundle outState)
//    {
//        outState.putInt(INSTANCE_DIARY_ID, mDiaryEntryId);
//        super.onSaveInstanceState(outState);
//
//
//    }

    private void intViews() {
        mEditTitle = findViewById(R.id.diaryTitle);
        mEditDescription = findViewById(R.id.editTextDiaryDescription);
        mButton = findViewById(R.id.saveButton);

        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                onSaveButtonClicked();
            }
        });
    }

    private void populateUI(DiaryEntry diaryEntry)
    {

    }

    public void onSaveButtonClicked() {
        String title = mEditTitle.getText().toString();
        String description = mEditDescription.getText().toString();
        Date date = new Date();

        DiaryEntry taskEntry = new DiaryEntry(title, description, date, date);
        mDb.diaryDao().insertTask(taskEntry);
        finish();
    }


}
