package fgmfitnessapp.fitnesstraining.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "workout_exercise_join",
        foreignKeys = {
                @ForeignKey(entity = Workout.class,
                        parentColumns = "workout_name",
                        childColumns = "workoutName"),
                @ForeignKey(entity = Exercise.class,
                        parentColumns = "exercise_name",
                        childColumns = "exerciseName")
        },
        indices = {
                @Index(value = {"workoutName"}),
                @Index(value = {"exerciseName"})
        }
        )
public class WorkoutExerciseJoin {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private final int id;
    @NonNull
    private final String workoutName;
    @NonNull
    private final String exerciseName;

    public WorkoutExerciseJoin(int id, String workoutName, String exerciseName) {
        this.id = id;
        this.workoutName = workoutName;
        this.exerciseName = exerciseName;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public String getExerciseName() {
        return exerciseName;
    }

}
