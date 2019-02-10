package com.alexander.networking.domain.interactors;

import com.alexander.networking.data.model.Weather;
import com.alexander.networking.domain.executors.Executor;
import com.alexander.networking.domain.executors.MainThread;
import com.alexander.networking.domain.repository.WeatherRepository;

import java.util.ArrayList;
import java.util.List;

public class GetItemWeatherInteractor extends AbstractInteractor implements Interactor {

    private IGettingInteractor.Callback callback;
    private WeatherRepository repository;
    private Weather weather;
    private long time;

    public GetItemWeatherInteractor(Executor executor, MainThread mainThread, IGettingInteractor.Callback callback,
                             WeatherRepository repository, long time) {
        super(executor, mainThread);
        this.callback = callback;
        this.repository = repository;
        this.time = time;
    }

    @Override
    public void run(){
        weather= repository.getItemWeather(time);
        if (weather == null)
            notifyError();
        else postWeather(weather);
    }

    private void notifyError(){
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onFailureGetWeather();
            }
        });
    }

    private void postWeather(final Weather weather){
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                List<Weather> forecast = new ArrayList<>();
                forecast.add(weather);
                callback.onGetWeather(forecast);
            }
        });
    }
}
