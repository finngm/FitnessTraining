package fgmfitnessapp.fitnesstraining;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import fgmfitnessapp.fitnesstraining.database.FitnessDatabase;
import fgmfitnessapp.fitnesstraining.model.Exercise;
import fgmfitnessapp.fitnesstraining.model.ExerciseDao;
import fgmfitnessapp.fitnesstraining.model.UserStats;
import fgmfitnessapp.fitnesstraining.model.UserStatsDao;
import fgmfitnessapp.fitnesstraining.model.Workout;
import fgmfitnessapp.fitnesstraining.model.WorkoutDao;
import fgmfitnessapp.fitnesstraining.model.WorkoutExerciseJoin;
import fgmfitnessapp.fitnesstraining.model.WorkoutExerciseJoinDao;
import fgmfitnessapp.fitnesstraining.model.WorkoutLog;
import fgmfitnessapp.fitnesstraining.model.WorkoutLogDao;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    private FitnessDatabase mDatabase;
    private ExerciseDao mExerciseDao;
    private WorkoutDao mWorkoutDao;
    private WorkoutExerciseJoinDao mWorkoutExerciseJoinDao;
    private WorkoutLogDao mWorkoutLogDao;
    private UserStatsDao mUserStatsDao;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDatabase = Room.inMemoryDatabaseBuilder(context, FitnessDatabase.class).build();
        mExerciseDao = mDatabase.exerciseModel();
        mWorkoutDao = mDatabase.workoutModel();
        mWorkoutExerciseJoinDao = mDatabase.workoutExerciseJoinModel();
        mWorkoutLogDao = mDatabase.workoutLogModel();
        mUserStatsDao = mDatabase.userStatsModel();
    }

    @After
    public void closeDb() {
        mDatabase.close();
    }

    @Test
    public void insertAndReadExercise() throws Exception {
        Exercise exercise = new Exercise( "Sit Ups",
                "Abs",
                10,
                R.drawable.sit_ups,
                0);
        mExerciseDao.insertAll(exercise);
        Exercise byName = mExerciseDao.getExerciseWithName(exercise.getExerciseName());
        assertEquals(true, byName.equals(exercise));
    }

    @Test
    public void insertAndReadWorkout() {
        Workout workout = new Workout( "Abs",
                "Abs",
                1,
                25,
                false,
                0);
        mWorkoutDao.insertAll(workout);
        Workout byName = mWorkoutDao.getWorkoutWithName(workout.getWorkoutName());
        assertEquals(true, byName.equals(workout));
    }

    @Test
    public void insertAndReadWorkoutWithExercise() {
        Exercise exercise = new Exercise( "Sit Ups",
                "Abs",
                10,
                R.drawable.sit_ups,
                0);
        mExerciseDao.insertAll(exercise);
        Workout workout = new Workout( "Abs",
                "Abs",
                1,
                25,
                false,
                0);
        mWorkoutDao.insertAll(workout);
        WorkoutExerciseJoin workoutExerciseJoin = new WorkoutExerciseJoin(0,
                workout.getWorkoutName(), exercise.getExerciseName());
        mWorkoutExerciseJoinDao.insert(workoutExerciseJoin);
        List<Exercise> byName = mWorkoutExerciseJoinDao.getExercisesForWorkouts(workout.getWorkoutName());
        assertEquals(true, byName.get(0).equals(exercise));
    }

    @Test
    public void insertAndReadUserStats() {
        UserStats userStats = new UserStats("userStats",
                5,
                15);
        mUserStatsDao.insert(userStats);
        UserStats byId = mUserStatsDao.getUserStats();
        assertEquals(true, byId.equals(userStats));
    }

    @Test
    public void insertAndReadWorkoutLog() {
        WorkoutLog workoutLog = new WorkoutLog(1,
                "Abs",
                "23/04/2018",
                "23:59");
        mWorkoutLogDao.insertAll(workoutLog);
        List<WorkoutLog> byId = mWorkoutLogDao.getWorkoutLogs();
        assertEquals(true, byId.get(0).equals(workoutLog));
    }
}
