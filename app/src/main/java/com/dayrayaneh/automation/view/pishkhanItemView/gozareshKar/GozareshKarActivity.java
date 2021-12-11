package com.dayrayaneh.automation.view.pishkhanItemView.gozareshKar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.adapter.pishkhan.VaziatSefaresh.VaziatSefareshAdapter;
import com.dayrayaneh.automation.adapter.pishkhan.gozareshKar.spinnerAdapter.SpinnerAdapter;
import com.dayrayaneh.automation.base.BaseActivity;
import com.dayrayaneh.automation.model.pishkhan.Gozareshkar.personalName.DataItem;
import com.dayrayaneh.automation.utils.Utils;
import com.dayrayaneh.automation.view.pishkhanItemView.gozareshKar.Fragment.GozareshKarMainFragment;
import com.dayrayaneh.automation.view.pishkhanItemView.gozareshKar.Fragment.GozareshkarDetailFragment;
import com.dayrayaneh.automation.viewModel.pishkhan.GozareshKarha.GozareshKarViewModel;
import com.github.bkhezry.searchablespinner.SearchableSpinner;
import com.github.bkhezry.searchablespinner.interfaces.IStatusListener;
import com.github.bkhezry.searchablespinner.interfaces.OnItemSelectedListener;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class GozareshKarActivity extends BaseActivity {

    private ImageView back;
    private TextView fromDate ,toDate ;
    private Toolbar toolbar;
    private VaziatSefareshAdapter adapter;
    private RecyclerView recyclerView;
    private GozareshKarViewModel viewModel;
    private View loadingView;
    private MaterialButton sendInfo;
    private View showEmpty ;
    private CheckBox checkBox;
    public static int personId =0;
    public static int personIdHelp =0;
    private SearchableSpinner searchableSpinner;
    private List<DataItem> personIdList = new ArrayList<>();
    private SpinnerAdapter spinnerAdapter;
    public static boolean isCheck = false;
    private LinearLayout containerSpinnerAndcheckbox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gozaresh_kar);
        init();
        setDate();
        viewModel();
        event();
        spinner();
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
        checkBox = findViewById(R.id.checkbox_gozareshKar);
        searchableSpinner = findViewById(R.id.spinner_gozareshKar);
        containerSpinnerAndcheckbox = findViewById(R.id.linearLayout3);
        viewModel = new ViewModelProvider(this).get(GozareshKarViewModel.class);


        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer_gozareshkar , new GozareshKarMainFragment(),"gozareshMain").commit();

    }
    private void viewModel() {
        viewModel.getPersonalList();
        viewModel.personalListLiveData.observe(this,personalListModel -> {
            personIdList.addAll(personalListModel.getData());
        });
    }



    private void spinner(){
        spinnerAdapter = new SpinnerAdapter(this,personIdList);
        searchableSpinner.setAdapter(spinnerAdapter);

        searchableSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(View view, int position, long id) {
                Toast.makeText(GozareshKarActivity.this, id+"", Toast.LENGTH_SHORT).show();
                personId = (int) id;
                personIdHelp = (int)id ;
            }

            @Override
            public void onNothingSelected() {

            }
        });

        searchableSpinner.setStatusListener(new IStatusListener() {
            @Override
            public void spinnerIsOpening() {
                Toast.makeText(GozareshKarActivity.this, "open", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void spinnerIsClosing() {
                Toast.makeText(GozareshKarActivity.this, "close", Toast.LENGTH_SHORT).show();
            }
        });




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





        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    isCheck=true;
                    searchableSpinner.setClickable(false);
                    searchableSpinner.setVisibility(View.INVISIBLE);
                    searchableSpinner.hideEdit();
                    personId = 0;

                }else {
                    searchableSpinner.setClickable(true);
                    searchableSpinner.setVisibility(View.VISIBLE);

                    isCheck = false;

                }
            }
        });


        GozareshkarDetailFragment.showAndHide.observe(this,hide->{
            if (hide){
                findViewById(R.id.include9).setVisibility(View.GONE);
                sendInfo.setVisibility(View.GONE);
                containerSpinnerAndcheckbox.setVisibility(View.GONE);

            }else {
                findViewById(R.id.include9).setVisibility(View.VISIBLE);
                sendInfo.setVisibility(View.VISIBLE);
                containerSpinnerAndcheckbox.setVisibility(View.VISIBLE);
            }
        });

        GozareshKarMainFragment.showLoading.observe(this,showLoading->{
            if (showLoading){
                loadingView.setVisibility(View.VISIBLE);
            }else {
                loadingView.setVisibility(View.GONE);
            }
        });

        sendInfo.setOnClickListener(v->{
            GozareshKarMainFragment mainFragment = (GozareshKarMainFragment) getSupportFragmentManager().findFragmentByTag("gozareshMain");
            mainFragment.viewModel();
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