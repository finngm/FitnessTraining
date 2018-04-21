package fgmfitnessapp.fitnesstraining.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fgmfitnessapp.fitnesstraining.R;
import fgmfitnessapp.fitnesstraining.model.Exercise;

public class SelectedExercisesAdapter extends RecyclerView.Adapter<SelectedExercisesAdapter.SelectedExercisesViewHolder> {
    private List<Exercise> mDataset;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener  listener) {
        mListener = listener;
    }

    // Reference to the views for each data item
    public static class SelectedExercisesViewHolder extends RecyclerView.ViewHolder {
        // each data item is a string
        public TextView mTextView;
        public SelectedExercisesViewHolder(View iView, final OnItemClickListener listener) {
            super(iView);
            mTextView = iView.findViewById(R.id.text_exerciseName);

            iView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SelectedExercisesAdapter(List<Exercise> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SelectedExercisesViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise_name_layout, parent, false);
        SelectedExercisesViewHolder vh = new SelectedExercisesViewHolder(v, mListener);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(SelectedExercisesViewHolder holder, int position) {
        holder.mTextView.setText(mDataset.get(position).getExerciseName());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
