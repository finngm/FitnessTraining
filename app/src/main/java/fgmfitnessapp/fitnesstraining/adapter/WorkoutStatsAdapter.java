package fgmfitnessapp.fitnesstraining.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fgmfitnessapp.fitnesstraining.R;
import fgmfitnessapp.fitnesstraining.model.Workout;

public class WorkoutStatsAdapter extends RecyclerView.Adapter<WorkoutStatsAdapter.WorkoutStatsViewHolder> {
    private List<Workout> mDataset;

    // Reference to the views for each data item
    public static class WorkoutStatsViewHolder extends RecyclerView.ViewHolder {
        // each data item is a string
        public TextView mTextWorkName;
        public TextView mTextTimesCompleted;
        public WorkoutStatsViewHolder(View iView) {
            super(iView);
            mTextWorkName = iView.findViewById(R.id.text_workoutName);
            mTextTimesCompleted = iView.findViewById(R.id.text_dateTime);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public WorkoutStatsAdapter(List<Workout> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public WorkoutStatsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.log_layout, parent, false);
        WorkoutStatsViewHolder vh = new WorkoutStatsViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(WorkoutStatsViewHolder holder, int position) {
        if (mDataset.get(position).isUserCreated()) {
            holder.mTextWorkName.setText(mDataset.get(position).getDisplayName());
        } else {
            holder.mTextWorkName.setText(mDataset.get(position).getDisplayName() + " Level: " +
            mDataset.get(position).getLevel());
        }
        holder.mTextTimesCompleted.setText("Completed " + mDataset.get(position).getTimesCompleted() + " Times");
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
