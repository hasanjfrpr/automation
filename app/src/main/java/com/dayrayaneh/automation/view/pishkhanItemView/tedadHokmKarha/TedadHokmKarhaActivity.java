package com.dayrayaneh.automation.view.pishkhanItemView.tedadHokmKarha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.MutableLiveData;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.adapter.pishkhan.tedadHokmKar.details.TedadHokmKarDetailsAdapter;
import com.dayrayaneh.automation.base.BaseActivity;
import com.dayrayaneh.automation.utils.Utils;
import com.dayrayaneh.automation.view.pishkhanItemView.tedadHokmKarha.fragment.TedadHokmKarDetailFragment;
import com.dayrayaneh.automation.view.pishkhanItemView.tedadHokmKarha.fragment.TedadHokmKarMainFragment;
import com.google.android.material.button.MaterialButton;

import org.greenrobot.eventbus.EventBus;

public class TedadHokmKarhaActivity extends BaseActivity {


    private ImageView back;
    private TextView startDate, endDate;
    private Toolbar toolbar;
    private MaterialButton sendInfo;
    private View customHeader , loadingView;
    private TedadHokmKarMainFragment mainFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tedad_hokm_karha);

        init();
        setDate();
        event();

    }


    private void init(){
        back = findViewById(R.id.IV_back_item_pishkhan);
        startDate = findViewById(R.id.TV_fromDate);
        endDate = findViewById(R.id.Tv_toDate);
        toolbar = findViewById(R.id.toolbar_item_pishkhan);
        toolbar.setTitle(getResources().getString(R.string.tedadHokmKarha));
        sendInfo = findViewById(R.id.Mbtn_tedadHokmKar_sendInfo);
        setSupportActionBar(toolbar);
        customHeader = findViewById(R.id.include8);
        mainFragment = new TedadHokmKarMainFragment();
        loadingView = findViewById(R.id.loading_view1);


        ////inflte mainFr
        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer_tedadHokmKar , new TedadHokmKarMainFragment(),"maintedad").commit();


    }

    private void setDate(){
        Utils.setDate(startDate , endDate , this);
    }

    private void event(){
        sendInfo.setOnClickListener(v -> {
           TedadHokmKarMainFragment mainFragment = (TedadHokmKarMainFragment) getSupportFragmentManager().findFragmentByTag("maintedad");
           mainFragment.viewModel();

        });

        back.setOnClickListener(v->{
            if (getSupportFragmentManager().findFragmentByTag("detailsTedad") != null){
            getSupportFragmentManager().popBackStack();}
            else {
                finish();
            }
        });

        TedadHokmKarMainFragment.mustShowLoading.observe(this,mustShow->{
            if (mustShow){
                loadingView.setVisibility(View.VISIBLE);
            }else {
                loadingView.setVisibility(View.GONE);
            }
        });

        TedadHokmKarDetailFragment.mustHide.observe(this , mustHide->{
            if (mustHide){
                customHeader.setVisibility(View.GONE);
                sendInfo.setVisibility(View.GONE);
            }else {
                customHeader.setVisibility(View.VISIBLE);
                sendInfo.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentByTag("detailsTedad") != null){
            getSupportFragmentManager().popBackStack();}
        else {
            super.onBackPressed();
        }
    }
}