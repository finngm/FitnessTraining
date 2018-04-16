package fgmfitnessapp.fitnesstraining.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Objects;

@Entity(indices = {@Index(value = {"workout_name"})})
public class Workout {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "workout_name")
    private String workoutName;

    @ColumnInfo(name = "intensity_level")
    private int level;

    @ColumnInfo(name = "rest_time")
    private int restTime;

    //@ColumnInfo(name = "exercises")
    //private List<Exercise> exercises;

    @ColumnInfo(name = "user_created")
    private boolean userCreated;

    public Workout(@NonNull String workoutName, int level, int restTime, boolean userCreated) {
        this.workoutName = workoutName;
        this.level = level;
        this.restTime = restTime;
        //this.exercises = exercises;
        this.userCreated = userCreated;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRestTime() {
        return restTime;
    }

    public void setRestTime(int restTime) {
        this.restTime = restTime;
    }

//    public List<Exercise> getExercises() {
//        return exercises;
//    }
//
//    public void setExercises(List<Exercise> exercises) {
//        this.exercises = exercises;
//    }

    public boolean isUserCreated() {
        return userCreated;
    }

    public void setUserCreated(boolean userCreated) {
        this.userCreated = userCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Workout workout = (Workout) o;
        return level == workout.level &&
                restTime == workout.restTime &&
                userCreated == workout.userCreated &&
                Objects.equals(workoutName, workout.workoutName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(workoutName, level, restTime, userCreated);
    }

    @Override
    public String toString() {
        return "Workout{" +
                "name='" + workoutName + '\'' +
                ", level=" + level +
                ", restTime=" + restTime +
                //", exercises=" + exercises +
                ", userCreated=" + userCreated +
                '}';
    }

}
