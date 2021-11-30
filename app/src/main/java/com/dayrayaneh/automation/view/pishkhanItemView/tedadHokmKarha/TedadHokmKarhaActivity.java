package com.dayrayaneh.automation.view.pishkhanItemView.tedadHokmKarha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.base.BaseActivity;
import com.dayrayaneh.automation.utils.Utils;
import com.dayrayaneh.automation.view.pishkhanItemView.tedadHokmKarha.fragment.TedadHokmKarMainFragment;
import com.google.android.material.button.MaterialButton;

public class TedadHokmKarhaActivity extends BaseActivity {


    private ImageView back;
    private TextView startDate, endDate;
    private Toolbar toolbar;
    private MaterialButton sendInfo;


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


        ////inflte mainFr
        getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frameContainer_tedadHokmKar , new TedadHokmKarMainFragment()).commit();


    }

    private void setDate(){
        Utils.setDate(startDate , endDate , this);
    }

    private void event(){
        sendInfo.setOnClickListener(v -> {

        });

        back.setOnClickListener(v->{
            finish();
        });
    }


}