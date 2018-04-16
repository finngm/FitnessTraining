package fgmfitnessapp.fitnesstraining.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Objects;

@Entity(indices = {@Index(value = {"exercise_name"})})
public class Exercise {
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

    public Exercise(@NonNull String exerciseName, String bodyPart, int repetitions, int image_id) {
        this.exerciseName = exerciseName;
        this.bodyPart = bodyPart;
        this.repetitions = repetitions;
        this.image_id = image_id;
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
