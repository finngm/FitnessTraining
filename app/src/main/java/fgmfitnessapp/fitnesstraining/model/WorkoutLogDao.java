package fgmfitnessapp.fitnesstraining.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface WorkoutLogDao {
    @Query("SELECT * FROM workoutlog")
    List<WorkoutLog> getWorkoutLogs();

    @Insert
    void insertAll(WorkoutLog... workoutLogs);
}
