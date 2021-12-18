package com.dayrayaneh.automation.view.pishkhanItemView.darsadKharidShahrestanha;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.adapter.pishkhan.darsadKharidShahrestan.DarsadKharidShahrestanAdapter;
import com.dayrayaneh.automation.adapter.pishkhan.pishkan_darsadKharidMoshtarian.DarsadKharidMoshtarianAdapter;
import com.dayrayaneh.automation.base.BaseActivity;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.model.pishkhan.darsadKharidMoshtari.DarsadkharidMoshtariModel;
import com.dayrayaneh.automation.model.pishkhan.darsadKharidShahrestan.DarsadKharidShahrestanModel;
import com.dayrayaneh.automation.utils.Utils;
import com.dayrayaneh.automation.viewModel.pishkhan.darsadKharidShahrestan.DarsadKharidShahrestanViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;

import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

public class DarsadKharidShahrestanActiviy extends BaseActivity implements DarsadKharidShahrestanAdapter.Events{


    private ImageView back;
    private TextView fromDate ,toDate , TV_productType;
    private Toolbar toolbar;
    private DarsadKharidShahrestanAdapter adapter;
    private RecyclerView recyclerView;
    private MaterialButton sendInfo;
    private MaterialCardView select_productType;
    private int checkItems = 2;
    private int productType = -1;
    private DarsadKharidShahrestanViewModel viewModel;
    private View loadingView , showEmpty;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_darsad_kharid_shahrestan_activiy);
        init();
        event();
        setData();
        viewModel();
    }
    private void init() {
        back = findViewById(R.id.IV_back_item_pishkhan);
        toolbar = findViewById(R.id.toolbar_item_pishkhan);
        toolbar.setTitle(getResources().getString(R.string.darsadKharidShahrestan));
        setSupportActionBar(toolbar);
        fromDate = findViewById(R.id.TV_fromDate);
        toDate = findViewById(R.id.Tv_toDate);
        recyclerView = findViewById(R.id.RV_pishKhan_darsadKharidShahrestan);
        sendInfo = findViewById(R.id.Mbtn_pishkhan_darsadKharidShahrestan_saveInfo);
        select_productType = findViewById(R.id.Mcv_darsadKharidshahrestan_select_productType);
        TV_productType = findViewById(R.id.TV_darsadKharidSharestan_select_productType);
        loadingView = findViewById(R.id.loading_view);
        showEmpty = findViewById(R.id.showEmpty);
        viewModel = new ViewModelProvider(this).get(DarsadKharidShahrestanViewModel.class);
    }

    private void setData(){
        Utils.setDate(fromDate,toDate,this);
    }

    private void setupRecycler(DarsadKharidShahrestanModel darsadKharidShahrestanModel) {
        adapter = new DarsadKharidShahrestanAdapter(darsadKharidShahrestanModel.getData(), this);
        adapter.events = this;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

    private void event() {
        ///onclick
        back.setOnClickListener(v -> {
            finish();
        });


        select_productType.setOnClickListener(v -> {
            selectCompanyDialog();

        });

        sendInfo.setOnClickListener(v -> {
            viewModel();
        });


        //replace fragment


    }

    private void selectCompanyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setSingleChoiceItems(R.array.productList, checkItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        TV_productType.setText(getResources().getString(R.string.software));
                        checkItems = 0;
                        productType = 0;
                        break;
                    case 1:
                        TV_productType.setText(getResources().getString(R.string.hardware));
                        checkItems = 1;
                        productType = 1;
                        break;
                    case 2:
                        TV_productType.setText(getResources().getString(R.string.all));
                        checkItems = 2;
                        productType = -1;
                        break;
                }
                dialog.dismiss();
            }
        });
        builder.create().show();
    }


    private void viewModel() {
        loadingView.setVisibility(View.VISIBLE);
        showEmpty.setVisibility(View.GONE);
        viewModel.getDarsadKharidShahrestan(ConstValue.startDate, ConstValue.endDate, productType);
        viewModel.darsadKharidShahrestanLiveData.observe(this, darsadKharidShahrestanModel  -> {
            if (darsadKharidShahrestanModel.getData().size() < 1){
                showEmpty.setVisibility(View.VISIBLE);
                loadingView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.INVISIBLE);
            }else {
                showEmpty.setVisibility(View.GONE);
                loadingView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                setupRecycler(darsadKharidShahrestanModel);
            }

        });
    }

    @Override
    public void itemEvents(int cityId) {
    Intent intent = new Intent(this,DarsadKharidShahrestanDetailActivity.class);
    intent.putExtra("cityId",cityId);
    intent.putExtra("productType",productType);
    startActivity(intent);
    }
}