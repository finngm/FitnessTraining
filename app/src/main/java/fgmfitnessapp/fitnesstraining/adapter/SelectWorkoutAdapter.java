package fgmfitnessapp.fitnesstraining.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fgmfitnessapp.fitnesstraining.R;
import fgmfitnessapp.fitnesstraining.model.Workout;

public class SelectWorkoutAdapter extends RecyclerView.Adapter<SelectWorkoutAdapter.SelectWorkoutViewHolder> {
    private List<Workout> mDataset;

    // Reference to the views for each data item
    public static class SelectWorkoutViewHolder extends RecyclerView.ViewHolder {
        // each data item is a string
        public TextView mTextView;
        public SelectWorkoutViewHolder(View iView) {
            super(iView);
            mTextView = iView.findViewById(R.id.text_itemTitle);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SelectWorkoutAdapter(List<Workout> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SelectWorkoutViewHolder onCreateViewHolder(ViewGroup parent,
                                                                           int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                               .inflate(R.layout.list_layout, parent, false);
        SelectWorkoutViewHolder vh = new SelectWorkoutViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(SelectWorkoutViewHolder holder, int position) {

        holder.mTextView.setText(mDataset.get(position).getWorkoutName());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
