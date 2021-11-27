package com.dayrayaneh.automation.view.pishkhanItemView.foroshSakhtAfzar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.adapter.pishkhan.forooshNarmAfzar.ForooshNarmAfzarAdapter;
import com.dayrayaneh.automation.adapter.pishkhan.forooshSakhtAzar.ForooshSakhtAfzarAdapter;
import com.dayrayaneh.automation.adapter.pishkhan.forooshSakhtAzar.compare.ForooshSakhtAfzarCompareAdaptrer;
import com.dayrayaneh.automation.adapter.pishkhan.forooshSakhtAzar.productCategories.ProductCategoriesAdapter;
import com.dayrayaneh.automation.base.BaseActivity;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.model.pishkhan.forooshNarmAfzar.ForooshNarmAfzarModel;
import com.dayrayaneh.automation.model.pishkhan.forooshSakhtAfzar.ForooshSakhtAfzarModel;
import com.dayrayaneh.automation.model.pishkhan.forooshSakhtAfzar.compare.ForooshSakhtAfzarCompareModel;
import com.dayrayaneh.automation.model.pishkhan.forooshSakhtAfzar.productCategories.DataItem;
import com.dayrayaneh.automation.model.pishkhan.forooshSakhtAfzar.productCategories.ProductCategories;
import com.dayrayaneh.automation.utils.Utils;
import com.dayrayaneh.automation.viewModel.pishkhan.forooshSakhtAfzar.ForooshSakhtAfzarViewModel;
import com.dayrayaneh.automation.viewModel.pishkhan.foroshNarmAfzar.ForooshNarmAfzarViewModel;
import com.google.android.material.button.MaterialButton;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.List;

import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

public class ForooshSakhtAfzarActivity extends BaseActivity {

    private ImageView back;
    private TextView fromDate, toDate, fromDate_mqayese, toDate_moqayese;
    private Toolbar toolbar;
    private RecyclerView rv_main, rv_moqayese;
    private ForooshSakhtAfzarAdapter adapter;
    private ForooshSakhtAfzarCompareAdaptrer adapter_compare;
    private ForooshSakhtAfzarViewModel viewModel;
    private View loadingView , showEmpty;
    private LinearLayout showCompare;
    private MaterialButton sendInfo;
    private CheckBox checkBox;
    private String fromDateM, toDateM;
    private boolean isCheckedd = false;
    private FrameLayout entekhabModelMahsol;

    private MaterialButton okCategories;
    private List<Integer> ids = new ArrayList<>();
    private String[] productName;
    private int[] productID;
    private boolean[] defaultChecked;
    private ProductCategories productCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forosh_sakht_afzar);

        init();
        setDate();
        event();
        getProductCategoriesViewModel();
