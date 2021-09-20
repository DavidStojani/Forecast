package com.example.forecast.Service;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.forecast.Model.CustomerModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherDataService {


    private static final String QUERY_FOR_CITY = "https://www.metaweather.com/api/location/";
    private static final String QUERY_FOR_ID = "https://www.metaweather.com/api/location/search/?query=";
    Context context;
    CustomerModel city = new CustomerModel();



    public WeatherDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListenerForID {
        void onError(String message);

        void onResponse(int cityId);
    }


    public void getCityId (String cityName, VolleyResponseListenerForID volleyResponseListener ) {
        String url = QUERY_FOR_ID + cityName;
        int cityId = 0;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            try {
                //Save the city ID
                JSONObject query = response.getJSONObject(0);
                city.setId(query.getInt("woeid"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            volleyResponseListener.onResponse(cityId);
        }, error -> Toast.makeText(context, "Id could not be assigned", Toast.LENGTH_SHORT).show());

        //Add the request to the queue
        TaskQueueSingleton.getInstance(context).addToRequestQueue(request);
    }

    public interface VolleyResponseListenerForWeather {
        void onError(String message);

        void onResponse(CustomerModel city);
    }


    public void getWeatherConditionById(int cityID, VolleyResponseListenerForWeather volleyResponseListener){

        String url = QUERY_FOR_CITY + cityID;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {

            try {

                //Save the weather condition for the next 48 hours

                JSONArray days = response.getJSONArray("consolidated_weather");
                JSONObject firstDay = days.getJSONObject(0);
                city.setWeather_state_first(firstDay.getString("weather_state_name"));
                JSONObject secondDay = days.getJSONObject(1);
                city.setWeather_state_second(secondDay.getString("weather_state_name"));

                System.out.println("CITY STATE WEATHER"+city.getWeather_state_first());

            } catch (JSONException e) {
                e.printStackTrace();
            }

            volleyResponseListener.onResponse(city);
        }, error -> Toast.makeText(context, "Weather state was not assigned", Toast.LENGTH_SHORT).show());

        // Add the request to the RequestQueue.
        TaskQueueSingleton.getInstance(context).addToRequestQueue(request);
    }

    public interface GetWeatherCondByNameCallBack {
        void onError(String error);
        void onResponse(CustomerModel city);
    }

    public void getWeatherConditionByName (String cityName, GetWeatherCondByNameCallBack callBack) {
        //Assign the city an ID. On the response than call the the function to get the weather condition based on that ID.
        //CallBacks nested, better solution possible.
        getCityId(cityName, new VolleyResponseListenerForID() {
            @Override
            public void onError(String message) {

            }

            @Override
            public void onResponse(int ityId) {
                getWeatherConditionById(city.getId(), new VolleyResponseListenerForWeather() {
                    @Override
                    public void onError(String message) {

                    }

                    @Override
                    public void onResponse(CustomerModel city) {
                        callBack.onResponse(city);
                    }
                });
            }
        });

    }


}
