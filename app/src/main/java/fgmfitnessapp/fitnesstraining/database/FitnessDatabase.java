package fgmfitnessapp.fitnesstraining.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import fgmfitnessapp.fitnesstraining.R;
import fgmfitnessapp.fitnesstraining.model.Exercise;
import fgmfitnessapp.fitnesstraining.model.ExerciseDao;
import fgmfitnessapp.fitnesstraining.model.UserStats;
import fgmfitnessapp.fitnesstraining.model.UserStatsDao;
import fgmfitnessapp.fitnesstraining.model.Workout;
import fgmfitnessapp.fitnesstraining.model.WorkoutDao;
import fgmfitnessapp.fitnesstraining.model.WorkoutExerciseJoin;
import fgmfitnessapp.fitnesstraining.model.WorkoutExerciseJoinDao;
import fgmfitnessapp.fitnesstraining.model.WorkoutLog;
import fgmfitnessapp.fitnesstraining.model.WorkoutLogDao;

@Database(entities = {Workout.class,
                      Exercise.class,
                      WorkoutExerciseJoin.class,
                      UserStats.class,
                      WorkoutLog.class}, version = 1, exportSchema = false)
public abstract class FitnessDatabase extends RoomDatabase {

    private static FitnessDatabase DBINSTANCE;

    // Tables
    public abstract ExerciseDao exerciseModel();
    public abstract WorkoutDao workoutModel();
    public abstract WorkoutExerciseJoinDao workoutExerciseJoinModel();
    public abstract UserStatsDao userStatsModel();
    public abstract WorkoutLogDao workoutLogModel();

