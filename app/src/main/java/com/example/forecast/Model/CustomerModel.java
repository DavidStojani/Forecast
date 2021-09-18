package com.example.forecast.Model;

public class CustomerModel {
    private String name;
    private int id;
    private String weather_state_first;
    private String weather_state_second;
    private int risk;

    public CustomerModel(String name, int id, String weather_state_first, String weather_state_second) {
        this.name = name;
        this.id = id;
        this.weather_state_first = weather_state_first;
        this.weather_state_second = weather_state_second;
    }
    public CustomerModel() {
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }


    public int getRisk() {
        return this.risk;
    }

    public int calcWeather_risk() {

        switch (weather_state_first) {
            case "sn":
                switch (weather_state_second) {
                    case "sn": return 100;
                    case "sl": return 70;
                    default:   return 30;
                }
            case "sl":
                switch (weather_state_second) {
                    case "sn": return 70;
                    case "sl": return 50;
                    default:   return 10;
                }
            default:
                switch (weather_state_second) {
                    case "sn": return 30;
                    case "sl": return 10;
                    default:   return 5;

                }

        }
    }

    public void setName(String name) {
        this.name = name;
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
        return "Snowrisk: " + risk +"%";
    }
}
