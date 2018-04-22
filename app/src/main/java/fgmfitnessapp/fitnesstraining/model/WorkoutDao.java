package fgmfitnessapp.fitnesstraining.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface WorkoutDao {
    @Query("SELECT * FROM workout")
    List<Workout> getAllWorkouts();

    @Query("SELECT workout_name FROM workout WHERE workout_name=:name")
    String getWorkoutNameWithName(String name);

    @Query("SELECT * FROM workout WHERE workout_name=:name")
    Workout getWorkoutWithName(String name);

    @Insert
    void insertAll(Workout... workouts);
}
