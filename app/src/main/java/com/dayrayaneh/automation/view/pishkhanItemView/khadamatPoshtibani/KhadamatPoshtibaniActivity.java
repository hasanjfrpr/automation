package com.dayrayaneh.automation.view.pishkhanItemView.khadamatPoshtibani;

import androidx.appcompat.app.AlertDialog;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.res.Configuration;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.adapter.pishkhan.khadamatPoshtibani.main.KhadamatPoshtibaniMainAdapter;
import com.dayrayaneh.automation.base.BaseActivity;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.model.pishkhan.khadamatPoshtibani.mian.KhadamatPoshtibaniMainModel;
import com.dayrayaneh.automation.utils.Utils;

import com.dayrayaneh.automation.viewModel.pishkhan.khadamatPoshtibani.KhadamatPoshtibaniViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout;
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog;


public class KhadamatPoshtibaniActivity extends BaseActivity implements KhadamatPoshtibaniMainAdapter.ClickItemEvent {

    private ImageView back;
    private TextView fromDate, toDate, TV_company, startTime, endTime, khadamatKol, khadamatkolTiger, khadamatKolNovin, paygiriKol;
    private Toolbar toolbar;
    private View loadingView , empty_layout;
    private MaterialCardView selectCompany;
    private MaterialButton sendInfo;
    private int checkItems = 2;
    private KhadamatPoshtibaniViewModel thisViewModel;
    private RecyclerView rv_main;
    private KhadamatPoshtibaniMainAdapter adapter;
    private boolean isPortrait = true;


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
            setContentView(R.layout.activity_khadamat_poshtibani);
            findViewById(R.id.include2).setVisibility(View.GONE);
            findViewById(R.id.linearLayout2).setVisibility(View.GONE);
            findViewById(R.id.Mcv_khadamatPoshtibani_select_company).setVisibility(View.GONE);
            findViewById(R.id.Mbtn_pishkhan_khadamt_poshtibani_saveInfo).setVisibility(View.GONE);
        }else {
        setContentView(R.layout.activity_khadamat_poshtibani);}


        init();
        setData();
        setTime();
        viewModel();
        event();


    }



    private void init() {
        back = findViewById(R.id.IV_back_item_pishkhan);
        toolbar = findViewById(R.id.toolbar_item_pishkhan);
        toolbar.setTitle(getResources().getString(R.string.khadamatPoshtibani));
        setSupportActionBar(toolbar);
        fromDate = findViewById(R.id.TV_fromDate);
        toDate = findViewById(R.id.Tv_toDate);
        loadingView = findViewById(R.id.loading_viewsd);
        empty_layout = findViewById(R.id.empty_stateSd);
        startTime = findViewById(R.id.TV_fromTime);
        endTime = findViewById(R.id.Tv_toTime);
        sendInfo = findViewById(R.id.Mbtn_pishkhan_khadamt_poshtibani_saveInfo);
        selectCompany = findViewById(R.id.Mcv_khadamatPoshtibani_select_company);
        TV_company = findViewById(R.id.TV_khadamatPoshtibani_select_company);
        khadamatKol = findViewById(R.id.TV_khadamatPoshtibani_tedadKolKhadamat);
        khadamatkolTiger = findViewById(R.id.TV_khadamatPoshtibani_tedadKolKhadamatTiger);
        khadamatKolNovin = findViewById(R.id.TV_khadamatPoshtibani_tedadKolKhadamatNovin);
        paygiriKol = findViewById(R.id.TV_khadamatPoshtibani_tedadKolPaygiriNovin);
        thisViewModel = new ViewModelProvider(this).get(KhadamatPoshtibaniViewModel.class);
        rv_main = findViewById(R.id.RV_pishKhan_khadamat_poshtibani_main);








    }

    private void setData() {

        Utils.setDate(fromDate, toDate, this);

    }

    private void setTime() {

        TimePickerDialog timePickerDialog = new TimePickerDialog();

        startTime.setOnClickListener(v -> {

            TimePickerDialog dpd = TimePickerDialog.newInstance(
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {

                            if (minute < 10) {
                                startTime.setText(hourOfDay + ":" + "0" + minute);
                                ConstValue.startTime = hourOfDay + ":" + "0" + minute;
                            } else {
                                startTime.setText(hourOfDay + ":" + minute);
                                ConstValue.startTime = hourOfDay + ":" + minute;
                            }
                        }
                    }, 00, 00, true
            );
            dpd.show(getFragmentManager(), "Datepickerdialog");
        });
