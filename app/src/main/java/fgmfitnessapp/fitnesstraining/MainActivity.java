package fgmfitnessapp.fitnesstraining;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import fgmfitnessapp.fitnesstraining.database.FitnessDatabase;

public class MainActivity extends AppCompatActivity {
    private FitnessDatabase fDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fDatabase = FitnessDatabase.getFileDatabase(getApplicationContext());
        new LoadDatabaseTask().execute();

        Button btn_workoutActivity = findViewById(R.id.btn_workouts);
        btn_workoutActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), SelectWorkoutActivity.class);
                startActivity(startIntent);
            }
        });

        Button btn_logActivity = findViewById(R.id.btn_log);
        btn_logActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), LogActivity.class);
                startActivity(startIntent);
            }
        });

        Button btn_statsActivity = findViewById(R.id.btn_stats);
        btn_statsActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), StatsActivity.class);
                startActivity(startIntent);
            }
        });
    }

    class LoadDatabaseTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            fDatabase.workoutModel().getAllWorkouts();
            return null;
        }

        @Override
        protected void onPostExecute(Void voids) {

        }
    }

}
