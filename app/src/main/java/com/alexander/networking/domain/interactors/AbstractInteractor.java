package com.alexander.networking.domain.interactors;

import com.alexander.networking.domain.executors.Executor;
import com.alexander.networking.domain.executors.MainThread;

import java.io.IOException;

public abstract class AbstractInteractor implements Interactor {

    public Executor executor;
    public MainThread mainThread;

    protected volatile boolean isCanceled;
    protected volatile boolean isRunning;

    public AbstractInteractor(Executor executor, MainThread mainThread) {
        this.executor = executor;
        this.mainThread = mainThread;
    }

    public abstract void run();

    public void cancel() {
        isCanceled = true;
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void onFinished() {
        isRunning = false;
        isCanceled = false;
    }

    public void execute() {
        this.isRunning = true;
        executor.execute(this);
    }
}
