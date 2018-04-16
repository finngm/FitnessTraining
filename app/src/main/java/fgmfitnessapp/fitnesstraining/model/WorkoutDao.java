package fgmfitnessapp.fitnesstraining.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface WorkoutDao {
    @Query("SELECT * FROM workout")
    List<Workout> getAllWorkouts();

    @Query("SELECT * FROM workout WHERE user_created")
    List<Workout> getUserCreatedWorkouts();

    @Insert
    void insertAll(Workout... workouts);
}
