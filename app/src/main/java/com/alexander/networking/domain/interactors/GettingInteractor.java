package com.alexander.networking.domain.interactors;

import com.alexander.networking.data.model.Weather;
import com.alexander.networking.domain.executors.Executor;
import com.alexander.networking.domain.executors.MainThread;
import com.alexander.networking.domain.repository.WeatherRepository;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class GettingInteractor extends AbstractInteractor implements Interactor {

    private IGettingInteractor.Callback callback;
    private WeatherRepository repository;
    private List <Weather> forecast;

    public GettingInteractor(Executor executor, MainThread mainThread, IGettingInteractor.Callback callback,
                             WeatherRepository repository) {
        super(executor, mainThread);
        this.callback = callback;
        this.repository = repository;
    }

    @Override
    public void run(){
        try {
            forecast = repository.getWeather();
        } catch (IOException e){
            Logger.getLogger(GettingInteractor.class.getName()).info(e.getMessage());
        }
        if (forecast == null)
            notifyError();
        else postWeather(forecast);
    }

    private void notifyError(){
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onFailureGetWeather();
            }
        });
    }

    private void postWeather(final List<Weather> forecast){
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onGetWeather(forecast);
            }
        });
    }
}
