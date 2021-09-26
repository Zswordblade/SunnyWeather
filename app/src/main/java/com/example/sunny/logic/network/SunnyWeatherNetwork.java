package com.example.sunny.logic.network;

import android.util.Log;

import com.example.sunny.logic.model.DailyResponse;
import com.example.sunny.logic.model.PlaceResponse;
import com.example.sunny.logic.model.RealtimeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SunnyWeatherNetwork {
      private static PlaceService placeService = ServiceCreator.PCreate(PlaceService.class);
      private static PlaceResponse Presult = new PlaceResponse();

      public  PlaceResponse searchPlaces(String query){

          placeService.searchPlace(query).enqueue(new Callback<PlaceResponse>() {
              @Override
              public void onResponse(Call<PlaceResponse> call, Response<PlaceResponse> response) {
                  PlaceResponse  body= response.body();
                  if(body!=null){
                      Presult = body;
                      Log.d("SunnyWeatherNetWork","response body is not null");
                      Log.d("SunnyWeatherNetWork","status is " +Presult.getStatus() );
                  }
                  else {
                      Log.d("SunnyWeatherNetWork","response body is null");
                  }
              }
              @Override
              public void onFailure(Call<PlaceResponse> call, Throwable t) {
                  Log.d("SunnyWeatherNetWork","连接错误");
                    t.printStackTrace();
              }
          });
          return Presult;
      }

      //下面是搜索天气模块
      private final WeatherService weatherService = ServiceCreator.WCreate(WeatherService.class);

      private static RealtimeResponse Rresult ;
      private static DailyResponse Dresult;
      private static Boolean RFlag = false;//标志位
      private static Boolean DFlag = true;

      public  RealtimeResponse getRealtimeWeather(String lng,String lat){

          weatherService.getRealtimeWeather(lng,lat).enqueue(new Callback<RealtimeResponse>() {
              @Override
              public void onResponse(Call<RealtimeResponse> call, Response<RealtimeResponse> response) {
                  if(response.body() != null){
                      Rresult = response.body();
                      RFlag = true;
                      Log.d("SunnyWeatherNetWork1","status is " +Rresult.getStatus() );
                      Log.d("SunnyWeatherNetWork1","Realtime response body is not null");
                  }
                  else {
                      Log.d("SunnyWeatherNetWork1","Realtime response body is null");
                  }
              }

              @Override
              public void onFailure(Call<RealtimeResponse> call, Throwable t) {
                  Log.d("SunnyWeatherNetWork1"," RealTime connect failed");
                  t.printStackTrace();
              }
          });
          if(RFlag) return Rresult;
          return null;
      }

      //获取未来天气数据
      public DailyResponse getDailyWeather(String lng,String lat){
          Log.d("SunnyWeatherNetWork","查看传入的lng和lat" + lng + " " + lat);

          weatherService.getDailyWeather(lng,lat).enqueue(new Callback<DailyResponse>() {
              @Override
              public void onResponse(Call<DailyResponse> call, Response<DailyResponse> response) {
                  if(response.body() != null){
                      Dresult = response.body();
                      DFlag = true;
                      Log.d("SunnyWeatherNetWork2","Dailytime response body is not null");
                      Log.d("SunnyWeatherNetWork2","Dresult is " +Dresult.getResult() );

                  }
                  else {
                      Log.d("SunnyWeatherNetWork2","Daily response body is null");
                  }
              }

              @Override
              public void onFailure(Call<DailyResponse> call, Throwable t) {
                  Log.d("SunnyWeatherNetWork2","Daily connect failed");
                  t.printStackTrace();
              }
          });
          if(DFlag) return Dresult;
            return null;
      }

}
