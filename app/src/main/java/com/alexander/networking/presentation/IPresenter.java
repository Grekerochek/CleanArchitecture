package com.alexander.networking.presentation;

import com.alexander.networking.data.model.Weather;

import java.util.List;

public interface IPresenter {
    void getWeatherFromRep();

    interface View{
        void showProgress();
        void hideProgress();
        void showError();
        void showWeather(List<Weather> forecast);
    }
}
