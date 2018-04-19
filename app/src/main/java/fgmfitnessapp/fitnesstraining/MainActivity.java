package fgmfitnessapp.fitnesstraining;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import fgmfitnessapp.fitnesstraining.database.FitnessDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FitnessDatabase.getFileDatabase(getApplicationContext());

        Button btn_workoutActivity = findViewById(R.id.btn_workouts);
        btn_workoutActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), WorkoutsActivity.class);
                startActivity(startIntent);
            }
        });
    }

}
