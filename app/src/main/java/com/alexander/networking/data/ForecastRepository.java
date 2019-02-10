package com.alexander.networking.data;

import com.alexander.networking.App;
import com.alexander.networking.data.model.Weather;
import com.alexander.networking.domain.repository.WeatherRepository;

import java.io.IOException;
import java.util.List;

public class ForecastRepository implements WeatherRepository {

    private WeatherDatabase db;
    private List<Weather> forecasts;

    @Override
    public List<Weather> getWeather(){
        db = App.getInstance().getDatabase();
        try {
            forecasts = new ApiMapper(new RetrofitHelper()).getWeather();
            if (forecasts != null && !forecasts.isEmpty()) {
                db.getWeatherDAO().delete();
                db.getWeatherDAO().insert(forecasts);
            }
        } catch (IOException e){
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
