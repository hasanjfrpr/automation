package com.dayrayaneh.automation.base;

import android.util.Log;

import androidx.annotation.NonNull;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class AutomationCompletable implements CompletableObserver {

    CompositeDisposable compositeDisposable;

    public AutomationCompletable(CompositeDisposable compositeDisposable) {
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        compositeDisposable.add(d);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        Log.i("completableError", e.toString());
    }


}
