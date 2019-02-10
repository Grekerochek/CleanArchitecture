package com.alexander.networking.threads;

import android.os.Handler;
import android.os.Looper;

import com.alexander.networking.domain.executors.MainThread;

public class MainThreadAnd implements MainThread {

    private static MainThread mainThread;

    private Handler handler;

    private MainThreadAnd() {
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        handler.post(runnable);
    }

    public static MainThread getInstance() {
        if (mainThread == null) {
            mainThread = new MainThreadAnd();
        }

        return mainThread;
    }
}
