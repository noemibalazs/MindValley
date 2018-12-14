package com.example.android.mindvalley.mindvalley.room;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PinExecutor {

    private final Executor diskIo;
    private final Executor networkIo;
    private final Executor mainThread;
    private static final Object LOCK = new Object();
    private static PinExecutor pinExecutor;

    private PinExecutor(Executor diskIo, Executor networkIo, Executor mainThread){
        this.diskIo = diskIo;
        this.networkIo = networkIo;
        this.mainThread = mainThread;
    }

    public static PinExecutor getInstance(){
        if (pinExecutor == null){
            synchronized (LOCK){
                pinExecutor = new PinExecutor(Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(3),
                        new PinThread());
            }
        }
        return pinExecutor;
    }

    public Executor getDiskIo() {
        return diskIo;
    }

    public Executor getNetworkIo() {
        return networkIo;
    }

    public Executor getMainThread() {
        return mainThread;
    }

    private static class PinThread implements Executor{

        private Handler mainHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainHandler.post(command);

        }
    }
}
