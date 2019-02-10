package com.alexander.networking.domain.repository;

import com.alexander.networking.data.model.Weather;

import java.io.IOException;
import java.util.List;

public interface WeatherRepository {
    List<Weather> getWeather() throws IOException;
    Weather getItemWeather(long time);
}
