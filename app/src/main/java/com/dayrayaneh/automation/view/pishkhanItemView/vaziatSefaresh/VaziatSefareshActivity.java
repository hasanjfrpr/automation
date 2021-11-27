package com.dayrayaneh.automation.view.pishkhanItemView.vaziatSefaresh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.adapter.pishkhan.VaziatSefaresh.VaziatSefareshAdapter;
import com.dayrayaneh.automation.adapter.pishkhan.darsadSefareshat.DarsadSefareshatAdapter;
import com.dayrayaneh.automation.base.BaseActivity;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.model.pishkhan.vaziatSefareshat.VaziatSefareshatModel;
import com.dayrayaneh.automation.utils.Utils;
import com.dayrayaneh.automation.view.pishkhanItemView.hokmKarha.fragments.HokmKarDetailFragment;
import com.dayrayaneh.automation.view.pishkhanItemView.hokmKarha.fragments.HokmKarMainFragment;
import com.dayrayaneh.automation.viewModel.pishkhan.darsadSefareshat.DarsadSefareshatViewModel;
import com.dayrayaneh.automation.viewModel.pishkhan.vaziatSefareshat.VaziatSefareshatViewModel;
import com.google.android.material.button.MaterialButton;

public class VaziatSefareshActivity extends BaseActivity {


    private ImageView back;
    private TextView fromDate ,toDate ;
    private Toolbar toolbar;
    private VaziatSefareshAdapter adapter;
    private RecyclerView recyclerView;
    private VaziatSefareshatViewModel viewModel;
    private View loadingView;
    private MaterialButton sendInfo;
    private View showEmpty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaziat_sefaresh);

        init();
        setDate();
        viewModel();
        event();


    }

    private void init() {
        back = findViewById(R.id.IV_back_item_pishkhan);
        fromDate =findViewById(R.id.TV_fromDate);
        toDate = findViewById(R.id.Tv_toDate);
        toolbar = findViewById(R.id.toolbar_item_pishkhan);
        toolbar.setTitle(getResources().getString(R.string.vaziatSefaresh));
        setSupportActionBar(toolbar);
        loadingView = findViewById(R.id.loading_view);
        showEmpty = findViewById(R.id.showEmpty);
        viewModel = new ViewModelProvider(this).get(VaziatSefareshatViewModel.class);
        recyclerView = findViewById(R.id.RV_vaziatSefareshat);
        sendInfo = findViewById(R.id.Mbtn_pishKhan_vaziatSefareshat_sendInfo);

    }

    private void setDate(){
        Utils.setDate(fromDate , toDate , this);
    }
    private void viewModel(){
        loadingView.setVisibility(View.VISIBLE);
        viewModel.getVaziatSefareshat(ConstValue.startDate , ConstValue.endDate);
        viewModel.vaziatSefareshatleLiveData.observe(this,vaziatSefareshatModel -> {
           if (vaziatSefareshatModel.getData().size() < 1 ){
               showEmpty.setVisibility(View.VISIBLE);
           }else {
               showEmpty.setVisibility(View.GONE);
               loadingView.setVisibility(View.GONE);
               setRecyclerView(vaziatSefareshatModel);
           }
        });
    }

    private void setRecyclerView(VaziatSefareshatModel vaziatSefareshatModel) {

        adapter = new VaziatSefareshAdapter(this,vaziatSefareshatModel.getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

    }

    private void event(){
        sendInfo.setOnClickListener(v->{
            viewModel();
        });

        back.setOnClickListener(v -> {
            finish();
        });
    }
}