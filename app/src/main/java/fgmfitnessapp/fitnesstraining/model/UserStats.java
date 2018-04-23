package fgmfitnessapp.fitnesstraining.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(indices = {@Index(value = {"id"})})
public class UserStats {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "workouts_completed")
    private int workoutsCompleted;

    @ColumnInfo(name = "exercises_completed")
    private int exercisesCompleted;

    public UserStats(@NonNull String id, int workoutsCompleted, int exercisesCompleted) {
        this.id = id;
        this.workoutsCompleted = workoutsCompleted;
        this.exercisesCompleted = exercisesCompleted;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public int getWorkoutsCompleted() {
        return workoutsCompleted;
    }

    public void setWorkoutsCompleted(int workoutsCompleted) {
        this.workoutsCompleted = workoutsCompleted;
    }

    public int getExercisesCompleted() {
        return exercisesCompleted;
    }

    public void setExercisesCompleted(int exercisesCompleted) {
        this.exercisesCompleted = exercisesCompleted;
    }
}
