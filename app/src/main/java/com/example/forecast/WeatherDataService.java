package com.example.forecast;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.forecast.Model.CustomerModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class WeatherDataService {


    public static final String QUERY_FOR_CITY = "https://www.metaweather.com/api/location/";

    Context context;

    String humidity="";

    public WeatherDataService(Context context) {
        this.context = context;
    }

    public String getWeatherCondition(int cityID){

        String url = QUERY_FOR_CITY + cityID;


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    JSONArray days = response.getJSONArray("consolidated_weather");
                    JSONObject firstDay = days.getJSONObject(1);

                    //humidity assigned correctly but never returned
                    humidity = firstDay.getString("weather_state_name");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(context, humidity, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error came up", Toast.LENGTH_SHORT).show();

            }
        });



// Add the request to the RequestQueue.

        MySingleton.getInstance(context).addToRequestQueue(request);

        return humidity;
    }
//
//    public List<CustomerModel> getCityForecastByID(String cityID) {
//
//    }
//
//    public List<CustomerModel> getCityForecastByName(){
//
//    }


}
