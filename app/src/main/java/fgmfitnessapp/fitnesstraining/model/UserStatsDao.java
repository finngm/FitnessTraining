package fgmfitnessapp.fitnesstraining.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

@Dao
public interface UserStatsDao {
    @Query("SELECT * FROM userStats")
    UserStats getUserStats();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserStats userStats);
}
