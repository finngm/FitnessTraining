package fgmfitnessapp.fitnesstraining.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fgmfitnessapp.fitnesstraining.R;
import fgmfitnessapp.fitnesstraining.model.Exercise;

public class ExerciseStatsAdapter extends RecyclerView.Adapter<ExerciseStatsAdapter.ExerciseStatsViewHolder> {
    private List<Exercise> mDataset;

    // Reference to the views for each data item
    public static class ExerciseStatsViewHolder extends RecyclerView.ViewHolder {
        // each data item is a string
        public TextView mTextExerciseName;
        public TextView mTextCompleted;
        public ImageView mImageView;
        public ExerciseStatsViewHolder(View iView) {
            super(iView);
            mTextExerciseName = iView.findViewById(R.id.text_exerciseName);
            mTextCompleted = iView.findViewById(R.id.text_completed);
            mImageView = iView.findViewById(R.id.image_itemImage);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ExerciseStatsAdapter(List<Exercise> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ExerciseStatsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercises_completed_layout, parent, false);
        ExerciseStatsViewHolder vh = new ExerciseStatsViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ExerciseStatsViewHolder holder, int position) {
        holder.mTextExerciseName.setText(mDataset.get(position).getExerciseName());
        holder.mTextCompleted.setText("Completed " + mDataset.get(position).getTimesCompleted() + " Times");
        holder.mImageView.setImageResource(mDataset.get(position).getImage_id());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}