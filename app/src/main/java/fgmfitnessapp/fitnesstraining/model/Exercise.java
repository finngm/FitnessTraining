package fgmfitnessapp.fitnesstraining.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.Objects;

@Entity(indices = {@Index(value = {"exercise_name"})})
public class Exercise implements Parcelable{
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "exercise_name")
    private String exerciseName;

    @ColumnInfo(name = "body_part")
    private String bodyPart;

    @ColumnInfo(name = "repetitions")
    private int repetitions;

    @ColumnInfo(name = "image_id")
    private int image_id;

    @ColumnInfo(name = "times_completed")
    private int timesCompleted;

    public Exercise(@NonNull String exerciseName,
                    String bodyPart,
                    int repetitions,
                    int image_id,
                    int timesCompleted) {
        this.exerciseName = exerciseName;
        this.bodyPart = bodyPart;
        this.repetitions = repetitions;
        this.image_id = image_id;
        this.timesCompleted = timesCompleted;
    }

    public Exercise(Parcel in) {
        exerciseName = in.readString();
        bodyPart    = in.readString();
        repetitions = in.readInt();
        image_id = in.readInt();
        timesCompleted = in.readInt();
    }

    @NonNull
    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(@NonNull String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
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
        out.writeString(exerciseName);
        out.writeString(bodyPart);
        out.writeInt(repetitions);
        out.writeInt(image_id);
        out.writeInt(timesCompleted);
    }

    //for arrays and creating from a parcel
    public static final Parcelable.Creator<Exercise> CREATOR = new Parcelable.Creator<Exercise>() {
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }
        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };

    /************************
     * equals/hash/toString *
     ************************/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercise exercise = (Exercise) o;
        return repetitions == exercise.repetitions &&
                image_id == exercise.image_id &&
                Objects.equals(exerciseName, exercise.exerciseName) &&
                Objects.equals(bodyPart, exercise.bodyPart);
    }

    @Override
    public int hashCode() {

        return Objects.hash(exerciseName, bodyPart, repetitions, image_id);
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "exerciseName='" + exerciseName + '\'' +
                ", bodyPart='" + bodyPart + '\'' +
                ", repetitions=" + repetitions +
                ", image_id=" + image_id +
                '}';
    }

}
