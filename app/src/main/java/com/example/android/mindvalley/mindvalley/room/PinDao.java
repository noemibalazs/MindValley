package com.example.android.mindvalley.mindvalley.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;


import java.util.List;

@Dao
public interface PinDao {

    @Query("SELECT * FROM pin")
    LiveData<List<PinEntity>> loadFavoritePins();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPin(PinEntity entity);

    @Delete
    void deletePin(PinEntity entity);
}
