package com.dayrayaneh.automation.view.pishkhanItemView.hokmKarha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.adapter.pishkhan.darsadSefareshat.DarsadSefareshatAdapter;
import com.dayrayaneh.automation.base.BaseActivity;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.model.pishkhan.HokmKar.HokmKarModel;
import com.dayrayaneh.automation.utils.Utils;
import com.dayrayaneh.automation.view.pishkhanItemView.hokmKarha.fragments.HokmKarDetailFragment;
import com.dayrayaneh.automation.view.pishkhanItemView.hokmKarha.fragments.HokmKarMainFragment;
import com.dayrayaneh.automation.viewModel.pishkhan.HokmKar.HokmKarViewModel;
import com.dayrayaneh.automation.viewModel.pishkhan.darsadSefareshat.DarsadSefareshatViewModel;
import com.google.android.material.button.MaterialButton;

public class HokmKarhaActivity extends BaseActivity {


    private ImageView back;
    private TextView fromDate ,toDate ;
    private Toolbar toolbar;
    private DarsadSefareshatAdapter adapter;
    private RecyclerView recyclerView;
    private DarsadSefareshatViewModel viewModel;
    private View loadingView;
    private MaterialButton sendInfo;
    private HokmKarMainFragment mainFragment;
    private HokmKarDetailFragment detailFragment;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hokm_karha);

        init();
        event();
        setDate();
        showLoadingView();

    }

    private void init() {

        fromDate = findViewById(R.id.TV_fromDate);
        toDate = findViewById(R.id.Tv_toDate);
        toolbar = findViewById(R.id.toolbar_item_pishkhan);
        toolbar.setTitle(getResources().getString(R.string.hokmKarha));
        setSupportActionBar(toolbar);
        back = findViewById(R.id.IV_back_item_pishkhan);
        sendInfo = findViewById(R.id.Mbtn_pishKhan_hokmKar_sendInfo);
        mainFragment = new HokmKarMainFragment();
        loadingView = findViewById(R.id.loading_view);



        //////set fragmentMain
        getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frameLayout_hokmKar , mainFragment, "hokmKarMain").commit();


    }
    private void event(){


        back.setOnClickListener(v -> {
            if (getSupportFragmentManager().findFragmentByTag("hokmKarDetail") !=null){
                getSupportFragmentManager().popBackStack();
            }else {
               finish();
            }
        });

      HokmKarDetailFragment.hideButtonSendIndo.observe(this,hide->{
          if (hide){
              sendInfo.setVisibility(View.GONE);
              findViewById(R.id.include7).setVisibility(View.GONE);
          }else {
              sendInfo.setVisibility(View.VISIBLE);
              findViewById(R.id.include7).setVisibility(View.VISIBLE);
          }
      });

        sendInfo.setOnClickListener(v -> {
         mainFragment.setViewModel();
        });



    }

    private void showLoadingView(){

        mainFragment.loadinLiveData.observe(this,mustShow->{
            if (mustShow){
                loadingView.setVisibility(View.VISIBLE);
            }else {
                loadingView.setVisibility(View.GONE);
            }
        });
    }

    private void setDate(){
        Utils.setDate(fromDate , toDate , this);
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentByTag("hokmKarDetail") !=null){
            getSupportFragmentManager().popBackStack();
        }else if (getSupportFragmentManager().findFragmentByTag("hokmKarMain") != null){
         finish();
        }else {
            super.onBackPressed();
        }
    }
}