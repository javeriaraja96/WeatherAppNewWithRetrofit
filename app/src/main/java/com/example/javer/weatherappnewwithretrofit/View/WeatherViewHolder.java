package com.example.javer.weatherappnewwithretrofit.View;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.javer.weatherappnewwithretrofit.R;

public class WeatherViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView maxTemp, minTemp, state, day;

    public WeatherViewHolder(View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.weatherPic);

        maxTemp = itemView.findViewById(R.id.maxTempR);
        minTemp = itemView.findViewById(R.id.minTempR);
        state = itemView.findViewById(R.id.weatherState);

        day = itemView.findViewById(R.id.day);

    }
}
