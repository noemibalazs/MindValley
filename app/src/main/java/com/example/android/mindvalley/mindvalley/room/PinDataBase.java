package com.example.android.mindvalley.mindvalley.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {PinEntity.class}, version = 1, exportSchema = false)
public abstract class PinDataBase extends RoomDatabase {

    private static final String PIN_DATABASE = "pin_database";
    private static final Object LOCK = new Object();
    private static PinDataBase spinDataBase;

    public static PinDataBase getPinDataBase(Context context){
        if (spinDataBase == null){
            synchronized (LOCK){
                spinDataBase = Room.databaseBuilder(context.getApplicationContext(),
                        PinDataBase.class, PinDataBase.PIN_DATABASE)
                        .build();
            }
        }

        return spinDataBase;
    }

  public abstract PinDao pinDao();
}
