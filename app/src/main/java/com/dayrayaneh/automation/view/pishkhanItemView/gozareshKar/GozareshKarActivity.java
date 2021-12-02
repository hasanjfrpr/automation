package com.dayrayaneh.automation.view.pishkhanItemView.gozareshKar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.adapter.pishkhan.VaziatSefaresh.VaziatSefareshAdapter;
import com.dayrayaneh.automation.base.BaseActivity;
import com.dayrayaneh.automation.utils.Utils;
import com.dayrayaneh.automation.view.pishkhanItemView.gozareshKar.Fragment.GozareshKarMainFragment;
import com.dayrayaneh.automation.viewModel.pishkhan.vaziatSefareshat.VaziatSefareshatViewModel;
import com.google.android.material.button.MaterialButton;

public class GozareshKarActivity extends BaseActivity {

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
        setContentView(R.layout.activity_gozaresh_kar);
        init();
        setDate();
        event();
    }


    private void init() {
        back = findViewById(R.id.IV_back_item_pishkhan);
        fromDate =findViewById(R.id.TV_fromDate);
        toDate = findViewById(R.id.Tv_toDate);
        toolbar = findViewById(R.id.toolbar_item_pishkhan);
        toolbar.setTitle(getResources().getString(R.string.gozareshKar));
        setSupportActionBar(toolbar);
        loadingView = findViewById(R.id.loading_view);
        sendInfo = findViewById(R.id.Mbtn_gozareshKar_sendInfo);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer_gozareshkar , new GozareshKarMainFragment(),"gozareshMain").commit();

    }

    private void setDate(){
        Utils.setDate(fromDate , toDate , this);
    }

    private void event() {
        back.setOnClickListener(v->{
            if (getSupportFragmentManager().findFragmentByTag("detailGozareshKar") != null){
                getSupportFragmentManager().popBackStack();
            }else{
                finish();
            }

        });
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentByTag("detailGozareshKar") != null){
            getSupportFragmentManager().popBackStack();
        }else{
            super.onBackPressed();
        }
    }
}