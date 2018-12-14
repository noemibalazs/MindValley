package com.example.android.mindvalley.mindvalley.room;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class PinViewModel extends AndroidViewModel {

    private LiveData<List<PinEntity>> pinEntities;

    public PinViewModel(@NonNull Application application) {
        super(application);

        PinDataBase dataBase = PinDataBase.getPinDataBase(getApplication());
        pinEntities = dataBase.pinDao().loadFavoritePins();
    }

    public LiveData<List<PinEntity>> getEntities(){
        return pinEntities;
    }
}
