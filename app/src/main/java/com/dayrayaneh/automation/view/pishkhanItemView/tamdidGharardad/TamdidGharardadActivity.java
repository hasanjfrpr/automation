package com.dayrayaneh.automation.view.pishkhanItemView.tamdidGharardad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.adapter.pishkhan.forooshNarmAfzar.ForooshNarmAfzarAdapter;
import com.dayrayaneh.automation.adapter.pishkhan.tamdidGharardad.TamdidGharardadAdapter;
import com.dayrayaneh.automation.adapter.pishkhan.tamdidGharardad.compare.TamdidGharardadCompareAdapter;
import com.dayrayaneh.automation.base.BaseActivity;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.model.pishkhan.forooshNarmAfzar.ForooshNarmAfzarModel;
import com.dayrayaneh.automation.model.pishkhan.tamdidQarardad.DataItem;
import com.dayrayaneh.automation.model.pishkhan.tamdidQarardad.TamdidGharardadModel;
import com.dayrayaneh.automation.model.pishkhan.tamdidQarardad.compare.TamdidGharardadCompareModel;
import com.dayrayaneh.automation.utils.Utils;
import com.dayrayaneh.automation.viewModel.pishkhan.foroshNarmAfzar.ForooshNarmAfzarViewModel;
import com.dayrayaneh.automation.viewModel.pishkhan.tamdidQarardad.TamdidGharardadViewModel;
import com.google.android.material.button.MaterialButton;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;

import java.util.List;

import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

public class TamdidGharardadActivity extends BaseActivity {


