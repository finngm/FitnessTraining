package fgmfitnessapp.fitnesstraining.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface WorkoutExerciseJoinDao {
    @Insert
    void insert(WorkoutExerciseJoin workoutExerciseJoin);

    @Query("SELECT * FROM workout INNER JOIN workout_exercise_join ON " +
            "workout.workout_name = workout_exercise_join.workoutName WHERE " +
            "workout_exercise_join.exerciseName=:exerciseName")
    List<Workout> getWorkoutsForExercises(final String exerciseName);

    @Query("SELECT * FROM exercise INNER JOIN workout_exercise_join ON " +
            "exercise.exercise_Name = workout_exercise_join.exerciseName WHERE " +
            "workout_exercise_join.workoutName=:workoutName")
            List<Exercise> getExercisesForWorkouts(final String workoutName);
}
