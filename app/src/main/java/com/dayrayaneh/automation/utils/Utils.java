package com.dayrayaneh.automation.utils;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.dayrayaneh.automation.base.ConstValue;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;

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


    public static String convertPersianDateToFormatOfServer(int year , int month , int day){
        PersianDate mDate = new PersianDate();
        int[] gregorian =mDate.toGregorian(year , month, day);
        return gregorian[0]+"-"+gregorian[1]+"-"+gregorian[2]+" ";
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
            datePickerDialog.setThemeDark(true);
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
        animator.setDuration(200);


        ObjectAnimator animatorY = ObjectAnimator.ofFloat(v , "scaleY" , 1f , 0.8f);
        animatorY.setDuration(200);
        animator.start();
        animatorY.start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator animator = ObjectAnimator.ofFloat(v , "scaleX" , 0.8f,1f);
                animator.setDuration(200);


                ObjectAnimator animatorY = ObjectAnimator.ofFloat(v , "scaleY" , 0.8f,1f);
                animatorY.setDuration(200);
                animatorY.start();
                animator.start();
            }
        },200);

    }
}
