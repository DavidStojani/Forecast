package com.example.forecast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    WeatherDataService weatherDataService = new WeatherDataService(this);
    TextView textView;

    List<Button> buttonList;
    ViewGroup parentView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.resultText);
        parentView = (ViewGroup) textView.getParent();
        buttonList = new ArrayList<>();



        for (int i = 0; i < parentView.getChildCount(); i++)  {

            if (parentView.getChildAt(i).getClass().toString().endsWith("Button")) {
                buttonList.add((Button) parentView.getChildAt(i));
            }
        }

        for (int i = 0; i < buttonList.size(); i++) {
            int finalI = i;
            buttonList.get(i).setOnClickListener(view -> {

                String cityName = (String) buttonList.get(finalI).getText();
                weatherDataService.getWeatherConditionByName(cityName, new WeatherDataService.GetWeatherCondByNameCallBack() {
                    @Override
                    public void onError(String error) {
                        Toast.makeText(MainActivity.this,"Something went wrong!" , Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(CustomerModel city) {
                        textView.setText(city.toString());
                    }
                });
                Log.d("Button Clicked",Integer.toString(view.getId()));
            });
        }

    }

}