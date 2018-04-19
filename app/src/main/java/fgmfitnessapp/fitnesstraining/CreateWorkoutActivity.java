package fgmfitnessapp.fitnesstraining;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import fgmfitnessapp.fitnesstraining.adapter.SelectExerciseAdapter;
import fgmfitnessapp.fitnesstraining.database.FitnessDatabase;
import fgmfitnessapp.fitnesstraining.model.Exercise;

public class CreateWorkoutActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    private FitnessDatabase fDatabase;
    final GetExercisesTask eTask = new GetExercisesTask();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workout);
        fDatabase = FitnessDatabase.getFileDatabase(getApplicationContext());

        mRecyclerView = findViewById(R.id.recview_exercises);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // load exercise data on background thread
        eTask.execute(null, null, null);
    }

    class GetExercisesTask extends AsyncTask<Void, Void, List<Exercise>> {
        @Override
        protected List<Exercise> doInBackground(Void... voids) {
            return fDatabase.exerciseModel().getAllExercises();
        }

        @Override
        protected void onPostExecute(List<Exercise> exercises) {
            mRecyclerView.setAdapter(new SelectExerciseAdapter(exercises));
        }
    }
}
