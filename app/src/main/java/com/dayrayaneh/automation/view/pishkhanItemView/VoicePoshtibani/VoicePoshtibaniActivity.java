package com.dayrayaneh.automation.view.pishkhanItemView.VoicePoshtibani;

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
import com.dayrayaneh.automation.adapter.pishkhan.voicePoshtibani.VoiceAdapter;
import com.dayrayaneh.automation.base.BaseActivity;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.dialog.SearchVoiceDialog;
import com.dayrayaneh.automation.dialog.VoiceDialog;
import com.dayrayaneh.automation.model.pishkhan.voicePoshtibani.VoiceModel;
import com.dayrayaneh.automation.utils.Utils;
import com.dayrayaneh.automation.viewModel.pishkhan.Voice.VoiceViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VoicePoshtibaniActivity extends BaseActivity implements VoiceAdapter.Events {


    private FloatingActionButton fab;
    private ImageView back;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private TextView startDate , endDate;
    private VoiceViewModel thisViewModel;
    private VoiceAdapter adapter;
    private View loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_poshtibani);
        init();
        setDate();
        event();
        viewModel();

    }

    private void init() {
        fab = findViewById(R.id.fab_voice);
        back = findViewById(R.id.IV_back_item_pishkhan);
        toolbar = findViewById(R.id.toolbar_item_pishkhan);
        startDate = findViewById(R.id.TV_fromDate);
        endDate = findViewById(R.id.Tv_toDate);
        toolbar.setTitle(getResources().getString(R.string.voicePoshtibani));
        setSupportActionBar(toolbar);
        loading = findViewById(R.id.loading);
        recyclerView = findViewById(R.id.RV_voice);
        thisViewModel = new ViewModelProvider(this).get(VoiceViewModel.class);

    }
    private void event(){
        back.setOnClickListener(v->{
            finish();
        });

        fab.setOnClickListener(v->{
            SearchVoiceDialog d = new SearchVoiceDialog();
            d.show(getSupportFragmentManager() , "");
        });
    }

    private void viewModel(){
        loading.setVisibility(View.VISIBLE);
        thisViewModel.getVoice(ConstValue.startDate , ConstValue.endDate , "" , "");
        thisViewModel.voiceLiveData.observe(this,voiceModel -> {
            loading.setVisibility(View.GONE);
            setupRecyclerView(voiceModel);
        });
    }

    private void setupRecyclerView(VoiceModel voiceModel) {
        adapter = new VoiceAdapter(this , voiceModel.getData());
        adapter.events = this;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL, false));
    }

    private void setDate(){
        Utils.setDate(startDate,endDate , this);
    }

    @Override
    public void itemEvents(String uniqueId) {
        ConstValue.uniqueIdVoice = uniqueId;
        VoiceDialog voiceDialog = new VoiceDialog();
        voiceDialog.show(getSupportFragmentManager() , ""  );
    }
}