    private ImageView back;
    private TextView fromDate, toDate  , fromDate_mqayese , toDate_moqayese , TV_serviceType , mablaqKol, tedadKol;
    private Toolbar toolbar;
    private RecyclerView rv_main , rv_moqayese;
    private TamdidGharardadAdapter adapter;
    private TamdidGharardadCompareAdapter adapter_compare;
    private TamdidGharardadViewModel tamdidGharardadViewModel;
    private View loadingView, showEmpty;
    private LinearLayout showCompare;
    private FrameLayout frame_select_service;
    private MaterialButton sendInfo;
    private CheckBox checkBox;
    private String fromDateM , toDateM;
    private boolean isCheckedd = false;
    private int item_serviceTypeId=0;
    private int noeService = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tamdid_gharardad);
        init();
        setDate();
        event();
        viewModel();



    }




    private void init() {
        back = findViewById(R.id.IV_back_item_pishkhan);
        toolbar = findViewById(R.id.toolbar_item_pishkhan);
        toolbar.setTitle(getResources().getString(R.string.tamdidQarardad));
        setSupportActionBar(toolbar);
        fromDate = findViewById(R.id.TV_fromDate);
        toDate = findViewById(R.id.Tv_toDate);
        checkBox = findViewById(R.id.checkbox_pishkan_tamdidQarardad);
        showCompare = findViewById(R.id.LinLayout_pishkhan_tamdidQarardad_moqayese);
        rv_main = findViewById(R.id.RV_pishkhan_tamdidQarardad_main);
        rv_moqayese = findViewById(R.id.RV_pishkhan_tamdidQarardad_moqayese);
        loadingView = findViewById(R.id.loading_view);
        sendInfo = findViewById(R.id.Mbtn_pishkhan_tamdidQarardad_sendInfo);
        fromDate_mqayese = findViewById(R.id.TV_fromDate_moqayese);
        toDate_moqayese = findViewById(R.id.Tv_toDate_moqayese);
        frame_select_service = findViewById(R.id.frameLayout_pishkhan_tamdidQarardad_entekhab_Service);
        TV_serviceType = findViewById(R.id.TV_pishkhan_tamdidQarardad_entekhabService);
        showEmpty = findViewById(R.id.showEmpty);
        tedadKol = findViewById(R.id.TV_pishkhan_tamdidQarardad_tedadKol);
        mablaqKol = findViewById(R.id.TV_pishkhan_tamdidQarardad_mablaqkol);


        tamdidGharardadViewModel = new ViewModelProvider(this).get(TamdidGharardadViewModel.class);

    }



    private void event(){
        back.setOnClickListener(v -> {finish();});

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    showCompare.setVisibility(View.VISIBLE);
                    isCheckedd = true;
                }else {
                    showCompare.setVisibility(View.GONE);
                    isCheckedd = false;
                }

            }
        });

        sendInfo.setOnClickListener(v -> {
            if (isCheckedd){
                ///request for compare date
                viewModelForCompareData();
            }else{
                viewModel();
            }
        });

        frame_select_service.setOnClickListener(v -> {
            selectCompanyDialog();
        });


    }

    private void setDate(){
        Utils.setDate(fromDate , toDate , this);
        setCompareDate();
    }

    private void setCompareDate(){

        PersianDate mDate = new PersianDate();
        PersianDateFormat format = new PersianDateFormat("Y/m/d");
        String currentDate = format.format(mDate);

        fromDate_mqayese.setText(currentDate);
        toDate_moqayese.setText(currentDate);
        fromDateM =  Utils.convertPersianDateToFormatOfServer(mDate.getShYear() ,mDate.getShMonth(), mDate.getShDay());
        toDateM = Utils.convertPersianDateToFormatOfServer(mDate.getShYear() ,mDate.getShMonth(), mDate.getShDay());


        com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog datePickerDialog = new com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog();
        fromDate_mqayese.setOnClickListener(v->{
            datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                    fromDate_mqayese.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
                    fromDateM =  Utils.convertPersianDateToFormatOfServer(year ,(monthOfYear+1 ), dayOfMonth);
                }
            });
            datePickerDialog.setThemeDark(true);
            datePickerDialog.show(getFragmentManager(), "");

        });

        toDate_moqayese.setOnClickListener(v->{
            datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                    toDate_moqayese.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
                    toDateM = Utils.convertPersianDateToFormatOfServer(year ,(monthOfYear+1 ), dayOfMonth);
                }
            });
            datePickerDialog.setThemeDark(true);
            datePickerDialog.show(getFragmentManager(), "");


        });


    }

    private void viewModel(){
        rv_main.setVisibility(View.VISIBLE);
        rv_moqayese.setVisibility(View.GONE);
        loadingView.setVisibility(View.VISIBLE);
        tamdidGharardadViewModel.getTamdidGharardad(ConstValue.startDate , ConstValue.endDate);
        tamdidGharardadViewModel.tamdidGharardadLiveData.observe(this,tamdidGharardadModel -> {
            if (tamdidGharardadModel.getData().size() < 1 ){
                showEmpty.setVisibility(View.VISIBLE);
                loadingView.setVisibility(View.GONE);
                rv_main.setVisibility(View.GONE);

            }else {
                showEmpty.setVisibility(View.GONE);
                loadingView.setVisibility(View.GONE);
                setTotalInfo(tamdidGharardadModel.getData());
                setRecyclerViewMain(tamdidGharardadModel);
            }

        });
    }

    private void setRecyclerViewMain(TamdidGharardadModel tamdidGharardadModel) {

        adapter  = new TamdidGharardadAdapter(this , tamdidGharardadModel.getData());
        rv_main.setVisibility(View.VISIBLE);
        rv_main.setAdapter(adapter);
        rv_main.setLayoutManager(new LinearLayoutManager(this , RecyclerView.VERTICAL , false));


    }


    private void viewModelForCompareData(){
        rv_main.setVisibility(View.GONE);
        rv_moqayese.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.VISIBLE);
        tamdidGharardadViewModel.getTamdidGharardadCompare(ConstValue.startDate , ConstValue.endDate , fromDateM , toDateM , noeService);
        tamdidGharardadViewModel.tamdidGharardadCompareLiveData.observe(this,tamdidGharardadCompareModel -> {

            if (tamdidGharardadCompareModel.getData().size() < 1 ){
                showEmpty.setVisibility(View.VISIBLE);
                loadingView.setVisibility(View.GONE);
                rv_moqayese.setVisibility(View.GONE);
            }else {
                showEmpty.setVisibility(View.GONE);
                loadingView.setVisibility(View.GONE);
                setRecyclerViewCompare(tamdidGharardadCompareModel);
            }
        });
    }

    private void setRecyclerViewCompare(TamdidGharardadCompareModel tamdidGharardadCompareModel) {

        adapter_compare  = new TamdidGharardadCompareAdapter(this , tamdidGharardadCompareModel.getData());
        rv_moqayese.setVisibility(View.VISIBLE);
        rv_moqayese.setAdapter(adapter_compare);
        rv_moqayese.setLayoutManager(new LinearLayoutManager(this , RecyclerView.VERTICAL , false));


    }



    private void selectCompanyDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setSingleChoiceItems(R.array.serviceType, item_serviceTypeId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        TV_serviceType.setText(getResources().getString(R.string.serviceAbi));
                        item_serviceTypeId = 0;
                        noeService =2;
                        break;
                    case 1:
                        TV_serviceType.setText(getResources().getString(R.string.servicNoghree));
                        item_serviceTypeId = 1;
                        noeService = 3;
                        break;
                    case 2:
                        TV_serviceType.setText(getResources().getString(R.string.serviceTalaee));
                        item_serviceTypeId = 2;
                        noeService = 4;
                        break;
                }
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void setTotalInfo(List<DataItem> dataItem) {
        int mablaqKol = 0;
        int tedadAqlam = 0;

        for (int i = 0; i < dataItem.size(); i++) {
            mablaqKol += dataItem.get(i).getMablaghGharardad();
            tedadAqlam += dataItem.get(i).getCountGharardad();
        }

        this.mablaqKol.setText(Utils.formatPersianNumber(mablaqKol));
        this.tedadKol.setText(Utils.formatPersianNumber(tedadAqlam));

    }

}