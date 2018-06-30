package com.example.hitman1337x.journalapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hitman1337x.journalapp.database.DiaryEntry;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder> {

    private static final String DATE_FORMAT = "dd/MM/yyy";

    final private ItemClickListener mItemClickListener;

    private List<DiaryEntry> mDiaryEntries;
    private Context mContext;

    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

    public DiaryAdapter(Context context, ItemClickListener listener){
        this.mContext = context;
        mItemClickListener = listener;

    }

    @NonNull
    @Override
    public DiaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.activity_diary_entries, parent, false);

        return new DiaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryViewHolder holder, int position) {

        // Determine the values of the wanted data
        DiaryEntry diaryEntry = mDiaryEntries.get(position);
        String title = diaryEntry.getTitle();
        String description = diaryEntry.getDescription();
        String createdOn = dateFormat.format(diaryEntry.getCreatedOn());
        String updatedOn = dateFormat.format(diaryEntry.getUpdateOn());

        //Set values
        holder.DiaryTitleView.setText(title);
        holder.DiaryDescriptionView.setText(description);
        holder.createdOnView.setText(createdOn);
        holder.updatedOnView.setText(updatedOn);

    }

    @Override
    public int getItemCount() {
        if (mDiaryEntries == null) {
            return 0;
        }
        return mDiaryEntries.size();
    }

    /**
     * When data changes, this method updates the list of taskEntries
     * and notifies the adapter to use the new values on it
     */
    public void setmDiaryEntries(List<DiaryEntry> DiaryEntries) {
        mDiaryEntries = DiaryEntries;
        notifyDataSetChanged();
    }

    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }

    // Inner class for creating ViewHolders
    public class DiaryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Class variables for the task description and priority TextViews
        TextView DiaryTitleView, DiaryDescriptionView, createdOnView, updatedOnView;


        public DiaryViewHolder(View itemView) {
            super(itemView);

            DiaryTitleView = itemView.findViewById(R.id.diaryTitle);
            DiaryDescriptionView = itemView.findViewById(R.id.diaryDescription);
            createdOnView = itemView.findViewById(R.id.diaryCreatedOn);
            updatedOnView = itemView.findViewById(R.id.diaryUpdatedOn);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int elementId = mDiaryEntries.get(getAdapterPosition()).getId();
            mItemClickListener.onItemClickListener(elementId);
        }
    }
    
}
