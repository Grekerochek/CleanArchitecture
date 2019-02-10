package com.alexander.networking.presentation.detailscreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.alexander.networking.R;
import com.alexander.networking.ServiceDB;
import com.alexander.networking.data.ForecastRepository;
import com.alexander.networking.data.model.Weather;
import com.alexander.networking.domain.executors.MainThread;
import com.alexander.networking.domain.executors.ThreadExecutor;
import com.alexander.networking.presentation.IPresenter;
import com.alexander.networking.threads.MainThreadAnd;

import java.util.List;

public class DetailsActivity extends AppCompatActivity implements IPresenter.View {

    private static String TIME = "time";
    private static String VALUE = "value";
    private String time;
    private long timeUn;
    private Weather weather;
    private Toolbar toolbar;
    private TextView text;
    private ProgressBar progressBar;

    private IPresenter presenter;

    public static final Intent newIntent(Context context, String time, long timeUn){
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(VALUE, time);
        intent.putExtra(TIME, timeUn);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);
        initViews();
        getData();
        setToolbar();
        presenter = new DetailPresenter(ThreadExecutor.getInstance(), MainThreadAnd.getInstance(), this, new ForecastRepository(), timeUn);
        presenter.getWeatherFromRep();

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError() {
        Toast.makeText(this, getText(R.string.error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showWeather(List<Weather> forecast) {
        if (forecast != null && !forecast.isEmpty()){
            text.setText(forecast.get(0).toString());
        }
    }

    private void initViews(){
        text = findViewById(R.id.text);
        toolbar = findViewById(R.id.toolbar);
        progressBar = findViewById(R.id.load);
    }

    private void getData(){
        time = getIntent().getStringExtra(VALUE);
        timeUn = getIntent().getLongExtra(TIME, 0);
    }

    private void setToolbar(){
        toolbar.setTitle(time);
        setSupportActionBar(toolbar);
    }
}
