package fgmfitnessapp.fitnesstraining;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

import fgmfitnessapp.fitnesstraining.adapter.ExerciseStatsAdapter;
import fgmfitnessapp.fitnesstraining.adapter.WorkoutStatsAdapter;
import fgmfitnessapp.fitnesstraining.database.FitnessDatabase;
import fgmfitnessapp.fitnesstraining.model.Exercise;
import fgmfitnessapp.fitnesstraining.model.UserStats;
import fgmfitnessapp.fitnesstraining.model.Workout;

public class StatsActivity extends AppCompatActivity {
    private RecyclerView mRecViewWorkouts;
    private RecyclerView mRecViewExercises;
    private RecyclerView.LayoutManager mLayoutManager;
    private WorkoutStatsAdapter mWorkoutStatsAdapter;
    private ExerciseStatsAdapter mExerciseStatsAdapter;

    private TextView text_workoutsCompleted;
    private TextView text_exercisesCompleted;

    private FitnessDatabase fDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        fDatabase = FitnessDatabase.getFileDatabase(getApplicationContext());
        text_workoutsCompleted = findViewById(R.id.text_workoutsCompleted);
        text_exercisesCompleted = findViewById(R.id.text_exercisesCompleted);

        // link recycle view to variable
        mRecViewWorkouts = findViewById(R.id.recview_workouts);
        mRecViewWorkouts.setHasFixedSize(true);
        // use a linear layout
        mLayoutManager = new LinearLayoutManager(this);
        mRecViewWorkouts.setLayoutManager(mLayoutManager);

        // link recycle view to variable
        mRecViewExercises = findViewById(R.id.recview_exercises);
        mRecViewExercises.setHasFixedSize(true);
        // use a linear layout
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecViewExercises.setLayoutManager(mLayoutManager);

        new GetUserStatsTask().execute();
        new GetWorkoutsTask().execute();
        new GetExercisesTask().execute();
    }

    class GetWorkoutsTask extends AsyncTask<Void, Void, List<Workout>> {
        @Override
        protected List<Workout> doInBackground(Void... voids) {
            return fDatabase.workoutModel().getAllWorkouts();
        }

        @Override
        protected void onPostExecute(List<Workout> workouts) {
            mWorkoutStatsAdapter = new WorkoutStatsAdapter(workouts);
            mRecViewWorkouts.setAdapter(mWorkoutStatsAdapter);
        }
    }

    class GetExercisesTask extends AsyncTask<Void, Void, List<Exercise>> {
        @Override
        protected List<Exercise> doInBackground(Void... voids) {
            return fDatabase.exerciseModel().getAllExercises();
        }

        @Override
        protected void onPostExecute(List<Exercise> exercises) {
            mExerciseStatsAdapter = new ExerciseStatsAdapter(exercises);
            mRecViewExercises.setAdapter(mExerciseStatsAdapter);
        }
    }

    class GetUserStatsTask extends AsyncTask<Void, Void, UserStats> {
        @Override
        protected UserStats doInBackground(Void... voids) {
            return fDatabase.userStatsModel().getUserStats();
        }

        @Override
        protected void onPostExecute(UserStats userStats) {
            text_workoutsCompleted.setText("Total Workouts Completed: " + userStats.getWorkoutsCompleted());
            text_exercisesCompleted.setText("Total Exercises Completed: " + userStats.getExercisesCompleted());
        }
    }
}
