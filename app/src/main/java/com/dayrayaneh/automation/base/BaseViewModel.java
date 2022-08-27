package com.dayrayaneh.automation.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseViewModel extends ViewModel {

    public CompositeDisposable compositeDisposable = new CompositeDisposable();



    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }
}
