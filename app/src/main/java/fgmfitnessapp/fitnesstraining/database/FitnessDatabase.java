package fgmfitnessapp.fitnesstraining.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

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

        final RoomDatabase.Callback dbCallback = new RoomDatabase.Callback(){

            public void onCreate (final SupportSQLiteDatabase db){
                // create default database entries in background thread
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        createDefaultWorkouts((FitnessDatabase) db);
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

    private static void createDefaultWorkouts(FitnessDatabase db) {
        // Create default exercises
        // Abs
        Exercise exercise1 = addExercise(db, "Sit Ups", "Abs", 10, 0);
        Exercise exercise2 = addExercise(db,"Reverse Crunches", "Abs", 10, 1);
        Exercise exercise3 = addExercise(db,"Bicycle Crunches", "Abs", 10, 2);

        // Quads
        Exercise exercise4 = addExercise(db,"Lunges", "Quads", 10, 3);
        Exercise exercise5 = addExercise(db,"High Knees", "Quads", 20, 4);
        Exercise exercise6 = addExercise(db,"Turning Kicks", "Quads", 10, 5);

        // Glutes
        Exercise exercise7 = addExercise(db,"Squats", "Glutes", 20, 6);
        Exercise exercise8 = addExercise(db,"Donkey Kicks", "Glutes", 20, 7);
        Exercise exercise9 = addExercise(db,"Bridges", "Glutes", 10, 8);

        // Triceps
        Exercise exercise10 = addExercise(db,"Close Grip Press-Ups", "Triceps", 10, 9);
        Exercise exercise11 = addExercise(db,"Tricep Dips", "Triceps", 10, 10);
        Exercise exercise12 = addExercise(db,"Tricep Extensions", "Triceps", 10, 11);

        // Biceps
        Exercise exercise13 = addExercise(db,"Leg Curls", "Biceps", 10, 12);
        Exercise exercise14 = addExercise(db,"Chin-Ups", "Biceps", 10, 13);
        Exercise exercise15 = addExercise(db,"Doorframe Rows", "Biceps", 10, 14);

        // Back
        Exercise exercise16 = addExercise(db,"Pull-Ups", "Back", 10, 15);
        Exercise exercise17 = addExercise(db,"Elbow Lifts", "Back", 10, 16);
        Exercise exercise18 = addExercise(db,"Superman", "Back", 10, 17);

        // Chest
        Exercise exercise19 = addExercise(db,"Press-Ups", "Chest", 10, 18);
        Exercise exercise20 = addExercise(db,"Plank Rotations", "Chest", 10, 19);
        Exercise exercise21 = addExercise(db,"Chest Squeezes", "Chest", 10, 20);

        // Create default workouts
        addWorkout(db, "Abs", 1, 25, groupExercises(exercise1, exercise2, exercise3), false);
        addWorkout(db, "Quads", 1, 30, groupExercises(exercise4, exercise5, exercise6), false);
        addWorkout(db, "Glutes", 1, 30, groupExercises(exercise7, exercise8, exercise9), false);
        addWorkout(db, "Triceps", 1, 25, groupExercises(exercise10, exercise11, exercise12), false);
        addWorkout(db, "Biceps", 1, 20, groupExercises(exercise13, exercise14, exercise15), false);
        addWorkout(db, "Back", 1, 25, groupExercises(exercise16, exercise17, exercise18), false);
        addWorkout(db, "Chest", 1, 20, groupExercises(exercise19, exercise20, exercise21), false);
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

    @Override
    public void init(DatabaseConfiguration config) {
        super.init(config);
    }

}
