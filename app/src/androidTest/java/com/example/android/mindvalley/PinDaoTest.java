package com.example.android.mindvalley;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.example.android.mindvalley.mindvalley.room.PinDao;
import com.example.android.mindvalley.mindvalley.room.PinDataBase;
import com.example.android.mindvalley.mindvalley.room.PinEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

@RunWith(JUnit4.class)
public class PinDaoTest {

    private PinDao dao;
    private PinDataBase mDB;

    @Before
    public void createDB(){
        Context context = InstrumentationRegistry.getTargetContext();
        mDB = Room.inMemoryDatabaseBuilder(context, PinDataBase.class).build();
        dao = mDB.pinDao();
    }

    @After
    public void closeDB() throws IOException{
        mDB.close();
    }

    @Test
    public void write() throws IOException{
        PinEntity pinEntity = new PinEntity("", "John", "", "" );
        pinEntity.setId("abcd");
        pinEntity.setProfile("https://images.unsplash.com/profile-1464495186405-68089dcd96c3?ixlib=rb-0.3.5\\u0026q=80\\u0026fm=jpg\\u0026crop=faces\\u0026fit=crop\\u0026h=32\\u0026w=32\\u0026s=63f1d805cffccb834cf839c719d91702");
        pinEntity.setImage("https://images.unsplash.com/photo-1464550883968-cec281c19761?ixlib=rb-0.3.5\\u0026q=80\\u0026fm=jpg\\u0026crop=entropy\\u0026w=1080\\u0026fit=max\\u0026s=1881cd689e10e5dca28839e68678f432");
        mDB.pinDao().insertPin(pinEntity);
    }

}
