package com.example.javer.weatherappnewwithretrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MetaWeatherService {

    @GET("/api/location/{woeid}/")
    public Call<String> loadWeatherData(@Path("woeid") long id);


    @GET("/api/location/search/?")
    public Call<String> loadCityData(@Query("query") String city);


}
