package com.example.javer.weatherappnewwithretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.javer.weatherappnewwithretrofit.Model.WeatherData;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class WeatherDetailActivity extends AppCompatActivity {

    TextView dayText;

    TextView humidity;
    TextView pressure;
    TextView wind;
    TextView location;
    TextView maxTempText;
    TextView minTempText;
    TextView state;
    ImageView iconWeather;

    String loc;
    JSONObject dataWeather;

    WeatherData dataObj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);

        loc = getIntent().getStringExtra("location");

        dayText = (TextView) findViewById(R.id.dayText);
        humidity = (TextView) findViewById(R.id.humidity);
        pressure = (TextView) findViewById(R.id.pressure);
        wind = (TextView) findViewById(R.id.wind);
        location = (TextView) findViewById(R.id.location);
        maxTempText = (TextView) findViewById(R.id.maxTempR);
        minTempText = (TextView) findViewById(R.id.minTempR);
        state = (TextView) findViewById(R.id.state);
        iconWeather = (ImageView) findViewById(R.id.icon_weather_detail);

        try {

            dataObj = (WeatherData) getIntent().getParcelableExtra("ITEM_EXTRA");

            dayText.setText(dataObj.getDate());
            humidity.setText("Humidity: "+ dataObj.getHumidity() + " %");
            pressure.setText("Pressure: "+ dataObj.getPressure()+ " hPa");
            wind.setText("Wind: " + dataObj.getWindSpeed());
            location.setText("Location: " + loc);
            maxTempText.setText(dataObj.getMaxTemp() + "");
            minTempText.setText(dataObj.getMinTemp() + "");
            state.setText(dataObj.getWeatherState() + "");

            Picasso.get().load(dataObj.getUrl()).into(iconWeather);


        }catch (Exception e) {
            e.printStackTrace();
        }
    }





}
