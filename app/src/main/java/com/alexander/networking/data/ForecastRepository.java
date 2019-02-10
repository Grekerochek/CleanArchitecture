package com.alexander.networking.data;

import android.arch.persistence.room.Room;

import com.alexander.networking.App;
import com.alexander.networking.data.model.Weather;
import com.alexander.networking.domain.repository.WeatherRepository;

import java.io.IOException;
import java.util.List;

public class ForecastRepository implements WeatherRepository {

    private WeatherDatabase db;
    private List<Weather> forecasts;

    @Override
    public List<Weather> getWeather() throws IOException {
        db = App.getInstance().getDatabase();
        forecasts = new ApiMapper(new RetrofitHelper()).getWeather();
        if (forecasts != null) {
            db.getWeatherDAO().insert(forecasts);
        }
        else {
            forecasts = db.getWeatherDAO().getForecast();
        }
        return forecasts;
    }

    @Override
    public Weather getItemWeather(long time) {
        db = App.getInstance().getDatabase();
        return db.getWeatherDAO().getWeather(time);
    }
}
