package com.example.javer.weatherappnewwithretrofit.View;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.javer.weatherappnewwithretrofit.Model.WeatherData;
import com.example.javer.weatherappnewwithretrofit.R;
import com.example.javer.weatherappnewwithretrofit.WeatherDetailActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherViewHolder>{

    JSONArray array;
    Context ctx;
    String location;

    public WeatherAdapter(JSONArray array, Context ctx, String location){


        this.array = array;
        this.ctx = ctx;
        this.location = location;

        for(int i = 1; i<=array.length(); i++){
            JSONObject obj = null;

            try {
                obj = array.getJSONObject(i);
                int minTemp = (int) obj.getDouble("min_temp");
                int maxTemp = (int) obj.getDouble("max_temp");
                int avgTemp = (int) obj.getDouble("the_temp");
                String abbr = obj.getString("weather_state_abbr");
                String state = obj.getString("weather_state_name");
                String weatherDate = obj.getString("applicable_date");
                String windSpeed = String.valueOf((int) Float.parseFloat(obj.getString("wind_speed")));
                String pressure = String.valueOf((int) Float.parseFloat(obj.getString("air_pressure")));
                String humidity = String.valueOf((int) Float.parseFloat(obj.getString("humidity")));

                list.add(new WeatherData(maxTemp, minTemp, avgTemp, state, abbr, weatherDate, humidity, pressure, windSpeed));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



    }

    ArrayList<WeatherData> list = new ArrayList<>();

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v  = LayoutInflater.from(ctx).inflate(R.layout.viewholder_recycle, parent, false);


        WeatherViewHolder holder = new WeatherViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, final int position) {

        WeatherData data = list.get(position);

        holder.maxTemp.setText(String.valueOf(data.getMaxTemp())+ "");
        holder.minTemp.setText(String.valueOf(data.getMinTemp()) + "");
        holder.state.setText(data.getWeatherState() + "");
        holder.day.setText(data.getDate() + "");

        Picasso.get().load(data.getUrl()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeIntent(list.get(position));
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
//        return 0;
    }


    public void makeIntent(WeatherData obj){
        Intent i = new Intent(ctx, WeatherDetailActivity.class);
        i.putExtra("ITEM_EXTRA", obj);
        i.putExtra("location", location);
        ctx.startActivity(i);
    }

}
