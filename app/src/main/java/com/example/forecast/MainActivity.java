package com.example.forecast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



//a click to test the api and the async
    public void onClick(View view) {
        WeatherDataService weatherDataService = new WeatherDataService(this);

        String weatherCondition = weatherDataService.getWeatherCondition(44418);

    }
}