package com.alexander.networking.domain.interactors;

import com.alexander.networking.data.model.Weather;
import com.alexander.networking.domain.executors.Executor;
import com.alexander.networking.domain.executors.MainThread;
import com.alexander.networking.domain.repository.WeatherRepository;

import java.util.List;

public class GettingInteractor extends AbstractInteractor implements Interactor {

    private IGettingInteractor.Callback callback;
    private WeatherRepository repository;

    public GettingInteractor(Executor executor, MainThread mainThread, IGettingInteractor.Callback callback,
                             WeatherRepository repository) {
        super(executor, mainThread);
        this.callback = callback;
        this.repository = repository;
    }

    @Override
    public void run(){
        List<Weather> forecast = repository.getWeather();
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
