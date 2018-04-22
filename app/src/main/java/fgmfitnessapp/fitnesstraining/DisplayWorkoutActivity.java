package fgmfitnessapp.fitnesstraining;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import fgmfitnessapp.fitnesstraining.adapter.SelectExerciseAdapter;
import fgmfitnessapp.fitnesstraining.database.FitnessDatabase;
import fgmfitnessapp.fitnesstraining.model.Exercise;
import fgmfitnessapp.fitnesstraining.model.Workout;

public class DisplayWorkoutActivity extends AppCompatActivity {
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecViewExercises;
    private SelectExerciseAdapter mExerciseAdapter;

    private TextView text_workoutName;
    private TextView text_workoutRest;
    private TextView text_intensityLevel;

    private Workout workout;
    private List<Exercise> workoutExercises;

    private FitnessDatabase fDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_workout);
        fDatabase = FitnessDatabase.getFileDatabase(getApplicationContext());
        text_workoutName = findViewById(R.id.text_workoutName);
        text_workoutRest = findViewById(R.id.text_workoutRest);
        text_intensityLevel = findViewById(R.id.text_intensityLevel);

        // link recycle view to variable
        mRecViewExercises = findViewById(R.id.recview_workoutExercises);
        mRecViewExercises.setHasFixedSize(true);
        // use a linear layout
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecViewExercises.setLayoutManager(mLayoutManager);

        // execute task to retrieve the workouts data
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            new GetFullWorkoutTask().execute((String) extras.get("workoutName"));
        } else {
            Toast.makeText(getApplicationContext(), "ERROR: no workout chosen?",
                    Toast.LENGTH_SHORT).show();
        }
    }

    class GetFullWorkoutTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... workoutName) {
            workout = fDatabase.workoutModel().getWorkoutWithName(workoutName[0]);
            workoutExercises = fDatabase.workoutExerciseJoinModel()
                                        .getExercisesForWorkouts(workoutName[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void voids) {
            // set textviews
            text_workoutName.setText(workout.getWorkoutName());
            text_workoutRest.setText("Rest Time: " + workout.getRestTime());
            if (workout.getLevel() == 0) {
                text_intensityLevel.setText("Custom Workout");
            }
            else {
                text_intensityLevel.setText("Intensity Level: " + workout.getLevel());
            }
            // set recyclerview items
            mExerciseAdapter = new SelectExerciseAdapter(workoutExercises);
            mRecViewExercises.setAdapter(mExerciseAdapter);
        }
    }
}
