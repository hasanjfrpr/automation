package com.dayrayaneh.automation.technology.httpclient;

import com.dayrayaneh.automation.base.ConstValue;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiInstance {
    private static Retrofit retrofit = null;




    ////add new header when get token from server
    private static OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {


                    Request oldRequest = chain.request();
                    Request.Builder newRequest = oldRequest.newBuilder();

                    if (ConstValue.tokenContainer != null) {
                        newRequest.addHeader("Authorization", "Bearer " + ConstValue.tokenContainer);
                    }

                    newRequest.method(oldRequest.method(), oldRequest.body());

                    return chain.proceed(newRequest.build());
                }
            }).connectTimeout(1, TimeUnit.MINUTES).readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS).build();

    public static ApiService getApiInstance() {


        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://" + ConstValue.ip + ":" + ConstValue.port + "/Api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();
        }

        return retrofit.create(ApiService.class);
    }

    public static ApiService getApiInstanceOnce() {

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://" + ConstValue.ip + ":" + ConstValue.port + "/Api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();

        return retrofit.create(ApiService.class);
    }
}
