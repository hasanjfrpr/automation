package com.dayrayaneh.automation.base;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

import com.dayrayaneh.automation.R;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;


public class App  extends Application {

    public static SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();



       sharedPreferences = this.getSharedPreferences("default_setting" , MODE_PRIVATE);
        setDefaultFont(sharedPreferences.getString("font",null));
        ConstValue.ip = sharedPreferences.getString("ip","");
        ConstValue.port = sharedPreferences.getString("port","");
        ConstValue.ip_voice = sharedPreferences.getString("ip_Voice","");
        ConstValue.port_voice = sharedPreferences.getString("port_Voice","");
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandling(getApplicationContext()));
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

    }

    public void setDefaultFont(String font){
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath(font)
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());

    }
}
