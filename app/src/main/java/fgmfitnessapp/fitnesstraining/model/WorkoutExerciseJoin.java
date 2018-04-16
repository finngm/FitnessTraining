package fgmfitnessapp.fitnesstraining.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;

@Entity(tableName = "workout_exercise_join",
        primaryKeys = { "workoutName", "exerciseName" },
        foreignKeys = {
                @ForeignKey(entity = Workout.class,
                        parentColumns = "workout_name",
                        childColumns = "workoutName"),
                @ForeignKey(entity = Exercise.class,
                        parentColumns = "exercise_name",
                        childColumns = "exerciseName")
        },
        indices = {@Index(value = {"workoutName", "exerciseName"})})
public class WorkoutExerciseJoin {
    @NonNull
    private final String workoutName;
    @NonNull
    private final String exerciseName;

    public WorkoutExerciseJoin(String workoutName, String exerciseName) {
        this.workoutName = workoutName;
        this.exerciseName = exerciseName;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public String getExerciseName() {
        return exerciseName;
    }
}
