package fgmfitnessapp.fitnesstraining;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_workoutActivity = findViewById(R.id.btn_workouts);
        btn_workoutActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), WorkoutsActivity.class);
                startActivity(startIntent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("FGM_DEBUG","On Start has been run");
    }

}
