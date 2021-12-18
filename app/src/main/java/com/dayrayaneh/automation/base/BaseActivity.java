package com.dayrayaneh.automation.base;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.dayrayaneh.automation.dialog.ErrorUnAccessDialog;
import com.dayrayaneh.automation.dialog.InternetDialog;
import com.dayrayaneh.automation.view.login.LoginActivity;
import com.google.android.material.snackbar.Snackbar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class BaseActivity extends AppCompatActivity  {

    InternetConnection internetConnection;
    InternetDialog dialog = new InternetDialog();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        internetConnection = new InternetConnection();
        EventBus.getDefault().register(this);


        //check connectivity change
        internetConnection.connectedLiveData.observe(this,connected->{
            if (!connected){

                dialog.show(getSupportFragmentManager() , "");
            }
        });

        dialog.connectedLiveData.observe(this,internetConnection->{
            if (!internetConnection){
                dialog.show(getSupportFragmentManager(),"");
            }
        });


    }



    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(internetConnection, new IntentFilter(
                ConnectivityManager.CONNECTIVITY_ACTION
        ));

    }

    @Override
    protected void onPause() {
        unregisterReceiver(internetConnection);

        super.onPause();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getError401(int errorCode){
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showErrorIp(String err){
        if(err.equals("خطای Ip یا Port")){
            Snackbar.make(this,getWindow().getDecorView().getRootView(),"خطای Ip یا Port" , Snackbar.LENGTH_SHORT).show();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showDialogErrorUnAccess(String un){
        if (un.equals("خطای نامشخص")){
            Snackbar.make(this,getWindow().getDecorView().getRootView(),un , Snackbar.LENGTH_SHORT).show();
       }
       else if (un.equals("دسترسی شما به ابن آیتم ممکن نیست.")){
            ErrorUnAccessDialog dialog = new ErrorUnAccessDialog();
            dialog.setCancelable(false);
            dialog.show(getSupportFragmentManager() , "");
        }
    }










    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
