package fgmfitnessapp.fitnesstraining.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.Objects;

@Entity(indices = {@Index(value = {"workout_name"})})
public class Workout implements Parcelable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "workout_name")
    private String workoutName;

    @ColumnInfo(name = "display_name")
    private String displayName;

    @ColumnInfo(name = "intensity_level")
    private int level;

    @ColumnInfo(name = "rest_time")
    private int restTime;

    @ColumnInfo(name = "user_created")
    private boolean userCreated;

    @ColumnInfo(name = "times_completed")
    private int timesCompleted;

    public Workout(@NonNull String workoutName,
                   String displayName,
                   int level,
                   int restTime,
                   boolean userCreated,
                   int timesCompleted) {
        this.workoutName = workoutName;
        this.displayName = displayName;
        this.level = level;
        this.restTime = restTime;
        this.userCreated = userCreated;
        this.timesCompleted = timesCompleted;
    }

    public Workout(Parcel in) {
        workoutName = in.readString();
        displayName = in.readString();
        level    = in.readInt();
        restTime = in.readInt();
        userCreated = in.readInt() == 1;
        timesCompleted = in.readInt();
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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

    public boolean isUserCreated() {
        return userCreated;
    }

    public void setUserCreated(boolean userCreated) {
        this.userCreated = userCreated;
    }

    public int getTimesCompleted() {
        return timesCompleted;
    }

    public void setTimesCompleted(int timesCompleted) {
        this.timesCompleted = timesCompleted;
    }

    /**********************
     * Parcelable methods *
     **********************/

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeString(workoutName);
        out.writeString(displayName);
        out.writeInt(level);
        out.writeInt(restTime);
        out.writeInt(userCreated ? 1 : 0);
        out.writeInt(timesCompleted);
    }

    //for arrays and creating from a parcel
    public static final Parcelable.Creator<Workout> CREATOR = new Parcelable.Creator<Workout>() {
        public Workout createFromParcel(Parcel in) {
            return new Workout(in);
        }
        public Workout[] newArray(int size) {
            return new Workout[size];
        }
    };

    /************************
     * equals/hash/toString *
     ************************/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Workout workout = (Workout) o;
        return level == workout.level &&
                restTime == workout.restTime &&
                userCreated == workout.userCreated &&
                timesCompleted == workout.timesCompleted &&
                Objects.equals(workoutName, workout.workoutName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(workoutName, level, restTime, userCreated, timesCompleted);
    }

    @Override
    public String toString() {
        return "Workout{" +
                "name='" + workoutName + '\'' +
                ", level=" + level +
                ", restTime=" + restTime +
                ", timesCompleted=" + timesCompleted +
                ", userCreated=" + userCreated +
                '}';
    }

}
