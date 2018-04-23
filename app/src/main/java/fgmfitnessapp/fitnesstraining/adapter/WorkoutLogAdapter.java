package fgmfitnessapp.fitnesstraining.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fgmfitnessapp.fitnesstraining.R;
import fgmfitnessapp.fitnesstraining.model.WorkoutLog;

public class WorkoutLogAdapter extends RecyclerView.Adapter<WorkoutLogAdapter.WorkoutLogViewHolder> {
    private List<WorkoutLog> mDataset;

    // Reference to the views for each data item
    public static class WorkoutLogViewHolder extends RecyclerView.ViewHolder {
        // each data item is a string
        public TextView mTextWorkName;
        public TextView mTextDateTime;
        public WorkoutLogViewHolder(View iView) {
            super(iView);
            mTextWorkName = iView.findViewById(R.id.text_workoutName);
            mTextDateTime = iView.findViewById(R.id.text_dateTime);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public WorkoutLogAdapter(List<WorkoutLog> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public WorkoutLogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.log_layout, parent, false);
        WorkoutLogViewHolder vh = new WorkoutLogViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(WorkoutLogViewHolder holder, int position) {
        holder.mTextWorkName.setText(mDataset.get(position).getWorkoutName());
        holder.mTextDateTime.setText("Completed On: " +
                mDataset.get(position).getDateCompleted() + " " +
                mDataset.get(position).getTimeCompleted());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
