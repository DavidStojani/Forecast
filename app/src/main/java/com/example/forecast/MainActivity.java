package com.example.forecast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    WeatherDataService weatherDataService = new WeatherDataService(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



//a click to test the api and the async
    public void onClick(View view) {

            weatherDataService.getWeatherCondition(44418, new WeatherDataService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this,"SmthWentWrong" , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String weatherCondition) {
                Toast.makeText(MainActivity.this, weatherCondition, Toast.LENGTH_SHORT).show();
            }
        });

    }
}