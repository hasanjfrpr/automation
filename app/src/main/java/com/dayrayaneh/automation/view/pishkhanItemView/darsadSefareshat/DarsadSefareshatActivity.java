package com.dayrayaneh.automation.view.pishkhanItemView.darsadSefareshat;

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
import com.dayrayaneh.automation.adapter.pishkhan.darsadSefareshat.DarsadSefareshatAdapter;
import com.dayrayaneh.automation.base.BaseActivity;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.model.pishkhan.darsadSefareshat.DarsadSefareshatModel;
import com.dayrayaneh.automation.utils.Utils;
import com.dayrayaneh.automation.viewModel.pishkhan.darsadKharidShahrestan.DarsadKharidShahrestanViewModel;
import com.dayrayaneh.automation.viewModel.pishkhan.darsadSefareshat.DarsadSefareshatViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

public class DarsadSefareshatActivity extends BaseActivity {


    private ImageView back;
    private TextView fromDate ,toDate ;
    private Toolbar toolbar;
    private DarsadSefareshatAdapter adapter;
    private RecyclerView recyclerView;
    private DarsadSefareshatViewModel viewModel;
    private View loadingView , showEmpty;
    private MaterialButton sendInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_darsad_takhfifat);
        init();
        setDate();
        event();
        viewModel();
    }

    private void init() {
        recyclerView = findViewById(R.id.RV_pishkhan_darsadSefareshat);
        fromDate = findViewById(R.id.TV_fromDate);
        toDate = findViewById(R.id.Tv_toDate);
        toolbar = findViewById(R.id.toolbar_item_pishkhan);
        toolbar.setTitle(getResources().getString(R.string.darsadSefareshat));
        setSupportActionBar(toolbar);
        back = findViewById(R.id.IV_back_item_pishkhan);
        loadingView = findViewById(R.id.loading_view);
        sendInfo = findViewById(R.id.Mbtn_pishkhan_darsadSefareshat_sendInfo);
        showEmpty = findViewById(R.id.showEmpty);
        viewModel = new ViewModelProvider(this).get(DarsadSefareshatViewModel.class);

    }
    private void event(){
        back.setOnClickListener(v -> { finish(); });

        sendInfo.setOnClickListener(v -> {viewModel();});

    }

    private void setDate(){
        Utils.setDate(fromDate , toDate , this);
    }

   private void viewModel(){
        loadingView.setVisibility(View.VISIBLE);
        viewModel.getDarsadSefareshat(ConstValue.startDate , ConstValue.endDate);
        viewModel.darsadSefareshatLiveData.observe(this,darsadSefareshatModel -> {
          if (darsadSefareshatModel.getData().size() <1 ){
              showEmpty.setVisibility(View.VISIBLE);
              loadingView.setVisibility(View.GONE);
          }else {
              showEmpty.setVisibility(View.GONE);
              loadingView.setVisibility(View.GONE);
              setRecyclerView(darsadSefareshatModel);
          }
        });
   }

    private void setRecyclerView(DarsadSefareshatModel darsadSefareshatModel) {
        adapter = new DarsadSefareshatAdapter(darsadSefareshatModel.getData(),this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL , false));
    }
}