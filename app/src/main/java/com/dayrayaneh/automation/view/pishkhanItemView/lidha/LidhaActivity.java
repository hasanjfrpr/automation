package com.dayrayaneh.automation.view.pishkhanItemView.lidha;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.adapter.pishkhan.lid.LidAdapter;
import com.dayrayaneh.automation.base.BaseActivity;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.model.pishkhan.lidModel.LidModel;
import com.dayrayaneh.automation.utils.Utils;
import com.dayrayaneh.automation.view.pishkhanItemView.bazaryabi.fragment.BazaryabiDetailListFragment;
import com.dayrayaneh.automation.view.pishkhanItemView.bazaryabi.fragment.BazaryabiMainListFragment;
import com.dayrayaneh.automation.viewModel.pishkhan.bazaryabi.BazaryabiViewModel;
import com.dayrayaneh.automation.viewModel.pishkhan.lidha.LidhaViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;

import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

public class LidhaActivity extends BaseActivity {


    private Toolbar toolbar;
    private TextView fromDate, toDate,  company;
    private ImageView btn_back;
    public View loadingView;
    private MaterialCardView selectCompany;
    private int checkItems=2;
    private MaterialButton sendInfo;
    private LidhaViewModel lidhaViewModel;
    public static int companyId = -1;
    private LidAdapter adapter;
    private SharedPreferences sharedPreferences;
    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lidha);
        init();
        event();
        setData();
        viewModel();
    }

    private void init() {
        toolbar = findViewById(R.id.toolbar_item_pishkhan);
        toolbar.setTitle(getResources().getString(R.string.lidha));
        setSupportActionBar(toolbar);
        fromDate = findViewById(R.id.TV_fromDate);
        toDate = findViewById(R.id.Tv_toDate);
        btn_back = findViewById(R.id.IV_back_item_pishkhan);
        loadingView = findViewById(R.id.loading_Lidha);
        selectCompany = findViewById(R.id.Mcv_Lidha_select_company);
        company = findViewById(R.id.TV_Lidha_select_company);
        sendInfo = findViewById(R.id.Mbtn_pishkhan_Lidha_saveInfo);
        recyclerView = findViewById(R.id.RV_pishKhan_lid);
        lidhaViewModel = new ViewModelProvider(this).get(LidhaViewModel.class);
        sharedPreferences = getSharedPreferences("date", MODE_PRIVATE);

    }
    private void setRecyclerView(LidModel lidModel){
        adapter = new LidAdapter(lidModel , this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this ,RecyclerView.VERTICAL , false));

    }

    private void viewModel(){


        ///////////////////
        loadingView.setVisibility(View.VISIBLE);
        if (lidhaViewModel.lidModelMutableLiveData.getValue() != null && getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE ){
            if (lidhaViewModel.lidModelMutableLiveData.getValue().getData().size() < 1){
                loadingView.setVisibility(View.GONE);
            }else {

                setRecyclerView(lidhaViewModel.lidModelMutableLiveData.getValue());
                loadingView.setVisibility(View.GONE);

            }

        }else{
            loadingView.setVisibility(View.VISIBLE);
            lidhaViewModel.getLid(ConstValue.startDate ,ConstValue.endDate ,companyId );
            lidhaViewModel.lidModelMutableLiveData.observe(this , lidModel -> {
                loadingView.setVisibility(View.GONE);
                adapter = new LidAdapter(lidModel , this);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this ,RecyclerView.VERTICAL , false));

            });
        }
    }

    private void event() {

        ///onclick

        btn_back.setOnClickListener(view -> {
            finish();
        });


        selectCompany.setOnClickListener(v -> {
            selectCompanyDialog();

        });


        sendInfo.setOnClickListener(v -> {
            loadingView.setVisibility(View.VISIBLE);
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
}