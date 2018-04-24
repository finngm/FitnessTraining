package fgmfitnessapp.fitnesstraining.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Objects;

@Entity
public class WorkoutLog {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "workout_name")
    private String workoutName;

    @ColumnInfo(name = "date_completed")
    private String dateCompleted;

    @ColumnInfo(name = "time_completed")
    private String timeCompleted;

    public WorkoutLog(@NonNull int id, String workoutName, String dateCompleted, String timeCompleted) {
        this.id = id;
        this.workoutName = workoutName;
        this.dateCompleted = dateCompleted;
        this.timeCompleted = timeCompleted;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(String dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public String getTimeCompleted() {
        return timeCompleted;
    }

    public void setTimeCompleted(String timeCompleted) {
        this.timeCompleted = timeCompleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkoutLog that = (WorkoutLog) o;
        return id == that.id &&
                Objects.equals(workoutName, that.workoutName) &&
                Objects.equals(dateCompleted, that.dateCompleted) &&
                Objects.equals(timeCompleted, that.timeCompleted);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, workoutName, dateCompleted, timeCompleted);
    }
}
