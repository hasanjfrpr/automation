package com.dayrayaneh.automation.view.pishkhanItemView.darsadKharidShahrestanha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.adapter.pishkhan.darsadKharidShahrestan.details.DarsadKharidShahrestanDetialsAdapter;
import com.dayrayaneh.automation.base.BaseActivity;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.model.pishkhan.darsadKharidShahrestan.details.DarsadKharidShahrestanDetailsModel;
import com.dayrayaneh.automation.viewModel.pishkhan.darsadKharidShahrestan.DarsadKharidShahrestanViewModel;

public class DarsadKharidShahrestanDetailActivity extends BaseActivity {

    private DarsadKharidShahrestanViewModel thisViewModel;
    private RecyclerView recyclerView;
    private DarsadKharidShahrestanDetialsAdapter adapter;
    private View loadingView;
    private Toolbar toolbar;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_darsad_kharid_shahrestan_detail);
        init();
        viewModel();
        event();
    }

    private void init() {
        recyclerView = findViewById(R.id.RV_pishKhan_darsadKharidShahrestan_detail);
        loadingView = findViewById(R.id.loading_view);
        back = findViewById(R.id.ic_back);
        toolbar = findViewById(R.id.toolbar_darsadKharidShahrestanDetail);
        toolbar.setTitle("جزئیات "+getResources().getString(R.string.darsadKharidShahrestan));
        setSupportActionBar(toolbar);
        thisViewModel = new ViewModelProvider(this).get(DarsadKharidShahrestanViewModel.class);
    }

    private void viewModel(){
        Bundle bundle = getIntent().getExtras();
        int productType = bundle.getInt("productType");
        int cityId = bundle.getInt("cityId");
        loadingView.setVisibility(View.VISIBLE);
        thisViewModel.getDarsadKharidShahrestanDetails(ConstValue.startDate , ConstValue.endDate , productType , cityId);
        thisViewModel.darsadKharidShahrestanDetailsLiveData.observe(this , darsadKharidShahrestanDetailsModel -> {
            loadingView.setVisibility(View.GONE);
            setupRecyclerView(darsadKharidShahrestanDetailsModel);
        });
    }

    private void event(){
        back.setOnClickListener(v->{
            finish();
        });
    }

    private void setupRecyclerView(DarsadKharidShahrestanDetailsModel darsadKharidShahrestanDetailsModel) {
        adapter = new DarsadKharidShahrestanDetialsAdapter(this , darsadKharidShahrestanDetailsModel.getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this , RecyclerView.VERTICAL , false));
    }


//
}