    public static FitnessDatabase getFileDatabase(Context context) {

        Callback dbCallback = new Callback(){

            @Override
            public void onCreate (@NonNull final SupportSQLiteDatabase db){
                super.onCreate(db);
                // create default database entries in background thread
                Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        createDefaultWorkouts();
                    }
                });
            }
        };

        if (DBINSTANCE == null) {
            DBINSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    FitnessDatabase.class, "fit_database").addCallback(dbCallback).build();
        }

        return DBINSTANCE;
    }

    private static void createDefaultWorkouts() {
        // Create user stats row
        addUserStats(DBINSTANCE, 0, 0);
        // Create default exercises
        // Abs
        Exercise exercise1 = addExercise(DBINSTANCE, "Sit Ups", "Abs", 10, R.drawable.sit_ups, 0);
        Exercise exercise2 = addExercise(DBINSTANCE,"Reverse Crunches", "Abs", 10, R.drawable.reverse_crunches, 0);
        Exercise exercise3 = addExercise(DBINSTANCE,"Bicycle Crunches", "Abs", 10, R.drawable.bicycle_crunches, 0);

        // Quads
        Exercise exercise4 = addExercise(DBINSTANCE,"Lunges", "Quads", 10, R.drawable.lunges, 0);
        Exercise exercise5 = addExercise(DBINSTANCE,"High Knees", "Quads", 20, R.drawable.high_knees, 0);
        Exercise exercise6 = addExercise(DBINSTANCE,"Turning Kicks", "Quads", 10, R.drawable.turning_kicks, 0);

        // Glutes
        Exercise exercise7 = addExercise(DBINSTANCE,"Squats", "Glutes", 20, R.drawable.squats, 0);
        Exercise exercise8 = addExercise(DBINSTANCE,"Donkey Kicks", "Glutes", 20, R.drawable.donkey_kicks, 0);
        Exercise exercise9 = addExercise(DBINSTANCE,"Bridges", "Glutes", 10, R.drawable.bridges, 0);

        // Triceps
        Exercise exercise10 = addExercise(DBINSTANCE,"Close Grip Press-Ups", "Triceps", 10, R.drawable.close_grip_press_ups, 0);
        Exercise exercise11 = addExercise(DBINSTANCE,"Tricep Dips", "Triceps", 10, R.drawable.tricep_dips, 0);
        Exercise exercise12 = addExercise(DBINSTANCE,"Tricep Extensions", "Triceps", 10, R.drawable.tricep_extensions, 0);

        // Biceps
        Exercise exercise13 = addExercise(DBINSTANCE,"Leg Curls", "Biceps", 10, R.drawable.leg_curls, 0);
        Exercise exercise14 = addExercise(DBINSTANCE,"Chin-Ups", "Biceps", 10, R.drawable.chin_ups, 0);
        Exercise exercise15 = addExercise(DBINSTANCE,"Doorframe Rows", "Biceps", 10, R.drawable.doorframe_rows, 0);

        // Back
        Exercise exercise16 = addExercise(DBINSTANCE,"Pull-Ups", "Back", 10, R.drawable.pull_ups, 0);
        Exercise exercise17 = addExercise(DBINSTANCE,"Elbow Lifts", "Back", 10, R.drawable.elbow_lifts, 0);
        Exercise exercise18 = addExercise(DBINSTANCE,"Superman", "Back", 10, R.drawable.superman, 0);

        // Chest
        Exercise exercise19 = addExercise(DBINSTANCE,"Press-Ups", "Chest", 10, R.drawable.press_ups, 0);
        Exercise exercise20 = addExercise(DBINSTANCE,"Plank Rotations", "Chest", 10, R.drawable.plank_rotations, 0);
        Exercise exercise21 = addExercise(DBINSTANCE,"Chest Squeezes", "Chest", 10, R.drawable.chest_squeezes, 0);

        // Create default
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(exercise1);
        exercises.add(exercise2);
        exercises.add(exercise3);
        addWorkout(DBINSTANCE, "Abs", "Abs", 1, 25,
                exercises, false, 0);

        exercises.clear();
        exercises.add(exercise1);
        exercises.add(exercise1);
        exercises.add(exercise2);
        exercises.add(exercise3);
        exercises.add(exercise3);
        exercises.add(exercise2);
        addWorkout(DBINSTANCE, "Abs2", "Abs", 2, 15,
                exercises, false, 0);

        exercises.clear();
        exercises.add(exercise13);
        exercises.add(exercise14);
        exercises.add(exercise15);
        addWorkout(DBINSTANCE, "Biceps", "Biceps", 1, 18,
                exercises, false, 0);

        exercises.clear();
        exercises.add(exercise13);
        exercises.add(exercise14);
        exercises.add(exercise15);
        exercises.add(exercise13);
        exercises.add(exercise13);
        exercises.add(exercise15);
        addWorkout(DBINSTANCE, "Biceps2", "Biceps", 2, 18,
                exercises, false, 0);

        exercises.clear();
        exercises.add(exercise19);
        exercises.add(exercise20);
        exercises.add(exercise21);
        addWorkout(DBINSTANCE, "Chest", "Chest", 1, 20,
                exercises, false, 0);

        exercises.clear();
        exercises.add(exercise19);
        exercises.add(exercise20);
        exercises.add(exercise21);
        exercises.add(exercise19);
        exercises.add(exercise20);
        exercises.add(exercise21);
        addWorkout(DBINSTANCE, "Chest2", "Chest",2, 15,
                exercises, false, 0);

        exercises.clear();
        exercises.add(exercise19);
        exercises.add(exercise19);
        exercises.add(exercise20);
        exercises.add(exercise20);
        exercises.add(exercise21);
        exercises.add(exercise20);
        exercises.add(exercise21);
        exercises.add(exercise19);
        exercises.add(exercise21);
        addWorkout(DBINSTANCE, "Chest3", "Chest", 3, 10,
                exercises, false, 0);

        Log.d("FGM_DEBUG","Database filled with default data");
    }

    private static Exercise addExercise(final FitnessDatabase db,
                                        final String name,
                                        final String bodyPart,
                                        final int reps,
                                        final int image_id,
                                        final int times_completed) {
        Exercise exercise = new Exercise(name, bodyPart, reps, image_id, times_completed);
        db.exerciseModel().insertAll(exercise);
        return exercise;
    }

    public static void updateExercise(final FitnessDatabase db,
                                        final String name,
                                        final String bodyPart,
                                        final int reps,
                                        final int image_id,
                                        final int times_completed) {
        db.exerciseModel().update(new Exercise(name, bodyPart, reps, image_id, times_completed));
    }

    public static void addWorkout(final FitnessDatabase db,
                                  final String name,
                                  final String displayName,
                                  final int level,
                                  final int restTime,
                                  final List<Exercise> exercises,
                                  final boolean userCreated,
                                  final int timesCompleted) {
        db.workoutModel().insertAll(new Workout(name, displayName, level, restTime, userCreated, timesCompleted));
        for (Exercise exercise : exercises) {
            db.workoutExerciseJoinModel().insert(new WorkoutExerciseJoin(0, name, exercise.getExerciseName()));
        }
    }

    public static void updateWorkout(final FitnessDatabase db,
                                  final String name,
                                  final String displayName,
                                  final int level,
                                  final int restTime,
                                  final boolean userCreated,
                                  final int timesCompleted) {
        db.workoutModel().update(new Workout(name, displayName, level, restTime, userCreated, timesCompleted));
    }

    public static void addUserStats(final FitnessDatabase db,
                                    final int workoutsCompleted,
                                    final int exercisesCompleted) {
        db.userStatsModel().insert(new UserStats("userStats", workoutsCompleted, exercisesCompleted));
    }

    public static void addWorkoutLog(final FitnessDatabase db,
                                     final String workoutName,
                                     final String dateCompleted,
                                     final String timeCompleted) {
        db.workoutLogModel().insertAll(new WorkoutLog(0, workoutName, dateCompleted, timeCompleted));
    }

    public static void destroyDBInstance() {
        DBINSTANCE = null;
    }

}