//
//
//
        endTime.setOnClickListener(v -> {
            TimePickerDialog dpd = TimePickerDialog.newInstance(
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {

                            if (minute < 10) {
                                endTime.setText(hourOfDay + ":" + "0" + minute);
                                ConstValue.endTime = hourOfDay + ":" + "0" + minute;
                            } else {
                                endTime.setText(hourOfDay + ":" + minute);
                                ConstValue.endTime = hourOfDay + ":" + minute;
                            }
                        }
                    }, 00, 00, true
            );
            dpd.show(getFragmentManager(), "Datepickerdialog");

        });
    }

    private void viewModel() {
      loadingView.setVisibility(View.VISIBLE);
        if (thisViewModel.khadamatPoshtibaniMainLiveData.getValue() != null ){
            if (thisViewModel.khadamatPoshtibaniMainLiveData.getValue().getData().size() < 1){
                empty_layout.setVisibility(View.VISIBLE);
                loadingView.setVisibility(View.GONE);
            }else {
                empty_layout.setVisibility(View.GONE);
                setRecycler(thisViewModel.khadamatPoshtibaniMainLiveData.getValue());
                loadingView.setVisibility(View.GONE);
                setTotalInformation(thisViewModel.khadamatPoshtibaniMainLiveData.getValue());
            }


        }else{
            thisViewModel.getKhadamatPoshtibaniMain(ConstValue.startDate , ConstValue.endDate , ConstValue.startTime , ConstValue.endTime ,ConstValue.companyId);
            thisViewModel.khadamatPoshtibaniMainLiveData.observe(this , khadamatPoshtibaniMainModel -> {
                if (khadamatPoshtibaniMainModel.getData().size() < 1){
                    empty_layout.setVisibility(View.VISIBLE);
                    loadingView.setVisibility(View.GONE);
                }else {
                    empty_layout.setVisibility(View.GONE);
                    setRecycler(khadamatPoshtibaniMainModel);
                    loadingView.setVisibility(View.GONE);
                    setTotalInformation(khadamatPoshtibaniMainModel);
                }

            });
        }

    }

    private void setRecycler(KhadamatPoshtibaniMainModel model) {
        adapter = new KhadamatPoshtibaniMainAdapter(this , model.getData());
        adapter.event = this;
        rv_main.setAdapter(adapter);
        rv_main.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }


    private void event() {


        ///onclick


        back.setOnClickListener(v -> {
                finish();

        });


        selectCompany.setOnClickListener(v -> {
            selectCompanyDialog();
        });





        sendInfo.setOnClickListener(v -> {
           viewModel();
        });


    }

    private void selectCompanyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setSingleChoiceItems(R.array.companyList, checkItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        TV_company.setText(getResources().getString(R.string.novin));
                        checkItems = 0;
                        ConstValue.companyId = 0;
                        break;
                    case 1:
                        TV_company.setText(getResources().getString(R.string.tiger));
                        checkItems = 1;
                        ConstValue.companyId = 1;
                        break;
                    case 2:
                        TV_company.setText(getResources().getString(R.string.all));
                        checkItems = 2;
                        ConstValue.companyId = -1;
                        break;
                }
                dialog.dismiss();
            }
        });
        builder.create().show();
    }


    public void setTotalInformation(KhadamatPoshtibaniMainModel model) {

        int khadamatKol = 0;
        int khadamatKoltiger = 0;
        int khadamatKolNovin = 0;
        int paygiriKol = 0;


        for (int i = 0; i < model.getData().size(); i++) {
            int company = model.getData().get(i).getFldCompany();
            if (company == 0) {
                khadamatKolNovin += model.getData().get(i).getKhadamatCount();
                paygiriKol += model.getData().get(i).getSdCount();
            } else {
                khadamatKoltiger += model.getData().get(i).getKhadamatCount();
            }
        }

        khadamatKol = khadamatKolNovin + khadamatKoltiger;

        this.khadamatKol.setText(String.valueOf(khadamatKol));
        this.khadamatkolTiger.setText(String.valueOf(khadamatKoltiger));
        this.khadamatKolNovin.setText(String.valueOf(khadamatKolNovin));
        this.paygiriKol.setText(String.valueOf(paygiriKol));

    }



    @Override
    public void onclickRecyclerItem(int userId) {
        Intent intent = new Intent(this , KhadamatPoshtibaniActivityDetails.class);
       intent.putExtra("userIdKhadamat" , userId);
        startActivity(intent);
    }
}