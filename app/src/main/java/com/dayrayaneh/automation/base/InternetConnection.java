package com.dayrayaneh.automation.base;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.dayrayaneh.automation.dialog.InternetDialog;
import com.dayrayaneh.automation.utils.Utils;

import org.greenrobot.eventbus.EventBus;

public class InternetConnection extends BroadcastReceiver {

    public MutableLiveData<Boolean> connectedLiveData = new MutableLiveData<>();


    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    @Override
    public void onReceive(Context context, Intent intent) {

        if (Utils.checkConnectivity(context)){
            connectedLiveData.setValue(true);
        }else {
            connectedLiveData.postValue(false);
        }








    }


}
