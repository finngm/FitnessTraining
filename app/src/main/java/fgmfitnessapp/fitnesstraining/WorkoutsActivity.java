package fgmfitnessapp.fitnesstraining;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.List;

import fgmfitnessapp.fitnesstraining.adapter.SelectWorkoutAdapter;
import fgmfitnessapp.fitnesstraining.database.FitnessDatabase;
import fgmfitnessapp.fitnesstraining.model.Workout;

public class WorkoutsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    private FitnessDatabase fDatabase;
    final GetWorkoutsTask wTask = new GetWorkoutsTask();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);
        fDatabase = FitnessDatabase.getFileDatabase(getApplicationContext());

        Button btn_workoutActivity = findViewById(R.id.btn_createWorkout);
        btn_workoutActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), CreateWorkoutActivity.class);
                startActivity(startIntent);
            }
        });

        mRecyclerView = findViewById(R.id.recview_workouts);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // load workout data on background thread
        wTask.execute(null, null, null);
    }

    class GetWorkoutsTask extends AsyncTask<Void, Void, List<Workout>> {
        @Override
        protected List<Workout> doInBackground(Void... voids) {
            return fDatabase.workoutModel().getAllWorkouts();
        }

        @Override
        protected void onPostExecute(List<Workout> workouts) {
            mRecyclerView.setAdapter(new SelectWorkoutAdapter(workouts));
        }
    }
}
