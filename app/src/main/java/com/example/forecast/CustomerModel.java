package com.example.forecast;

import androidx.annotation.NonNull;

public class CustomerModel {
    private String name;
    private int id;
    private String weather_state_first;
    private String weather_state_second;
    private int risk;

    public CustomerModel() {
    }


    public int getId() {
        return id;
    }

    public int calcWeather_risk() {

        switch (weather_state_first) {
            case "Snow":
                switch (weather_state_second) {
                    case "Snow": return 100;
                    case "Sleet": return 70;
                    default:   return 30;
                }
            case "Sleet":
                switch (weather_state_second) {
                    case "Snow": return 70;
                    case "Sleet": return 50;
                    default:   return 10;
                }
            default:
                switch (weather_state_second) {
                    case "Snow": return 30;
                    case "Sleet": return 10;
                    default:   return 5;

                }

        }
    }


    public String getWeather_state_first() {
        return weather_state_first;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWeather_state_first(String weather_state_name) {
        this.weather_state_first = weather_state_name;
    }

    public void setWeather_state_second(String weather_state_second) {
        this.weather_state_second = weather_state_second;
        this.risk = calcWeather_risk();
    }


    @Override
    public String toString() {
        return "Condition: "+ weather_state_first +"\r Snowrisk: " + risk +"%";
    }
}
