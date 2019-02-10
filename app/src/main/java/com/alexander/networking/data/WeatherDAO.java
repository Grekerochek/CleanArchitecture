package com.alexander.networking.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.alexander.networking.data.model.Weather;

import java.util.List;

@Dao
public interface WeatherDAO {

    @Query("select * from forecast where time = :time")
    Weather getWeather(long time);

    @Query("select * from forecast")
    List<Weather> getForecast();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Weather weather);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Weather> forecast);

    @Delete
    void delete(Weather weather);

    @Query("delete from forecast")
    void delete();
}
