package fgmfitnessapp.fitnesstraining;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fgmfitnessapp.fitnesstraining.adapter.SelectExerciseAdapter;
import fgmfitnessapp.fitnesstraining.adapter.SelectedExercisesAdapter;
import fgmfitnessapp.fitnesstraining.database.FitnessDatabase;
import fgmfitnessapp.fitnesstraining.model.Exercise;

public class CreateWorkoutActivity extends AppCompatActivity {
    private RecyclerView mRecViewSelectExercise;
    private RecyclerView mRecViewSelectedExercises;
    private RecyclerView.LayoutManager mLayoutManager;
    private SelectExerciseAdapter mExerciseAdapter;
    private SelectedExercisesAdapter mSelectedExercisesAdapter;
    private Button btn_finishCreateWorkout;
    private EditText editText_workoutName;
    private EditText editText_restTime;

    private FitnessDatabase fDatabase;
    final GetExercisesTask eTask = new GetExercisesTask();

    private List<Exercise> allExercises;
    private List<Exercise> selectedExercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workout);
        fDatabase = FitnessDatabase.getFileDatabase(getApplicationContext());
        btn_finishCreateWorkout = findViewById(R.id.btn_finishCreateWorkout);
        editText_workoutName = findViewById(R.id.editText_workoutName);
        editText_restTime = findViewById(R.id.editText_restTime);

        // link recycle view to variable
        mRecViewSelectExercise = findViewById(R.id.recview_exercises);
        mRecViewSelectExercise.setHasFixedSize(true);
        // use a linear layout
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecViewSelectExercise.setLayoutManager(mLayoutManager);

        // link recycle view to variable
        mRecViewSelectedExercises = findViewById(R.id.recview_selectedExercises);
        mRecViewSelectedExercises.setHasFixedSize(true);
        // use a linear layout
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecViewSelectedExercises.setLayoutManager(mLayoutManager);
        // set adapter
        selectedExercises = new ArrayList<>();
        mSelectedExercisesAdapter = new SelectedExercisesAdapter(selectedExercises);
        mRecViewSelectedExercises.setAdapter(mSelectedExercisesAdapter);
        // when click the selected exercise is removed from the workout
        mSelectedExercisesAdapter.setOnItemClickListener(new SelectedExercisesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                removeSelectedExercise(position);
            }
        });

        // load exercise data on background thread
        eTask.execute(null, null, null);
    }

    public void insertSelectedExercise(int position) {
        selectedExercises.add(allExercises.get(position));
        enableFinish();
        mSelectedExercisesAdapter.notifyItemInserted(selectedExercises.size()-1);
    }

    public void removeSelectedExercise(int position) {
        selectedExercises.remove(position);
        enableFinish();
        mSelectedExercisesAdapter.notifyItemRemoved(position);
    }

    public void finishCreateWorkout(View view) {
        new GetWorkoutNameTask().execute(editText_workoutName.getText().toString());
    }

    // have the fields been filled out correctly and is the name unique
    private boolean isWorkoutCorrect(String workoutName) {
        if (TextUtils.isEmpty(editText_workoutName.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter a Workout Name",
                    Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(editText_restTime.getText().toString()) ||
                 Integer.parseInt(editText_restTime.getText().toString()) < 1) {
            Toast.makeText(getApplicationContext(), "Enter a Rest Time",
                    Toast.LENGTH_SHORT).show();
        }
        else if (!editText_workoutName.getText().toString().equals(workoutName)) {
            return true;
        }
        else {
            Toast.makeText(getApplicationContext(), "A Workout with this name already exists",
                    Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    // enable/disable the finish button
    private void enableFinish() {
        if (selectedExercises.size() > 0) {
            btn_finishCreateWorkout.setEnabled(true);
        } else {
            btn_finishCreateWorkout.setEnabled(false);
        }
    }

    class GetExercisesTask extends AsyncTask<Void, Void, List<Exercise>> {
        @Override
        protected List<Exercise> doInBackground(Void... voids) {
            return fDatabase.exerciseModel().getAllExercises();
        }

        @Override
        protected void onPostExecute(List<Exercise> exercises) {
            allExercises = exercises;
            mExerciseAdapter = new SelectExerciseAdapter(allExercises);
            mRecViewSelectExercise.setAdapter(mExerciseAdapter);

            mExerciseAdapter.setOnItemClickListener(new SelectExerciseAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    insertSelectedExercise(position);
                }
            });
        }
    }

    class GetWorkoutNameTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... workoutName) {
            return fDatabase.workoutModel().getWorkoutWithName(workoutName[0]);
        }

        @Override
        protected void onPostExecute(String workoutName) {
            if (isWorkoutCorrect(workoutName)) {
                Toast.makeText(getApplicationContext(), "INSERT NEW WORKOUT",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
