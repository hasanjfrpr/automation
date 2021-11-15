package com.dayrayaneh.automation.base;

import android.app.Application;

import com.dayrayaneh.automation.R;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;


public class App  extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

       setDefaultFont(this.getSharedPreferences("default_setting" , MODE_PRIVATE).getString("font",null));


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
