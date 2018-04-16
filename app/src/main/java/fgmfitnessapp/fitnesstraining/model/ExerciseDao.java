package fgmfitnessapp.fitnesstraining.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ExerciseDao {
    @Query("SELECT * FROM exercise")
    List<Exercise> getAllExercises();

    @Insert
    void insertAll(Exercise... exercises);
}