//        viewModel();


    }

    private void init() {
        back = findViewById(R.id.IV_back_item_pishkhan);
        toolbar = findViewById(R.id.toolbar_item_pishkhan);
        toolbar.setTitle(getResources().getString(R.string.hardware));
        setSupportActionBar(toolbar);
        fromDate = findViewById(R.id.TV_fromDate);
        toDate = findViewById(R.id.Tv_toDate);
        checkBox = findViewById(R.id.checkbox_pishkan_foroshSakhtAfzar);
        showCompare = findViewById(R.id.LinLayout_pishkhan_forooshSakhtAfzar_moqayese);
        rv_main = findViewById(R.id.RV_pishkhan_forooshSakhtAfzar_main);
        rv_moqayese = findViewById(R.id.RV_pishkhan_forooshSakhtAfzar_moqayese);
        loadingView = findViewById(R.id.loading_view);
        sendInfo = findViewById(R.id.Mbtn_pishkhan_foroosh_sakhtAfzar_sendInfo);
        fromDate_mqayese = findViewById(R.id.TV_fromDate_moqayese);
        toDate_moqayese = findViewById(R.id.Tv_toDate_moqayese);
        entekhabModelMahsol = findViewById(R.id.frameLayout_pishkhan_forooshSakhtAfzar_entekhab_modelMahsool);
        okCategories = findViewById(R.id.Mbtn_ok_select_ProductCategories);
        showEmpty = findViewById(R.id.showEmpty);


        viewModel = new ViewModelProvider(this).get(ForooshSakhtAfzarViewModel.class);

    }

    private void event() {
        back.setOnClickListener(v -> {
            finish();
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    showCompare.setVisibility(View.VISIBLE);
                    setCompareDate();
                    isCheckedd = true;
                } else {
                    showCompare.setVisibility(View.GONE);
                    isCheckedd = false;
                }

            }
        });

        sendInfo.setOnClickListener(v -> {

               if (isCheckedd){
                   viewModelCompare();
               }else {
                   viewModel();
               }

        });


        entekhabModelMahsol.setOnClickListener(v -> {
            setDialogForProductCategories(productCategories);

        });



    }

    private void setDate() {
        Utils.setDate(fromDate, toDate, this);
    }

    private void viewModel() {
        loadingView.setVisibility(View.VISIBLE);
        viewModel.getForooshSakhtAfzar(ConstValue.startDate, ConstValue.endDate, ids);
        viewModel.forooshSakhtAfzarLiveData.observe(this, forooshSakhtAfzarModel -> {
            if (forooshSakhtAfzarModel.getData().size() < 1){
                showEmpty.setVisibility(View.VISIBLE);
                loadingView.setVisibility(View.GONE);
            }else {
                showEmpty.setVisibility(View.GONE);
                loadingView.setVisibility(View.GONE);
                setRecyclerViewMain(forooshSakhtAfzarModel);
            }
        });

    }

    private void viewModelCompare(){

        loadingView.setVisibility(View.VISIBLE);
        viewModel.getForooshSakhtAfzarCompare(ConstValue.startDate, ConstValue.endDate, fromDateM ,toDateM , ids);
        viewModel.forooshSakhtAfzarCompareLiveData.observe(this, forooshSakhtAfzarCompareModel -> {
            loadingView.setVisibility(View.GONE);
            setupRecyclerViewCompare(forooshSakhtAfzarCompareModel);
        });
    }

    private void setRecyclerViewMain(ForooshSakhtAfzarModel forooshSakhtAfzarModel) {

        rv_moqayese.setVisibility(View.GONE);
        rv_main.setVisibility(View.VISIBLE);
        adapter = new ForooshSakhtAfzarAdapter(this, forooshSakhtAfzarModel.getData());
        rv_main.setVisibility(View.VISIBLE);
        rv_main.setAdapter(adapter);
        rv_main.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

    }

  private void setupRecyclerViewCompare(ForooshSakhtAfzarCompareModel forooshSakhtAfzarCompareModel){
        rv_main.setVisibility(View.GONE);
        rv_moqayese.setVisibility(View.VISIBLE);
        adapter_compare = new ForooshSakhtAfzarCompareAdaptrer(this,forooshSakhtAfzarCompareModel.getData());
        rv_moqayese.setAdapter(adapter_compare);
        rv_moqayese.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL ,false));
  }

    private void setCompareDate() {

        PersianDate mDate = new PersianDate();
        PersianDateFormat format = new PersianDateFormat("Y/m/d");
        String currentDate = format.format(mDate);

        fromDate_mqayese.setText(currentDate);
        toDate_moqayese.setText(currentDate);
        fromDateM = Utils.convertPersianDateToFormatOfServer(mDate.getShYear(), mDate.getShMonth(), mDate.getShDay());
        toDateM = Utils.convertPersianDateToFormatOfServer(mDate.getShYear(), mDate.getShMonth(), mDate.getShDay());


        com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog datePickerDialog = new com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog();
        fromDate_mqayese.setOnClickListener(v -> {
            datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                    fromDate_mqayese.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                    fromDateM = Utils.convertPersianDateToFormatOfServer(year, (monthOfYear + 1), dayOfMonth);
                }
            });
            datePickerDialog.setThemeDark(true);
            datePickerDialog.show(getFragmentManager(), "");

        });

        toDate_moqayese.setOnClickListener(v -> {
            datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                    toDate_moqayese.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                    toDateM = Utils.convertPersianDateToFormatOfServer(year, (monthOfYear + 1), dayOfMonth);
                }
            });
            datePickerDialog.setThemeDark(true);
            datePickerDialog.show(getFragmentManager(), "");


        });


    }

    private void getProductCategoriesViewModel() {
        viewModel.getProductCategories();
        viewModel.productCategoriesLiveData.observe(this, productCategories -> {
//            setDialogForProductCategories(productCategories.getData());
            productName = new String[productCategories.getData().size()];
            productID = new int[productCategories.getData().size()];
            defaultChecked = new boolean[productCategories.getData().size()];
            this.productCategories = productCategories;

            for (int i = 0; i < productCategories.getData().size(); i++) {
                ids.add(productCategories.getData().get(i).getId());
                productName[i] = productCategories.getData().get(i).getName();
                productID[i] = productCategories.getData().get(i).getId();

            }
            loadingView.setVisibility(View.VISIBLE);
            viewModel.getForooshSakhtAfzar(ConstValue.startDate, ConstValue.endDate, ids);
            viewModel.forooshSakhtAfzarLiveData.observe(this, forooshSakhtAfzarModel -> {
                loadingView.setVisibility(View.GONE);
                setRecyclerViewMain(forooshSakhtAfzarModel);
            });

        });
    }



    private void setDialogForProductCategories(ProductCategories productCategories){

//        String[] productName = new String[productCategories.size()];
//
//
//        for (int i = 0; i < productCategories.size(); i++) {
//            ids.add(productCategories.get(i).getId());
//        }
//
//        for (int i = 0; i < productCategories.size(); i++) {
//            productName[i] = productCategories.get(i).getName();
//        }
//
//        boolean[] defaultCheckList = new boolean[productCategories.size()];


        for (int i = 0; i < ids.size(); i++) {
            if (ids.get(i) !=null){
                defaultChecked[i] = true;
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMultiChoiceItems(productName, defaultChecked, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                if (isChecked && (ids.get(which) == null || ids.get(which) == 0)){
                    ids.add(productCategories.getData().get(which).getId());
                }else {
                    ids.remove(which);
                }

            }
        });
        builder.setPositiveButton(getResources().getString(R.string.mdtp_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (isCheckedd){
                    viewModelCompare();
                }else {
                    viewModel();
                }

            }
        });
        builder.create().show();


    }



}