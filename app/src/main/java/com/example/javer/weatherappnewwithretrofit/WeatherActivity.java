package com.example.javer.weatherappnewwithretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.javer.weatherappnewwithretrofit.Model.WeatherData;
import com.example.javer.weatherappnewwithretrofit.View.WeatherAdapter;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class WeatherActivity extends AppCompatActivity {

    WeatherData dataObj;
    TextView weatherState;
    TextView tempDay;
    TextView tempNight;
    TextView today;
    MetaWeatherService service;

    ConstraintLayout todaysWeather;

    ImageView imageState;
    String location;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        today = (TextView) findViewById(R.id.Date);
        tempDay = (TextView) findViewById(R.id.temperatureDay);
        tempNight = (TextView) findViewById(R.id.temperatureNight);
        weatherState = (TextView) findViewById(R.id.weatherState);

        imageState = (ImageView) findViewById(R.id.imageState);
        todaysWeather = (ConstraintLayout) findViewById(R.id.todaysWeather);

        if(service == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://www.metaweather.com/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
            service = retrofit.create(MetaWeatherService.class);
        }

        int woeid = Integer.parseInt(getIntent().getStringExtra("woeid"));

        service.loadWeatherData(woeid).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {

                    JSONObject jsonObject = new JSONObject(response.body());
                    JSONArray array = jsonObject.getJSONArray("consolidated_weather");

                    JSONObject obj = array.getJSONObject(0);
                    initializeDataObj(obj);

                    location = jsonObject.getString("title");


                    today.setText(dataObj.getDate());
                    tempDay.setText(dataObj.getMaxTemp() + "");
                    tempNight.setText(dataObj.getMinTemp() + "");
                    weatherState.setText(dataObj.getWeatherState());
                    Picasso.get().load(dataObj.getUrl()).into(imageState);

                    todaysWeather.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            makeIntent(dataObj);
                        }
                    });

                    recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                    recyclerView.setAdapter(new WeatherAdapter(array, getApplicationContext(), location));
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });




    }

    public void makeIntent(WeatherData obj){
        Intent i = new Intent(getApplicationContext(), WeatherDetailActivity.class);
        i.putExtra("ITEM_EXTRA", obj);
        i.putExtra("location", location);
        startActivity(i);
    }

    private void initializeDataObj(JSONObject obj){

        try{
            int minTemp = (int) obj.getDouble("min_temp");
            int maxTemp = (int) obj.getDouble("max_temp");
            int avgTemp = (int) obj.getDouble("the_temp");
            String abbr = obj.getString("weather_state_abbr");
            String state = obj.getString("weather_state_name");
            String weatherDate = obj.getString("applicable_date");
            String windSpeed = String.valueOf((int) Float.parseFloat(obj.getString("wind_speed")));
            String pressure = String.valueOf((int) Float.parseFloat(obj.getString("air_pressure")));
            String humidity = String.valueOf((int) Float.parseFloat(obj.getString("humidity")));

            dataObj = new WeatherData(maxTemp, minTemp, avgTemp, state, abbr, weatherDate, humidity, pressure, windSpeed);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
