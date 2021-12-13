package com.dayrayaneh.automation.view.pishkhanItemView.darsadKhardiMoshtari;

import androidx.appcompat.app.AlertDialog;
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
import com.dayrayaneh.automation.adapter.pishkhan.pishkan_darsadKharidMoshtarian.DarsadKharidMoshtarianAdapter;
import com.dayrayaneh.automation.base.BaseActivity;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.model.pishkhan.darsadKharidMoshtari.DarsadkharidMoshtariModel;
import com.dayrayaneh.automation.utils.Utils;
import com.dayrayaneh.automation.viewModel.pishkhan.darsadKharidMoshtari.DarsadKharidMoshtariViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;

import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

public class DarsadKharidMoshtariActivity extends BaseActivity  implements  DarsadKharidMoshtarianAdapter.Events{

    private ImageView back;
    private TextView fromDate, toDate, TV_productType;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private DarsadKharidMoshtarianAdapter adapter;
    private DarsadKharidMoshtariViewModel darsadKharidMoshtariViewModel;
    private View loadingView , showEmpty;
    private int productType = -1;
    private MaterialCardView selectProductType;
    private MaterialButton sendInfo;
    private int checkItems = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ddarsad_kharid_moshtari);
        init();
        setData();
        event();
        viewModel();

    }


    private void init() {
        back = findViewById(R.id.IV_back_item_pishkhan);
        toolbar = findViewById(R.id.toolbar_item_pishkhan);
        toolbar.setTitle(getResources().getString(R.string.darsadKharidMoshtari));
        setSupportActionBar(toolbar);
        fromDate = findViewById(R.id.TV_fromDate);
        toDate = findViewById(R.id.Tv_toDate);
        recyclerView = findViewById(R.id.RV_pishKhan_darsadKharidMoshtarian);
        loadingView = findViewById(R.id.loading_bazaryabi);
        sendInfo = findViewById(R.id.Mbtn_pishkhan_darsadKharidMoshtari_saveInfo);
        selectProductType = findViewById(R.id.Mcv_darsadKharidMoshtari_select_productType);
        TV_productType = findViewById(R.id.TV_darsadKharidMoshtari_select_productType);
        showEmpty = findViewById(R.id.showEmpty);
        darsadKharidMoshtariViewModel = new ViewModelProvider(this).get(DarsadKharidMoshtariViewModel.class);

    }

    private void setData() {

        Utils.setDate(fromDate, toDate, this);

    }

    private void setupRecycler(DarsadkharidMoshtariModel darsadKharidMoshtariMode) {
        adapter = new DarsadKharidMoshtarianAdapter(darsadKharidMoshtariMode.getData(), this);
        adapter.events = this;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

    private void event() {
        ///onclick
        back.setOnClickListener(v -> {
            finish();
        });


        selectProductType.setOnClickListener(v -> {
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
        darsadKharidMoshtariViewModel.getDarsadKharidMoshtari(ConstValue.startDate, ConstValue.endDate, productType);
        darsadKharidMoshtariViewModel.darsadkharidMoshtariLiveData.observe(this, darsadkharidMoshtariModel -> {

            if (darsadkharidMoshtariModel.getData().size() <1 ){
                showEmpty.setVisibility(View.VISIBLE);
                loadingView.setVisibility(View.GONE);
            }else {
                showEmpty.setVisibility(View.GONE);
                loadingView.setVisibility(View.GONE);
                setupRecycler(darsadkharidMoshtariModel);
            }

        });
    }


    @Override
    public void itemEventClick(int moshtarianId) {
        Intent intent = new Intent(this , DarsadKharidMoshtariDetailsActivity.class);
        intent.putExtra("productId",productType);
        intent.putExtra("moshtarianId",moshtarianId );
        startActivity(intent);
    }
}