package com.dayrayaneh.automation.view.pishkhanItemView.darsadTakhfifAzHarSefaresh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.adapter.pishkhan.darsadKharidShahrestan.DarsadKharidShahrestanAdapter;
import com.dayrayaneh.automation.adapter.pishkhan.darsadThakhfifAzHarSefaresh.DarsadTakhfifAzHarSefareshAdapter;
import com.dayrayaneh.automation.base.BaseActivity;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.model.pishkhan.darsadKharidShahrestan.DarsadKharidShahrestanModel;
import com.dayrayaneh.automation.model.pishkhan.darsadThakhfifAzHarSefaresh.DarsadTakhfifAzHarSefareshModel;
import com.dayrayaneh.automation.utils.Utils;
import com.dayrayaneh.automation.viewModel.pishkhan.darsadKharidShahrestan.DarsadKharidShahrestanViewModel;
import com.dayrayaneh.automation.viewModel.pishkhan.darsadTakhfifAzHarSefaresh.DarsadTakhfifAzHarSefareshViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

public class DarsadTakhfifAzHarSefareshActivity extends BaseActivity {


    private ImageView back;
    private TextView fromDate ,toDate;
    private Toolbar toolbar;
    private DarsadTakhfifAzHarSefareshAdapter adapter;
    private RecyclerView recyclerView;
    private MaterialButton sendInfo;
    private DarsadTakhfifAzHarSefareshViewModel viewModel;
    private View loadingView , showEmpty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_darsad_takhfif_az_har_sefaresh);
        init();
        event();
        setDate();
        viewModel();
    }


    private void init(){
        back = findViewById(R.id.IV_back_item_pishkhan);
        fromDate = findViewById(R.id.TV_fromDate);
        toDate = findViewById(R.id.Tv_toDate);
        toolbar = findViewById(R.id.toolbar_item_pishkhan);
        toolbar.setTitle(getResources().getString(R.string.darsadTakhfifAzHarsefaresh));
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.RV_darsadTakhfifAzHarsefaresh);
        sendInfo = findViewById(R.id.Mbtn_darsadTakhfifAzHarsefaresh_sendInfo);
        loadingView = findViewById(R.id.loading_view);
        showEmpty = findViewById(R.id.showEmpty);

        ////viewMode init
        viewModel = new ViewModelProvider(this).get(DarsadTakhfifAzHarSefareshViewModel.class);


    }
    private void event() {
        ///onclick
        back.setOnClickListener(v -> {
            finish();
        });



        sendInfo.setOnClickListener(v -> {
            viewModel();
        });




    }

    private void setDate(){
        Utils.setDate(fromDate , toDate , this);
    }

    private void viewModel() {
        loadingView.setVisibility(View.VISIBLE);

        viewModel.getDarsadTakhfifAzHarSefaresh(ConstValue.startDate, ConstValue.endDate);

        viewModel.darsadTakhfifAzHarSefareshLiveData.observe(this, darsadTakhfifAzHarSefareshModel  -> {
           if (darsadTakhfifAzHarSefareshModel.getData().size() < 1){
               showEmpty.setVisibility(View.VISIBLE);
               loadingView.setVisibility(View.GONE);
           }else {
               showEmpty.setVisibility(View.GONE);
               loadingView.setVisibility(View.GONE);
               setupRecycler(darsadTakhfifAzHarSefareshModel);
           }

        });
    }
    private void setupRecycler(DarsadTakhfifAzHarSefareshModel darsadTakhfifAzHarSefareshModel) {
        adapter = new DarsadTakhfifAzHarSefareshAdapter(this,darsadTakhfifAzHarSefareshModel.getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }


}