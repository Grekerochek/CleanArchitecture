package com.alexander.networking.presentation.weatherscreen;

import com.alexander.networking.data.model.Weather;
import com.alexander.networking.domain.executors.Executor;
import com.alexander.networking.domain.executors.MainThread;
import com.alexander.networking.domain.interactors.GettingInteractor;
import com.alexander.networking.domain.interactors.IGettingInteractor;
import com.alexander.networking.domain.interactors.Interactor;
import com.alexander.networking.domain.repository.WeatherRepository;
import com.alexander.networking.presentation.AbstractPresenter;
import com.alexander.networking.presentation.IPresenter;

import java.util.List;

public class WeatherPresenter extends AbstractPresenter implements IPresenter, IGettingInteractor.Callback {

    private WeatherRepository repository;
    private IPresenter.View view;

    public WeatherPresenter(Executor executor, MainThread mainThread, View view,
                            WeatherRepository repository){
        super(executor, mainThread);
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void getWeatherFromRep() {
        view.showProgress();
        Interactor interactor = new GettingInteractor(executor, mainThread, this,
                repository);
        interactor.execute();
    }

    @Override
    public void onGetWeather(List<Weather> forecast) {
        view.hideProgress();
        view.showWeather(forecast);
    }

    @Override
    public void onFailureGetWeather() {
        view.hideProgress();
        view.showError();
    }
}
