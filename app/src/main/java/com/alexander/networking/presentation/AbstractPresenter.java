package com.alexander.networking.presentation;

import com.alexander.networking.domain.executors.Executor;
import com.alexander.networking.domain.executors.MainThread;

public abstract class AbstractPresenter {
    protected Executor executor;
    protected MainThread mainThread;

    public AbstractPresenter(Executor executor, MainThread mainThread) {
        this.executor = executor;
        this.mainThread = mainThread;
    }
}
