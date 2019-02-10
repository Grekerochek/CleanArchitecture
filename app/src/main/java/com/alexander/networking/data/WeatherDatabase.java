package com.alexander.networking.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.alexander.networking.data.WeatherDAO;
import com.alexander.networking.data.model.Weather;

@Database(entities = Weather.class, version = 1)
public abstract class WeatherDatabase extends RoomDatabase {

    public abstract WeatherDAO getWeatherDAO();
}