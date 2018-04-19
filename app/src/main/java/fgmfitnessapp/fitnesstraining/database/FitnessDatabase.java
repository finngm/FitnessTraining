package fgmfitnessapp.fitnesstraining.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import fgmfitnessapp.fitnesstraining.model.Exercise;
import fgmfitnessapp.fitnesstraining.model.ExerciseDao;
import fgmfitnessapp.fitnesstraining.model.Workout;
import fgmfitnessapp.fitnesstraining.model.WorkoutDao;
import fgmfitnessapp.fitnesstraining.model.WorkoutExerciseJoin;
import fgmfitnessapp.fitnesstraining.model.WorkoutExerciseJoinDao;

@Database(entities = {Workout.class, Exercise.class, WorkoutExerciseJoin.class}, version = 1, exportSchema = false)
public abstract class FitnessDatabase extends RoomDatabase {

    private static FitnessDatabase DBINSTANCE;

    // Tables
    public abstract ExerciseDao exerciseModel();
    public abstract WorkoutDao workoutModel();
    public abstract WorkoutExerciseJoinDao workoutExerciseJoinModel();

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
                    FitnessDatabase.class, "ft_database").addCallback(dbCallback).build();
        }

        return DBINSTANCE;
    }

    private static void createDefaultWorkouts() {
//        FitnessDatabase db = (FitnessDatabase) fdb;
        // Create default exercises
        // Abs
        Exercise exercise1 = addExercise(DBINSTANCE, "Sit Ups", "Abs", 10, 0);
        Exercise exercise2 = addExercise(DBINSTANCE,"Reverse Crunches", "Abs", 10, 1);
        Exercise exercise3 = addExercise(DBINSTANCE,"Bicycle Crunches", "Abs", 10, 2);

        // Quads
        Exercise exercise4 = addExercise(DBINSTANCE,"Lunges", "Quads", 10, 3);
        Exercise exercise5 = addExercise(DBINSTANCE,"High Knees", "Quads", 20, 4);
        Exercise exercise6 = addExercise(DBINSTANCE,"Turning Kicks", "Quads", 10, 5);

        // Glutes
        Exercise exercise7 = addExercise(DBINSTANCE,"Squats", "Glutes", 20, 6);
        Exercise exercise8 = addExercise(DBINSTANCE,"Donkey Kicks", "Glutes", 20, 7);
        Exercise exercise9 = addExercise(DBINSTANCE,"Bridges", "Glutes", 10, 8);

        // Triceps
        Exercise exercise10 = addExercise(DBINSTANCE,"Close Grip Press-Ups", "Triceps", 10, 9);
        Exercise exercise11 = addExercise(DBINSTANCE,"Tricep Dips", "Triceps", 10, 10);
        Exercise exercise12 = addExercise(DBINSTANCE,"Tricep Extensions", "Triceps", 10, 11);

        // Biceps
        Exercise exercise13 = addExercise(DBINSTANCE,"Leg Curls", "Biceps", 10, 12);
        Exercise exercise14 = addExercise(DBINSTANCE,"Chin-Ups", "Biceps", 10, 13);
        Exercise exercise15 = addExercise(DBINSTANCE,"Doorframe Rows", "Biceps", 10, 14);

        // Back
        Exercise exercise16 = addExercise(DBINSTANCE,"Pull-Ups", "Back", 10, 15);
        Exercise exercise17 = addExercise(DBINSTANCE,"Elbow Lifts", "Back", 10, 16);
        Exercise exercise18 = addExercise(DBINSTANCE,"Superman", "Back", 10, 17);

        // Chest
        Exercise exercise19 = addExercise(DBINSTANCE,"Press-Ups", "Chest", 10, 18);
        Exercise exercise20 = addExercise(DBINSTANCE,"Plank Rotations", "Chest", 10, 19);
        Exercise exercise21 = addExercise(DBINSTANCE,"Chest Squeezes", "Chest", 10, 20);

        // Create default workouts
        addWorkout(DBINSTANCE, "Abs", 1, 25, groupExercises(exercise1, exercise2, exercise3), false);
        addWorkout(DBINSTANCE, "Quads", 1, 30, groupExercises(exercise4, exercise5, exercise6), false);
        addWorkout(DBINSTANCE, "Glutes", 1, 30, groupExercises(exercise7, exercise8, exercise9), false);
        addWorkout(DBINSTANCE, "Triceps", 1, 25, groupExercises(exercise10, exercise11, exercise12), false);
        addWorkout(DBINSTANCE, "Biceps", 1, 20, groupExercises(exercise13, exercise14, exercise15), false);
        addWorkout(DBINSTANCE, "Back", 1, 25, groupExercises(exercise16, exercise17, exercise18), false);
        addWorkout(DBINSTANCE, "Chest", 1, 20, groupExercises(exercise19, exercise20, exercise21), false);
    }

    private static List<Exercise> groupExercises(Exercise exercise1,Exercise exercise2,Exercise exercise3) {
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(exercise1);
        exercises.add(exercise2);
        exercises.add(exercise3);
        return exercises;
    }

    private static Exercise addExercise(final FitnessDatabase db,
                                        final String name,
                                        final String bodyPart,
                                        final int reps,
                                        final int image_id) {
        Exercise exercise = new Exercise(name, bodyPart, reps, image_id);
        db.exerciseModel().insertAll(exercise);
        return exercise;
    }

    private static void addWorkout(final FitnessDatabase db,
                                   final String name,
                                   final int level,
                                   final int restTime,
                                   final List<Exercise> exercises,
                                   final boolean userCreated) {
        db.workoutModel().insertAll(new Workout(name, level, restTime, userCreated));
        for (Exercise exercise : exercises) {
            db.workoutExerciseJoinModel().insert(new WorkoutExerciseJoin(name, exercise.getExerciseName()));
        }
    }

    public static void destroyDBInstance() {
        DBINSTANCE = null;
    }

}
