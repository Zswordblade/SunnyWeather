package com.example.sunny.logic.network;

import android.util.Log;

import com.example.sunny.logic.model.DailyResponse;
import com.example.sunny.logic.model.RealtimeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherService {
    @GET("v2.5/Ts1Mh8Fse0cGmcon/{lng},{lat}/realtime.json")
    Call<RealtimeResponse> getRealtimeWeather
            (@Path("lng") String lng,
             @Path("lat") String lat);
    @GET("v2.5/Ts1Mh8Fse0cGmcon/{lng},{lat}/daily.json")
    Call<DailyResponse> getDailyWeather
            (@Path("lng") String lng,
             @Path("lat") String lat);

}
