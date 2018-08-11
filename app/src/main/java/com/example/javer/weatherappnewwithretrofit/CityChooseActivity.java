package com.example.javer.weatherappnewwithretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter.Factory.*;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class CityChooseActivity extends AppCompatActivity {

    TextInputLayout cityName;
    TextView errorText;
    String city;

    MetaWeatherService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_choose);

        cityName = (TextInputLayout) findViewById(R.id.CityNameLayout);
        errorText = (TextView) findViewById(R.id.errorText);


    }

    public void submit(View view){

        if(!(cityName.getEditText().getText().toString().trim().equals(""))){
            city = cityName.getEditText().getText().toString().trim().toLowerCase().replace(" ", "");


            if(service == null){
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://www.metaweather.com/")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .build();
                service = retrofit.create(MetaWeatherService.class);
            }


            service.loadCityData(city).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    try {

                        JSONArray jsonResponse = new JSONArray(response.body());

                        String woeid = jsonResponse.getJSONObject(0).getString("woeid");
                        Intent i = new Intent(getApplicationContext(), WeatherActivity.class);
                        i.putExtra("woeid", woeid);
                        startActivity(i);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                        errorText.setText("City not found. Re-enter city name.");
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    errorText.setText("City not found. Re-enter city name.");
                }
            });

        }
        else{
            errorText.setText("Enter city name.");
        }
    }

}