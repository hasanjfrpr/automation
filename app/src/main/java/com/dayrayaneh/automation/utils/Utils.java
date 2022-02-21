package com.dayrayaneh.automation.utils;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.dayrayaneh.automation.base.ConstValue;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

public class Utils {


    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    public static String convertPersianDateToFormatOfServer(int year , int month , int day){
        PersianDate mDate = new PersianDate();
        int[] gregorian =mDate.toGregorian(year , month, day);
        return gregorian[0]+"-"+gregorian[1]+"-"+gregorian[2]+" ";
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale =  context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static void setDate(TextView fromDate , TextView toDate , Activity activity){
        ///set default date
        PersianDate mDate = new PersianDate();
        PersianDateFormat format = new PersianDateFormat("Y/m/d");
        String currentDate = format.format(mDate);


        if (ConstValue.endDatePersian ==null && ConstValue.startDatePersian ==null) {
            fromDate.setText(currentDate);
            toDate.setText(currentDate);
            ////save default persianDate
            ConstValue.startDatePersian = currentDate;
            ConstValue.endDatePersian = currentDate;

            ///// default gregorian date
            ConstValue.startDate =   Utils.convertPersianDateToFormatOfServer(mDate.getShYear() , mDate.getShMonth() , mDate.getShDay());
            ConstValue.endDate =   Utils.convertPersianDateToFormatOfServer(mDate.getShYear() , mDate.getShMonth() , mDate.getShDay());

        }else {
            fromDate.setText(ConstValue.startDatePersian);
            toDate.setText(ConstValue.endDatePersian);
        }



        /////set date selection
        com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog datePickerDialog = new com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog();
        fromDate.setOnClickListener(v->{
            datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                    fromDate.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
                    /// String date for send to ViewModel
                    ConstValue.startDate = Utils.convertPersianDateToFormatOfServer(year , (monthOfYear+1), dayOfMonth);

                    ///start persianDate for save
                    ConstValue.startDatePersian = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;
                }
            });
            datePickerDialog.setThemeDark(false);
            datePickerDialog.show(activity.getFragmentManager(), "");


        });

        toDate.setOnClickListener(v->{
            datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                    toDate.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);

                    /// String date for send to ViewModel
                    ConstValue.endDate = Utils.convertPersianDateToFormatOfServer(year ,(monthOfYear+1 ), dayOfMonth);
                    /////end persian date to save
                    ConstValue.endDatePersian = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;
                }
            });
            datePickerDialog.setThemeDark(true);
            datePickerDialog.show(activity.getFragmentManager(), "");


        });

    }

    public static String formatPersianNumber(double number) {

        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");

        String strFormat = decimalFormat.format(number);

        return strFormat
                .replace("0", "۰")
                .replace("1", "۱")
                .replace("2", "۲")
                .replace("3", "۳")
                .replace("4", "۴")
                .replace("5", "۵")
                .replace("6", "۶")
                .replace("7", "۷")
                .replace("8", "۸")
                .replace("9", "۹");
    }


    public static void setAnimationClick(View v , Context context){
        ObjectAnimator animator = ObjectAnimator.ofFloat(v , "scaleX" , 1f , 0.8f);
        animator.setDuration(150);


        ObjectAnimator animatorY = ObjectAnimator.ofFloat(v , "scaleY" , 1f , 0.8f);
        animatorY.setDuration(150);
        animator.start();
        animatorY.start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator animator = ObjectAnimator.ofFloat(v , "scaleX" , 0.8f,1f);
                animator.setDuration(150);


                ObjectAnimator animatorY = ObjectAnimator.ofFloat(v , "scaleY" , 0.8f,1f);
                animatorY.setDuration(150);
                animatorY.start();
                animator.start();
            }
        },150);

    }

    public static  boolean checkConnectivity(Context context){

        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    static public boolean isURLReachable(Context context , String urls) {
         boolean[] isUrlReachable = {false};

        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = cm.getActiveNetworkInfo();
                if (netInfo != null && netInfo.isConnected()) {
                    try {
                        URL url = new URL(urls);
                        HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                        urlc.setConnectTimeout(3000);          // 10 s.
                        urlc.connect();
                        if (urlc.getResponseCode() == 200) {        // 200 = "OK" code (http connection is fine).
                           isUrlReachable[0] =true;
                        } else {
                            isUrlReachable[0] = false;
                        }
                    } catch (MalformedURLException e1) {
                        isUrlReachable[0] = false;
                    } catch (Exception e) {
                        isUrlReachable[0] = false;
                    }
                }
                return isUrlReachable[0];

            }
        };
        return isUrlReachable[0];
    }



    




}
