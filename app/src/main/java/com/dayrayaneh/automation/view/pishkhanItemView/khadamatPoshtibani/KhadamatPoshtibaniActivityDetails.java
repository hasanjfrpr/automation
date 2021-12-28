package com.dayrayaneh.automation.view.pishkhanItemView.khadamatPoshtibani;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.adapter.pishkhan.khadamatPoshtibani.detail.KhadamatPoshtibaniDetailAdapter;
import com.dayrayaneh.automation.base.BaseActivity;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.dialog.VoiceDialog;
import com.dayrayaneh.automation.model.pishkhan.khadamatPoshtibani.detail.KhadamatPoshtibaniDetailModel;
import com.dayrayaneh.automation.viewModel.pishkhan.khadamatPoshtibani.KhadamatPoshtibaniViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class KhadamatPoshtibaniActivityDetails extends BaseActivity implements KhadamatPoshtibaniDetailAdapter.Events {

    private int userId;
    private KhadamatPoshtibaniViewModel thisViewModel;
    private RecyclerView rv_detail;
    private KhadamatPoshtibaniDetailAdapter adapter;
    private ImageView back;
    private TextView startDate , endDate;
    private View loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khadamat_poshtibani_details);
        init();
        event();
        viewModel();
    }



    private void init() {
        rv_detail = findViewById(R.id.RV_pishkhan_khadamatPoshtibani_detail);
        thisViewModel = new ViewModelProvider(this).get(KhadamatPoshtibaniViewModel.class);
        startDate = findViewById(R.id.TV_showStartDate_khadamatPoshtibani_detail);
        endDate = findViewById(R.id.TV_showEndDate_khadamatPoshtibani_detail);
        back = findViewById(R.id.IV_back_detail_khadamatPoshtibani);
        loadingView = findViewById(R.id.loadinView_ss);


    }

    private void event(){
        back.setOnClickListener(v -> {
           finish();
        });

        startDate.setText(" از "+ ConstValue.startDatePersian);
        endDate.setText(" تا  "+ConstValue.endDatePersian);
    }

    private void viewModel(){

        Bundle bundle = getIntent().getExtras();
        userId = bundle.getInt("userIdKhadamat");
       loadingView.setVisibility(View.VISIBLE);
        thisViewModel.getKhadamatPoshtibaniDetail(ConstValue.startDate , ConstValue.endDate , ConstValue.startTime , ConstValue.endTime , userId);
        thisViewModel.khadamatPoshtibaniDetaileLiveData.observe(this , khadamatPoshtibaniDetailModel -> {
          loadingView.setVisibility(View.GONE);
            setRecyclerView(khadamatPoshtibaniDetailModel);
        });
    }

    private void setRecyclerView(KhadamatPoshtibaniDetailModel khadamatPoshtibaniDetailModel){
        adapter = new KhadamatPoshtibaniDetailAdapter(this , khadamatPoshtibaniDetailModel.getData());
        adapter.events = this;
        rv_detail.setAdapter(adapter);
        rv_detail.setLayoutManager(new LinearLayoutManager(this , RecyclerView.VERTICAL , false));
    }

    @Override
    public void onclickVoicePlay(String uniqueId) {


        ConstValue.uniqueIdVoice = uniqueId;
        VoiceDialog voiceDialog = new VoiceDialog();
        voiceDialog.setCancelable(false);
        String urls = "http://"+ConstValue.ip_voice+":"+ConstValue.port_voice+"/callreport/getaudio_auto.php?uniq="+uniqueId;
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = cm.getActiveNetworkInfo();
                if (netInfo != null && netInfo.isConnected()) {
                    try {
                        URL url = new URL(urls);
                        HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                        urlc.setConnectTimeout(3000);          // 10 s.
                        urlc.connect();
                        if (urlc.getResponseCode() == 200) {        // 200 = "OK" code (http connection is fine).
                            voiceDialog.show(getSupportFragmentManager() , ""  );
                        } else {
                            Snackbar.make(findViewById(R.id.TV_khadamatPoshtibani_tedadKolKhadamat) , getResources().getString(R.string.ipErrorVoice),Snackbar.LENGTH_LONG).show();
                        }
                    } catch (MalformedURLException e1) {
                        Snackbar.make(findViewById(R.id.TV_khadamatPoshtibani_tedadKolKhadamat) , getResources().getString(R.string.ipErrorVoice),Snackbar.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Snackbar.make(findViewById(R.id.TV_khadamatPoshtibani_tedadKolKhadamat) , getResources().getString(R.string.ipErrorVoice),Snackbar.LENGTH_LONG).show();
                    }
                }
                return null;

            }
        }.execute();
    }
}