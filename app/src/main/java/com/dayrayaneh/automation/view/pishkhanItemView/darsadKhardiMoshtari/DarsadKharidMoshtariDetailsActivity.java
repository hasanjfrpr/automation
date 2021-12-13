package com.dayrayaneh.automation.view.pishkhanItemView.darsadKhardiMoshtari;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.adapter.pishkhan.pishkan_darsadKharidMoshtarian.DarsadKharidMoshtarianAdapter;
import com.dayrayaneh.automation.adapter.pishkhan.pishkan_darsadKharidMoshtarian.details.DarsadKharidMoshtarianDetalisAdapter;
import com.dayrayaneh.automation.base.BaseActivity;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.model.pishkhan.darsadKharidMoshtari.details.DarsadKharidMoshtariDetailsModel;
import com.dayrayaneh.automation.viewModel.pishkhan.darsadKharidMoshtari.DarsadKharidMoshtariViewModel;

public class DarsadKharidMoshtariDetailsActivity extends BaseActivity {

    private DarsadKharidMoshtariViewModel thisViewModel;
    private View loadingView;
    private RecyclerView recyclerView;
    private DarsadKharidMoshtarianDetalisAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_darsad_kharid_moshtari_details_activirty);
        init();
        viewModel();
    }


    private void init() {
        loadingView = findViewById(R.id.loading_view);
        thisViewModel = new ViewModelProvider(this).get(DarsadKharidMoshtariViewModel.class);
        recyclerView = findViewById(R.id.RV_pishKhan_darsadKharidShahrestan_detail);
    }

    private void viewModel() {
        Bundle bundle = getIntent().getExtras();
        int productType = bundle.getInt("productId");
        int moshtarianId = bundle.getInt("moshtarianId");
        loadingView.setVisibility(View.VISIBLE);
        thisViewModel.getDarsadKharidMoshtariDetails(ConstValue.startDate , ConstValue.endDate , productType , moshtarianId);
        thisViewModel.darsadkharidMoshtariDetailLiveData.observe(this,darsadKharidMoshtariDetailsModel -> {
            loadingView.setVisibility(View.GONE);
            setupRecyclerView(darsadKharidMoshtariDetailsModel);
        });
    }

    private void setupRecyclerView(DarsadKharidMoshtariDetailsModel darsadKharidMoshtariDetailsModel) {
        adapter = new DarsadKharidMoshtarianDetalisAdapter(this , darsadKharidMoshtariDetailsModel.getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL , false));
    }


}