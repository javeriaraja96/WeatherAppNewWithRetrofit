package com.example.javer.weatherappnewwithretrofit.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class WeatherData implements Parcelable{
    private int maxTemp;
    private int minTemp;
    private int avgTemp;
    private String weatherState;
    private String weatherStateAbbr;
    private String date;
    private String humidity;
    private String pressure;
    private String windSpeed;



    public WeatherData(Parcel in ) {
        readFromParcel( in );
    }

    public WeatherData(int maxTemp, int minTemp, int avgTemp, String weatherState, String weatherStateAbbr, String date, String humidity, String pressure, String windSpeed) {
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.avgTemp = avgTemp;
        this.weatherState = weatherState;
        this.weatherStateAbbr = weatherStateAbbr;
        this.date = date;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }


    public int getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }

    public int getAvgTemp() {
        return avgTemp;
    }

    public void setAvgTemp(int avgTemp) {
        this.avgTemp = avgTemp;
    }

    public String getWeatherState() {
        return weatherState;
    }

    public void setWeatherState(String weatherState) {
        this.weatherState = weatherState;
    }

    public String getWeatherStateAbbr() {
        return weatherStateAbbr;
    }

    public void setWeatherStateAbbr(String weatherStateAbbr) {
        this.weatherStateAbbr = weatherStateAbbr;
    }
    public String getUrl(){
        return String.format("https://www.metaweather.com/static/img/weather/png/%s.png", weatherStateAbbr);
    }

    public String getDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return ((String) DateFormat.format("EEE, MMM d, ''yy", simpleDateFormat.parse(date)));
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(avgTemp);
        dest.writeInt(minTemp);
        dest.writeInt(maxTemp);
        dest.writeString(date);
        dest.writeString(humidity);
        dest.writeString(pressure);
        dest.writeString(windSpeed);
        dest.writeString(weatherStateAbbr);
        dest.writeString(weatherState);

    }

    private void readFromParcel(Parcel in ) {

        avgTemp = in.readInt();
        minTemp = in.readInt();
        maxTemp = in.readInt();
        date = in.readString();
        humidity = in.readString();
        pressure = in.readString();
        windSpeed = in.readString();
        weatherStateAbbr = in.readString();
        weatherState = in.readString();

    }


    public static final Parcelable.Creator<WeatherData> CREATOR = new Parcelable.Creator<WeatherData>(){

        @Override
        public WeatherData createFromParcel(Parcel source) {
            return new WeatherData(source);
        }

        @Override
        public WeatherData[] newArray(int size) {
            return new WeatherData[size];
        }
    };








}
