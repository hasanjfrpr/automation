package com.dayrayaneh.automation.view.pishkhanItemView.VoicePoshtibani;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
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
import com.google.android.material.snackbar.Snackbar;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class VoicePoshtibaniActivity extends BaseActivity implements VoiceAdapter.Events,SearchVoiceDialog.VoiceDialogEvent {


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
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
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
            d.event = this;
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

    private void viewModelFilter(String serial , String mobile){
        loading.setVisibility(View.VISIBLE);
        thisViewModel.getVoice(ConstValue.startDate , ConstValue.endDate , serial , mobile);
        thisViewModel.voiceLiveData.observe(this,voiceModel -> {
            loading.setVisibility(View.GONE);
            setupRecyclerView(voiceModel);
        });
    }

    @Override
    public void itemEvents(String uniqueId) {
//        ConstValue.uniqueIdVoice = uniqueId;
//        VoiceDialog voiceDialog = new VoiceDialog();
//        voiceDialog.setCancelable(false);
//        String urls = "http://"+ConstValue.ip_voice+":"+ConstValue.port_voice+"/callreport/getaudio_auto.php?uniq="+uniqueId;
//        if (Utils.isURLReachable(this ,urls )){
//            voiceDialog.show(getSupportFragmentManager() , ""  );
//        }else{
//            Snackbar.make(fab , getResources().getString(R.string.ipErrorVoice),Snackbar.LENGTH_LONG).show();
//        }
        ConstValue.uniqueIdVoice = uniqueId;
        VoiceDialog voiceDialog = new VoiceDialog();
        voiceDialog.setCancelable(false);
        String urls = "http://"+ConstValue.ip_voice+":"+ConstValue.port_voice+"/callreport/getaudio_auto.php?uniq="+uniqueId;
//        if (Utils.isURLReachable(Objects.requireNonNull(getContext()),urls )){
//            voiceDialog.show(getActivity().getSupportFragmentManager() , ""  );
//        }else{
//            Snackbar.make(getActivity().findViewById(R.id.TV_khadamatPoshtibani_tedadKolKhadamat) , getResources().getString(R.string.ipErrorVoice),Snackbar.LENGTH_LONG).show();
//        }
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
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
                            Snackbar.make(findViewById(R.id.fab_voice) , getResources().getString(R.string.ipErrorVoice),Snackbar.LENGTH_LONG).show();
                        }
                    } catch (MalformedURLException e1) {
                        Snackbar.make(findViewById(R.id.fab_voice) , getResources().getString(R.string.ipErrorVoice),Snackbar.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Snackbar.make(findViewById(R.id.fab_voice) , getResources().getString(R.string.ipErrorVoice),Snackbar.LENGTH_LONG).show();
                    }
                }
                return null;

            }
        }.execute();

    }

    @Override
    public void sendInfoClick(String serial, String mobile) {
        viewModelFilter(serial , mobile);
    }
}