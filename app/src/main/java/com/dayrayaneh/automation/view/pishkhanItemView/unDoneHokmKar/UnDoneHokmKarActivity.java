package com.dayrayaneh.automation.view.pishkhanItemView.unDoneHokmKar;

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
import com.dayrayaneh.automation.adapter.pishkhan.UndoneHokmKar.UnDoneHokmKarAdapter;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.model.pishkhan.UnDoneHokmKar.UnDoneHokmKarModel;
import com.dayrayaneh.automation.utils.Utils;
import com.dayrayaneh.automation.viewModel.pishkhan.UndoneHokmKar.UnDoneHokmKarViewModel;
import com.google.android.material.button.MaterialButton;

public class UnDoneHokmKarActivity extends AppCompatActivity {

    private UnDoneHokmKarViewModel thisViewModel;
    private TextView startDate , endDate;
    private MaterialButton send;
    private View loadingView;
    private View empty_layout;
    private UnDoneHokmKarAdapter adapter;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_un_done_hokm_kar);
        init();
        setDate();
        event();
        viewModel();
    }


    private void init() {
        startDate =findViewById(R.id.TV_fromDate);
        endDate = findViewById(R.id.Tv_toDate);
        send=findViewById(R.id.Mbtn_pishKhan_UnDonehokmKar_sendInfo);
        loadingView = findViewById(R.id.loading_view_undone);
        recyclerView = findViewById(R.id.RV_pishkhan_undoneHokmKar);
        empty_layout = findViewById(R.id.empty_undone);
        back = findViewById(R.id.ic_back);
        toolbar  = findViewById(R.id.toolbar_item_pishkhan);
        toolbar.setTitle(getResources().getString(R.string.unDoneHokmKar));
        setSupportActionBar(toolbar);
        thisViewModel = new ViewModelProvider(this).get(UnDoneHokmKarViewModel.class);
    }
    private void event() {
        send.setOnClickListener(view -> {
            viewModel();
        });
    }

    private void setDate(){
        Utils.setDate(startDate , endDate , this);
    }

    private void viewModel(){
        loadingView.setVisibility(View.VISIBLE);
        thisViewModel.getUnDoneHokmKar(ConstValue.startDate , ConstValue.endDate);
        thisViewModel.unDoneHokmKarModelMutableLiveData.observe(this, unDoneHokmKarModel -> {
            loadingView.setVisibility(View.GONE);
            if (unDoneHokmKarModel.getData().size() <1){
                empty_layout.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.INVISIBLE);
            }else {
                empty_layout.setVisibility(View.GONE);
                setRecyclerView(unDoneHokmKarModel);
            }
        });
    }

    private void setRecyclerView(UnDoneHokmKarModel unDoneHokmKarModel) {
        recyclerView.setVisibility(View.VISIBLE);
        adapter = new UnDoneHokmKarAdapter(this,unDoneHokmKarModel.getData());
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL , false));
        recyclerView.setAdapter(adapter);
    }

}