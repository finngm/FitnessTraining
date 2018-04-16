package fgmfitnessapp.fitnesstraining;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import fgmfitnessapp.fitnesstraining.adapter.SelectWorkoutAdapter;
import fgmfitnessapp.fitnesstraining.database.FitnessDatabase;
import fgmfitnessapp.fitnesstraining.model.Workout;

public class WorkoutsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private FitnessDatabase fDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);

        // get database instance
        fDatabase = FitnessDatabase.getFileDatabase(getApplicationContext());

        mRecyclerView = findViewById(R.id.recview_workouts);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify the adapter
        // load workout data on background thread
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                mAdapter = new SelectWorkoutAdapter(fetchData());
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<Workout> fetchData() {
        return fDatabase.workoutModel().getAllWorkouts();
    }
}
