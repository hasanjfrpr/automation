package com.dayrayaneh.automation.base;

import android.media.metrics.Event;
import android.util.Log;

import androidx.annotation.NonNull;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.single.SingleToObservable;
import retrofit2.HttpException;

public abstract class AutomationSingleObserver<T> implements SingleObserver<T> {

    CompositeDisposable compositeDisposable;

    public AutomationSingleObserver(CompositeDisposable compositeDisposable) {
        this.compositeDisposable = compositeDisposable;
    }
    @Override
    public void onSubscribe(@NonNull Disposable d) {
        compositeDisposable.add(d);
    }

    @Override
    public void onError(@NonNull Throwable e) {

        Log.i("singleObserverError", e.toString());

        if (e instanceof java.net.SocketTimeoutException || e instanceof java.net.ConnectException || e instanceof java.net.UnknownHostException){
            String unKnowError;
            if (e.getMessage().trim().equals("timeout")){
                 unKnowError = "عدم دریافت پاسخ";
            }else {
                 unKnowError = "خطای Ip یا Port";
            }
            EventBus.getDefault().post(unKnowError);
        }else if (e instanceof HttpException) {
            if (((HttpException) e).code() == 401) {

                EventBus.getDefault().post(401);

            } else if (((HttpException) e).code() == 403) {
                String unAccess = "دسترسی شما به ابن آیتم ممکن نیست.";
                EventBus.getDefault().post(unAccess);
            }else {
                String unKnowError = "خطای نامشخص";
                EventBus.getDefault().post(unKnowError);
            }
        } else {
            String unKnowError = "خطای نامشخص";
            EventBus.getDefault().post(unKnowError);
        }
    }


}
