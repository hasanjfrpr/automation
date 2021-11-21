package com.dayrayaneh.automation.view.pishkhanItemView.bazaryabi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.base.BaseActivity;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.base.Keys;
import com.dayrayaneh.automation.model.pishkhan.pishkhan_bazaryabi.count.BazaryabiMainModel;
import com.dayrayaneh.automation.utils.Utils;
import com.dayrayaneh.automation.view.pishkhanItemView.bazaryabi.fragment.BazaryabiMainListFragment;
import com.dayrayaneh.automation.viewModel.pishkhan.bazaryabi.BazaryabiViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;
import com.google.type.DateTime;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import org.greenrobot.eventbus.EventBus;

import java.io.Console;

import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

public class BazaryabiActivity extends BaseActivity {

    private Toolbar toolbar;
    private TextView fromDate, toDate, tedadKol, tedadTiger, tedadNovin, company;
    private ImageView btn_back;
    private View loadingView;
    private MaterialCardView selectCompany;
    private int checkItems=2;
    private MaterialButton sendInfo;
    private String startDate ;
    private String endDate;
    private String startDatePersian ;
    private String endDatePersian;
    private BazaryabiViewModel bazaryabiViewModel;
    private int companyId = -1;
    private SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bazaryabi);
        init();
        setData();
        event();
        viewModel();

    }


    private void init() {
        toolbar = findViewById(R.id.toolbar_item_pishkhan);
        toolbar.setTitle(getResources().getString(R.string.bazaryabi));
        setSupportActionBar(toolbar);
        fromDate = findViewById(R.id.TV_fromDate);
        toDate = findViewById(R.id.Tv_toDate);
        btn_back = findViewById(R.id.IV_back_item_pishkhan);
        tedadKol = findViewById(R.id.Tv_bazaryabi_tedadKolBazaryabi);
        tedadTiger = findViewById(R.id.Tv_bazaryabi_tedadKolTiger);
        tedadNovin = findViewById(R.id.Tv_bazaryabi_tedadKolNovin);
        loadingView = findViewById(R.id.loading_bazaryabi);
        selectCompany = findViewById(R.id.Mcv_bazaryabi_select_company);
        company = findViewById(R.id.TV_Bazaryabi_select_company);
        sendInfo = findViewById(R.id.Mbtn_pishkhan_bazaryabi_saveInfo);
        bazaryabiViewModel = new ViewModelProvider(this).get(BazaryabiViewModel.class);
        sharedPreferences = getSharedPreferences("date", MODE_PRIVATE);
    }

    private void event() {
        ///onclick
        btn_back.setOnClickListener(v -> {
            finish();
        });


        selectCompany.setOnClickListener(v -> {
            selectCompanyDialog();

        });

        sendInfo.setOnClickListener(v -> {
            viewModel();
        });


        //replace fragment



    }

    private void setData() {
        ///set default date
        PersianDate mDate = new PersianDate();
        PersianDateFormat format = new PersianDateFormat("Y/m/d");
        String currentDate = format.format(mDate);
        ///default jalali date
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
        fromDate.setOnClickListener(v -> {
            datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                    ///TextView  date for show to user
                    fromDate.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);

                    /// String date for send to ViewModel
                    ConstValue.startDate = Utils.convertPersianDateToFormatOfServer(year , (monthOfYear+1), dayOfMonth);

                    ///start persianDate for save
                    ConstValue.startDatePersian = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;



                }
            });
            datePickerDialog.setThemeDark(true);
            datePickerDialog.show(getFragmentManager(), "");

        });

        toDate.setOnClickListener(v -> {
            datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                    ///TextView  date for show to user
                    toDate.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                    /// String date for send to ViewModel
                    ConstValue.endDate = Utils.convertPersianDateToFormatOfServer(year ,(monthOfYear+1 ), dayOfMonth);
                    /////end persian date to save
                    ConstValue.endDatePersian = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;

                }
            });
            datePickerDialog.setThemeDark(true);
            datePickerDialog.show(getFragmentManager(), "");


        });


    }

    private void selectCompanyDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setSingleChoiceItems(R.array.companyList, checkItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        company.setText(getResources().getString(R.string.novin));
                        checkItems = 0;
                        companyId = 0;
                        break;
                    case 1:
                        company.setText(getResources().getString(R.string.tiger));
                        checkItems = 1;
                        companyId = 1;
                        break;
                    case 2:
                        company.setText(getResources().getString(R.string.all));
                        checkItems = 2;
                        companyId = -1;
                        break;
                }
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void viewModel(){
        bazaryabiViewModel.getBazarYabiMainCount(ConstValue.startDate, ConstValue.endDate , companyId);
        loadingView.setVisibility(View.VISIBLE);
        bazaryabiViewModel.bazaryabiMainLiveData.observe(this,bazaryabiMainModel -> {
            if (bazaryabiMainModel.getStatus().isIsError()){
                Snackbar.make(this,btn_back,getResources().getString(R.string.unknowErro) , Snackbar.LENGTH_LONG);
            }else {
                EventBus.getDefault().post(bazaryabiMainModel);
                loadingView.setVisibility(View.GONE);
                addAmarBazaryabi(bazaryabiMainModel);
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayout_bazaryabi, new BazaryabiMainListFragment(companyId),"bazaryabiMainFragment")
                .commit();

    }

    private void addAmarBazaryabi(BazaryabiMainModel bazaryabiMainModel){
         int tedadKolTiger = 0;
         int tedadKolNovin = 0;
        for (int i = 0; i < bazaryabiMainModel.getData().size() ; i++) {

            switch (bazaryabiMainModel.getData().get(i).getCompany()){
                case 0:
                    tedadKolNovin =+ bazaryabiMainModel.getData().get(i).getProformaCount();
                    break;
                case 1:
                     tedadKolTiger =+ bazaryabiMainModel.getData().get(i).getProformaCount();
                    break;
            }


        }
        tedadKol.setText(String.valueOf(tedadKolNovin+tedadKolTiger));
        tedadTiger.setText(String.valueOf(tedadKolTiger));
        tedadNovin.setText(String.valueOf(tedadKolNovin));
    }


    @Override
    public void onBackPressed() {

 if (getSupportFragmentManager().findFragmentByTag("bazaryabiDetailFragment") !=null){
            getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayout_bazaryabi, new BazaryabiMainListFragment(companyId),"bazaryabiMainFragment")
                    .commit();
        }else {
            super.onBackPressed();
        }
    }
}