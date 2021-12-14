package com.dayrayaneh.automation.view.pishkhanItemView.darsadKhardiMoshtari;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

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
    private Toolbar toolbar;
    private ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_darsad_kharid_moshtari_details_activirty);
        init();
        event();
        viewModel();
    }


    private void init() {
        loadingView = findViewById(R.id.loading_viewss);
        thisViewModel = new ViewModelProvider(this).get(DarsadKharidMoshtariViewModel.class);
        recyclerView = findViewById(R.id.RV_pishKhan_darsadKharidMoshtari_detail);
        toolbar = findViewById(R.id.toolbar_darsadKharidMoshtariDetail);
        toolbar.setTitle("جزئیات "+getResources().getString(R.string.darsadKharidMoshtari));
        setSupportActionBar(toolbar);
        back  = findViewById(R.id.ic_backs);
    }

    private void event(){
        back.setOnClickListener(v -> {
            finish();
        });
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