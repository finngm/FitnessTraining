package fgmfitnessapp.fitnesstraining.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ExerciseDao {
    @Query("SELECT * FROM exercise")
    List<Exercise> getAllExercises();

    @Query("SELECT * FROM exercise WHERE exercise_name=:name")
    Exercise getExerciseWithName(String name);

    @Insert
    void insertAll(Exercise... exercises);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void update(Exercise exercise);
}
