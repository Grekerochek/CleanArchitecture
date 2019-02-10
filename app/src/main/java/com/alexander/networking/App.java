package com.alexander.networking;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.alexander.networking.data.WeatherDatabase;

public class App extends Application {
    public static App instance;

    private WeatherDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, WeatherDatabase.class, "my_database")
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public WeatherDatabase getDatabase() {
        return database;
    }
}
