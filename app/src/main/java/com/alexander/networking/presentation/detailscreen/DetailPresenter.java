package com.alexander.networking.presentation.detailscreen;

import com.alexander.networking.data.model.Weather;
import com.alexander.networking.domain.executors.Executor;
import com.alexander.networking.domain.executors.MainThread;
import com.alexander.networking.domain.interactors.GetItemWeatherInteractor;
import com.alexander.networking.domain.interactors.IGettingInteractor;
import com.alexander.networking.domain.interactors.Interactor;
import com.alexander.networking.domain.repository.WeatherRepository;
import com.alexander.networking.presentation.AbstractPresenter;
import com.alexander.networking.presentation.IPresenter;

import java.util.List;

public class DetailPresenter extends AbstractPresenter implements IPresenter, IGettingInteractor.Callback {

    private WeatherRepository repository;
    private IPresenter.View view;
    private long time;

    public DetailPresenter(Executor executor, MainThread mainThread, View view,
                            WeatherRepository repository, long time){
        super(executor, mainThread);
        this.view = view;
        this.repository = repository;
        this.time = time;
    }

    @Override
    public void getWeatherFromRep() {
        view.showProgress();
        Interactor interactor = new GetItemWeatherInteractor(executor, mainThread, this,
                repository, time);
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
