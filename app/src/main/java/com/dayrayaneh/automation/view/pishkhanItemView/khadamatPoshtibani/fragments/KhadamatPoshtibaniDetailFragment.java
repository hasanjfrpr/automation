package com.dayrayaneh.automation.view.pishkhanItemView.khadamatPoshtibani.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.adapter.pishkhan.khadamatPoshtibani.detail.KhadamatPoshtibaniDetailAdapter;
import com.dayrayaneh.automation.adapter.pishkhan.khadamatPoshtibani.main.KhadamatPoshtibaniMainAdapter;
import com.dayrayaneh.automation.base.BaseFragment;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.dialog.VoiceDialog;
import com.dayrayaneh.automation.model.pishkhan.khadamatPoshtibani.detail.KhadamatPoshtibaniDetailModel;
import com.dayrayaneh.automation.utils.Utils;
import com.dayrayaneh.automation.view.pishkhanItemView.khadamatPoshtibani.KhadamatPoshtibaniActivity;
import com.dayrayaneh.automation.viewModel.pishkhan.khadamatPoshtibani.KhadamatPoshtibaniViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class KhadamatPoshtibaniDetailFragment extends BaseFragment implements KhadamatPoshtibaniDetailAdapter.Events {


    private int userId;
    private KhadamatPoshtibaniViewModel thisViewModel;
    private RecyclerView rv_detail;
    public static MutableLiveData<Boolean> isShowLoadin = new MutableLiveData<>();
    public static MutableLiveData<Boolean> hide = new MutableLiveData<>();
    private KhadamatPoshtibaniDetailAdapter adapter;
    private ImageView back;
    private TextView startDate , endDate;

    public KhadamatPoshtibaniDetailFragment(int userId) {
        this.userId = userId;
    }
    public KhadamatPoshtibaniDetailFragment() {

    }

    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setRequestedOrientation(Configuration.ORIENTATION_PORTRAIT);
        return inflater.inflate(R.layout.fragment_khadamat_poshtibani_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
        viewModel();
        event();
    }

    private void init(View view) {
        rv_detail = view.findViewById(R.id.RV_pishkhan_khadamatPoshtibani_detail);
        thisViewModel = new ViewModelProvider(this).get(KhadamatPoshtibaniViewModel.class);
        startDate = view.findViewById(R.id.TV_showStartDate_khadamatPoshtibani_detail);
        endDate = view.findViewById(R.id.TV_showEndDate_khadamatPoshtibani_detail);
        back = view.findViewById(R.id.IV_back_detail_khadamatPoshtibani);

    }

    private void event(){
        back.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().popBackStack();
        });

        startDate.setText(" از "+ConstValue.startDatePersian);
        endDate.setText(" تا  "+ConstValue.endDatePersian);
    }

    private void viewModel(){
        isShowLoadin.setValue(true);
        thisViewModel.getKhadamatPoshtibaniDetail(ConstValue.startDate , ConstValue.endDate , ConstValue.startTime , ConstValue.endTime , userId);
        thisViewModel.khadamatPoshtibaniDetaileLiveData.observe(this , khadamatPoshtibaniDetailModel -> {
            isShowLoadin.setValue(false);
            setRecyclerView(khadamatPoshtibaniDetailModel);
        });
    }

    private void setRecyclerView(KhadamatPoshtibaniDetailModel khadamatPoshtibaniDetailModel){
        adapter = new KhadamatPoshtibaniDetailAdapter(getContext() , khadamatPoshtibaniDetailModel.getData());
        adapter.events = this;
        rv_detail.setAdapter(adapter);
        rv_detail.setLayoutManager(new LinearLayoutManager(getContext() , RecyclerView.VERTICAL , false));
    }

    @Override
    public void onStart() {
        super.onStart();
        hide.setValue(true);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onStop() {
        hide.setValue(false);
        super.onStop();
    }

    @Override
    public void onclickVoicePlay(String uniqueId) {

//            ConstValue.uniqueIdVoice  = uniqueId;
//            VoiceDialog voiceDialog = new VoiceDialog() ;
//            voiceDialog.show(getActivity().getSupportFragmentManager() , "voiceDialog");
//            voiceDialog.setCancelable(false);

//
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
                ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = cm.getActiveNetworkInfo();
                if (netInfo != null && netInfo.isConnected()) {
                    try {
                        URL url = new URL(urls);
                        HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                        urlc.setConnectTimeout(3000);          // 10 s.
                        urlc.connect();
                        if (urlc.getResponseCode() == 200) {        // 200 = "OK" code (http connection is fine).
                            voiceDialog.show(getActivity().getSupportFragmentManager() , ""  );
                        } else {
                            Snackbar.make(getActivity().findViewById(R.id.TV_khadamatPoshtibani_tedadKolKhadamat) , getResources().getString(R.string.ipErrorVoice),Snackbar.LENGTH_LONG).show();
                        }
                    } catch (MalformedURLException e1) {
                        Snackbar.make(getActivity().findViewById(R.id.TV_khadamatPoshtibani_tedadKolKhadamat) , getResources().getString(R.string.ipErrorVoice),Snackbar.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Snackbar.make(getActivity().findViewById(R.id.TV_khadamatPoshtibani_tedadKolKhadamat) , getResources().getString(R.string.ipErrorVoice),Snackbar.LENGTH_LONG).show();
                    }
                }
                return null;

            }
        }.execute();
    }
}