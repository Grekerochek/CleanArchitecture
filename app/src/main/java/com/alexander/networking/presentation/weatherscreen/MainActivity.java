package com.alexander.networking.presentation.weatherscreen;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.alexander.networking.data.ForecastRepository;
import com.alexander.networking.domain.executors.ThreadExecutor;
import com.alexander.networking.presentation.CustomItemDecorator;
import com.alexander.networking.R;
import com.alexander.networking.data.model.Weather;
import com.alexander.networking.presentation.Adapter;
import com.alexander.networking.presentation.IPresenter;
import com.alexander.networking.threads.MainThreadAnd;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IPresenter.View {

    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private LinearLayoutManager manager;
    private List<Weather> forecasts;
    private ProgressBar progressBar;

    private IPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();
        initListeners();
        initRecyclerView();
        presenter = new WeatherPresenter(ThreadExecutor.getInstance(), MainThreadAnd.getInstance(),
                this, new ForecastRepository());
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
        adapter.setData(forecast);
    }

    private void initViews(){
        fab = findViewById(R.id.fab);
        progressBar = findViewById(R.id.load);
    }

    private void initListeners(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getWeatherFromRep();
            }
        });
    }

    private void initRecyclerView(){

        recyclerView = findViewById(R.id.recyclerView);
        forecasts = new ArrayList<>();
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new Adapter(this, forecasts);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new CustomItemDecorator(ContextCompat.getDrawable(getApplicationContext(), R.drawable.devider)));
    }


}
