package com.alexander.networking.domain.repository;

import com.alexander.networking.data.model.Weather;
import java.util.List;

public interface WeatherRepository {
    List<Weather> getWeather();
    Weather getItemWeather(long time);
}
