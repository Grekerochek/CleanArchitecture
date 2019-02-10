package com.alexander.networking.domain.executors;

public interface MainThread {
    void post(Runnable runnable);
}
