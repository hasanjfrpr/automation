package com.dayrayaneh.automation.view.pishkhanItemView.gozareshKar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.adapter.pishkhan.VaziatSefaresh.VaziatSefareshAdapter;
import com.dayrayaneh.automation.adapter.pishkhan.gozareshKar.main.GozareshKarMainAdapter;
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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class GozareshKarActivity extends BaseActivity {

    private ImageView back;
    private TextView fromDate ,toDate ;
    private Toolbar toolbar;
    public static MutableLiveData<String > searchTextLiveData = new MutableLiveData<>();
    private RecyclerView recyclerView;
    private GozareshKarViewModel viewModel;
    private View loadingView;
    private MaterialButton sendInfo;
    private View showEmpty ;
    private CheckBox checkBox;
    public static int personId =92;
    public static int personIdHelp =92;
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
//        searchItem();
    }



    private void init() {
        back = findViewById(R.id.IV_back_item_pishkhan);
        fromDate =findViewById(R.id.TV_fromDate);
        toDate = findViewById(R.id.Tv_toDate);
        toolbar = findViewById(R.id.toolbar_item_pishkhan);
        toolbar.setTitle(getResources().getString(R.string.gozareshKar));
        setSupportActionBar(toolbar);
        loadingView = findViewById(R.id.loading_view12);
        sendInfo = findViewById(R.id.Mbtn_gozareshKar_sendInfo);
        checkBox = findViewById(R.id.checkbox_gozareshKar);
        searchableSpinner = findViewById(R.id.spinner_gozareshKar);
        containerSpinnerAndcheckbox = findViewById(R.id.linearLayout3);
        viewModel = new ViewModelProvider(this).get(GozareshKarViewModel.class);



        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer_gozareshkar , new GozareshKarMainFragment(),"gozareshMain").commit();

    }
    private void viewModel() {
        loadingView.setVisibility(View.VISIBLE);
        viewModel.getPersonalList();
        viewModel.personalListLiveData.observe(this,personalListModel -> {
            personIdList.addAll(personalListModel.getData());
            spinner(personalListModel.getData());
            loadingView.setVisibility(View.GONE);
        });
    }



    private void spinner(List<DataItem> list){
        spinnerAdapter = new SpinnerAdapter(this,list);
        searchableSpinner.setAdapter(spinnerAdapter);
        searchableSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(View view, int position, long id) {
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


            }

            @Override
            public void spinnerIsClosing() {
                searchableSpinner.hideEdit();
            }
        });




    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       switch (item.getItemId()){

           case R.id.backss:
                   if (getSupportFragmentManager().findFragmentByTag("detailGozareshKar") != null){
                       getSupportFragmentManager().popBackStack();
                   }else{
                       finish();
                   }
                   personIdHelp = 92;
                   personId = 92;
               return true;


       }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_gozaresh_kar,menu);
        final MenuItem searchItem = menu.findItem(R.id.searchss);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchTextLiveData.setValue(newText);
                return true;
            }
        });
        return true;
    }





    private void setDate(){
        Utils.setDate(fromDate , toDate , this);
    }

    private void event() {


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    isCheck=true;
                    searchableSpinner.setClickable(false);
                    searchableSpinner.setVisibility(View.GONE);
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