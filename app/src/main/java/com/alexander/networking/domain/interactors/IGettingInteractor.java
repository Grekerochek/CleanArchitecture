package com.alexander.networking.domain.interactors;
import com.alexander.networking.data.model.Weather;

import java.util.List;

public interface IGettingInteractor extends Interactor {
    interface Callback{
        void onGetWeather(List<Weather> forecast);
        void onFailureGetWeather();
    }
}
