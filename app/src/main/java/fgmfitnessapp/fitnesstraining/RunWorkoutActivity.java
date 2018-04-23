package fgmfitnessapp.fitnesstraining;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import fgmfitnessapp.fitnesstraining.database.FitnessDatabase;
import fgmfitnessapp.fitnesstraining.model.Exercise;
import fgmfitnessapp.fitnesstraining.model.UserStats;
import fgmfitnessapp.fitnesstraining.model.Workout;

public class RunWorkoutActivity extends AppCompatActivity {
    private TextView text_exerciseName;
    private TextView text_repetitions;
    private ImageView img_exercise;
    private TextView text_countdown;
    private Button btn_nextExercise;

    private Workout workout;
    private List<Exercise> workoutExercises;

    private FitnessDatabase fDatabase;

    private int workoutCount = 1;
    private int exerciseListSize;

    private int workoutsCompleted;
    private int exercisesCompleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_workout);
        fDatabase = FitnessDatabase.getFileDatabase(getApplicationContext());
        text_exerciseName = findViewById(R.id.text_exerciseName);
        text_repetitions = findViewById(R.id.text_repetitions);
        img_exercise = findViewById(R.id.img_exercise);
        text_countdown = findViewById(R.id.text_countdown);
        btn_nextExercise = findViewById(R.id.btn_nextExercise);

        // get data from previous activity
        Intent intent = getIntent();
        workout = intent.getParcelableExtra("workout");
        workoutExercises = intent.getParcelableArrayListExtra("exercises");
        exerciseListSize = workoutExercises.size();

        new GetUserStatsTask().execute();
        displayExercise();
    }

    public void nextExercise(View view) {
        toggleButton(false);
        if (onLastExercise()) {
            new UpdateWorkoutStatsTask().execute();
        } else {
            workoutCount += 1;
            displayCountdown();
            runCountdown();
        }
    }

    private void displayExercise() {
        if (onLastExercise()) {
            btn_nextExercise.setText("Finish!");
        }
        toggleButton(true);
        text_countdown.setVisibility(View.INVISIBLE);

        text_exerciseName.setText(workoutExercises.get(workoutCount-1).getExerciseName());
        text_repetitions.setText(workoutExercises.get(workoutCount-1).getRepetitions() + " Reps");
        img_exercise.setImageResource(workoutExercises.get(workoutCount-1).getImage_id());

        showFields();
    }

    private void displayCountdown() {
        hideFields();

        text_countdown.setVisibility(View.VISIBLE);
        text_exerciseName.setText("Rest Time!");
    }

    private void runCountdown() {
        new CountDownTimer((workout.getRestTime()+2)*1000, 1000) {

            public void onTick(long millisUntilFinished) {
                text_countdown.setText(Integer.toString(
                        Double.valueOf(TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)).intValue()-1)
                );
            }

            public void onFinish() {
                displayExercise();
            }

        }.start();
    }

    private void toggleButton(boolean enable) {
        btn_nextExercise.setEnabled(enable);
    }

    private boolean onLastExercise() {
        return workoutCount == exerciseListSize;
    }

    /********************************************
     * methods to show/hide the exercise fields *
     ********************************************/

    private void showFields() {
        text_repetitions.setVisibility(View.VISIBLE);
        img_exercise.setVisibility(View.VISIBLE);
    }

    private void hideFields() {
        text_repetitions.setVisibility(View.INVISIBLE);
        img_exercise.setVisibility(View.INVISIBLE);
    }

    /*********
     * tasks *
     *********/

    class GetUserStatsTask extends AsyncTask<Void, Void, UserStats> {
        @Override
        protected UserStats doInBackground(Void... voids) {
            return fDatabase.userStatsModel().getUserStats();
        }

        @Override
        protected void onPostExecute(UserStats userStats) {
            workoutsCompleted = userStats.getWorkoutsCompleted();
            exercisesCompleted = userStats.getExercisesCompleted();
        }
    }

    /*****************************************
     * update user stats/workout/workout log *
     *****************************************/
    class UpdateWorkoutStatsTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            // add to workout log
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String currentDate = dateFormat.format(new Date());
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            String currentTime = timeFormat.format(Calendar.getInstance().getTime());
            fDatabase.addWorkoutLog(fDatabase,
                    workout.getDisplayName(),
                    currentDate,
                    currentTime);
            // update user stats
            fDatabase.addUserStats(fDatabase,
                    workoutsCompleted+1,
                    exercisesCompleted+exerciseListSize);
            // update completed workout
            fDatabase.updateWorkout(fDatabase,
                    workout.getWorkoutName(),
                    workout.getDisplayName(),
                    workout.getLevel(),
                    workout.getRestTime(),
                    workout.isUserCreated(),
                    workout.getTimesCompleted()+1);
            // update completed exercises
            for (Exercise exercise : workoutExercises) {
                fDatabase.updateExercise(fDatabase,
                        exercise.getExerciseName(),
                        exercise.getBodyPart(),
                        exercise.getRepetitions(),
                        exercise.getImage_id(),
                        exercise.getTimesCompleted()+1);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void voids) {
            Toast.makeText(getApplicationContext(), "Workout Completed!",
                    Toast.LENGTH_SHORT).show();
            //Intent startIntent = new Intent(getApplicationContext(), SelectWorkoutActivity.class);
            //startActivity(startIntent);
            finish();
        }
    }
}
