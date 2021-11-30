package com.dayrayaneh.automation.view.pishkhanItemView.sefarashMoshtariJadid;

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
import com.dayrayaneh.automation.adapter.pishkhan.VaziatSefaresh.VaziatSefareshAdapter;
import com.dayrayaneh.automation.adapter.pishkhan.sefareshMoshtariJadid.SefareshMoshtariJadidAdapter;
import com.dayrayaneh.automation.base.BaseActivity;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.model.pishkhan.sefareshMoshtariJadid.SefareshMoshtariModel;
import com.dayrayaneh.automation.model.pishkhan.vaziatSefareshat.VaziatSefareshatModel;
import com.dayrayaneh.automation.utils.Utils;
import com.dayrayaneh.automation.viewModel.pishkhan.sefareshMoshtariJadid.SefareshMoshtariVeiwModel;
import com.dayrayaneh.automation.viewModel.pishkhan.vaziatSefareshat.VaziatSefareshatViewModel;
import com.google.android.material.button.MaterialButton;

public class SefareshMoshtariJadidActivity extends BaseActivity {

    private ImageView back;
    private TextView fromDate ,toDate ;
    private Toolbar toolbar;
    private SefareshMoshtariJadidAdapter adapter;
    private RecyclerView recyclerView;
    private SefareshMoshtariVeiwModel viewModel;
    private View loadingView;
    private MaterialButton sendInfo;
    private View showEmpty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sefaresh_moshtari_jadid);

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
        toolbar.setTitle(getResources().getString(R.string.sefareshatMoshtariJadid));
        setSupportActionBar(toolbar);
        loadingView = findViewById(R.id.loading_view);
        showEmpty = findViewById(R.id.showEmpty);
        viewModel = new ViewModelProvider(this).get(SefareshMoshtariVeiwModel.class);
        recyclerView = findViewById(R.id.RV_sefareshMoshtariJadid);
        sendInfo = findViewById(R.id.Mbtn_pishKhan_SefareshMoshtariJadid_sendInfo);



    }

    private void setDate(){
        Utils.setDate(fromDate , toDate , this);
    }
    private void viewModel(){
        loadingView.setVisibility(View.VISIBLE);
        viewModel.getSefareshMoshtariJaid(ConstValue.startDate , ConstValue.endDate);
        viewModel.sefareshMoshtariLiveData.observe(this,sefareshMoshtariModel -> {
            if (sefareshMoshtariModel.getData().size() < 1){
                showEmpty.setVisibility(View.VISIBLE);
                loadingView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
            }else{
                showEmpty.setVisibility(View.GONE);
                loadingView.setVisibility(View.GONE);
                setRecyclerView(sefareshMoshtariModel);
            }
        });

    }

    private void setRecyclerView(SefareshMoshtariModel sefareshMoshtariModel) {

        adapter = new SefareshMoshtariJadidAdapter(this,sefareshMoshtariModel.getData());
        recyclerView.setVisibility(View.VISIBLE);
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