package fgmfitnessapp.fitnesstraining;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import fgmfitnessapp.fitnesstraining.adapter.WorkoutLogAdapter;
import fgmfitnessapp.fitnesstraining.database.FitnessDatabase;
import fgmfitnessapp.fitnesstraining.model.WorkoutLog;

public class LogActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private WorkoutLogAdapter mWorkoutLogAdapter;

    private FitnessDatabase fDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        fDatabase = FitnessDatabase.getFileDatabase(getApplicationContext());

        mRecyclerView = findViewById(R.id.recview_logs);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        new GetWorkoutLogsTask().execute();
    }

    class GetWorkoutLogsTask extends AsyncTask<Void, Void, List<WorkoutLog>> {
        @Override
        protected List<WorkoutLog> doInBackground(Void... voids) {
            return fDatabase.workoutLogModel().getWorkoutLogs();
        }

        @Override
        protected void onPostExecute(List<WorkoutLog> workoutLogs) {
            mWorkoutLogAdapter = new WorkoutLogAdapter(workoutLogs);
            mRecyclerView.setAdapter(mWorkoutLogAdapter);
        }
    }
}
