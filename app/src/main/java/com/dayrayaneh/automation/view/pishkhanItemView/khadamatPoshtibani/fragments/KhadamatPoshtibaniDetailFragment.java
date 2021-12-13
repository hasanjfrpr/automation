package com.dayrayaneh.automation.view.pishkhanItemView.khadamatPoshtibani.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
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
import com.dayrayaneh.automation.view.pishkhanItemView.khadamatPoshtibani.KhadamatPoshtibaniActivity;
import com.dayrayaneh.automation.viewModel.pishkhan.khadamatPoshtibani.KhadamatPoshtibaniViewModel;

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

    @Override
    public void onStop() {
        hide.setValue(false);
        super.onStop();
    }

    @Override
    public void onclickVoicePlay(String uniqueId) {
        VoiceDialog voiceDialog = new VoiceDialog() ;
        voiceDialog.show(getActivity().getSupportFragmentManager() , "voiceDialog");
        voiceDialog.setCancelable(false);
       if (getActivity().getSupportFragmentManager().findFragmentByTag("voiceDialog") != null){
           voiceDialog.playVoice(uniqueId);
       }else {
           ConstValue.uniqueIdVoice = uniqueId;
       }
    }
}