package com.example.forecast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forecast.Model.CustomerModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    WeatherDataService weatherDataService = new WeatherDataService(this);
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.resultText);

    }


//a click to test the api and the async
    public void onClick(View view) {

            weatherDataService.getWeatherCondition(44418, new WeatherDataService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this,"SmthWentWrong" , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(CustomerModel city) {
                //Toast.makeText(MainActivity.this, city.toString(), Toast.LENGTH_SHORT).show();
                textView.setText(city.toString());
            }
        });
    }
}