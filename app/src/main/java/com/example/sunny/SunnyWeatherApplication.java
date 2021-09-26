package com.example.sunny;

import android.app.Application;
import android.content.Context;

public class SunnyWeatherApplication extends Application {

    public static final String TOKEN = "Ts1Mh8Fse0cGmcon";

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }

}
