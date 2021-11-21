package com.dayrayaneh.automation.view.pishkhanItemView.hokmKarha.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
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
import com.dayrayaneh.automation.adapter.pishkhan.hokmKar.followers.HokmKarFollowersAdapter;
import com.dayrayaneh.automation.adapter.pishkhan.hokmKar.request.HokmKarRequestAdapter;
import com.dayrayaneh.automation.base.BaseFragment;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.model.pishkhan.HokmKar.followers.HokmKarFollowersModel;
import com.dayrayaneh.automation.model.pishkhan.HokmKar.request.HokmKarRequestModel;
import com.dayrayaneh.automation.viewModel.pishkhan.HokmKar.HokmKarViewModel;

public class HokmKarDetailFragment extends BaseFragment {

    private int userId;
    private HokmKarViewModel thisViewModel;
    private RecyclerView rv_request;
    private View loadingView;
    private HokmKarRequestAdapter requestAdapter;
    private HokmKarFollowersAdapter followersAdapter;
    private RecyclerView rv_followers;
    public static MutableLiveData<Boolean> hideButtonSendIndo = new MutableLiveData<Boolean>();
    private TextView startDate, endDate;
    private ImageView back;


    public HokmKarDetailFragment(int userId) {
        this.userId = userId;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hokm_kar_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
     init(view);
     event();
     viewModel();
    }

    private void init(View view) {
      hideButtonSendIndo.setValue(true);
        thisViewModel = new ViewModelProvider(this).get(HokmKarViewModel.class);
        rv_request = view.findViewById(R.id.RV_hokmKar_request);
        rv_followers = view.findViewById(R.id.RV_hokmKar_followers);
        loadingView = view.findViewById(R.id.loading_view);
        back = view.findViewById(R.id.IV_back_detail_hokmkar);
        startDate = view.findViewById(R.id.TV_showStartDate_hokmKar_detail);
        endDate = view.findViewById(R.id.TV_showEndDate_hokmKar_detail);
    }


    private void event(){
        back.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().popBackStack();
        });
        startDate.setText(" از تاریخ "+ConstValue.startDatePersian);
        endDate.setText( " تا تاریخ "+ConstValue.endDatePersian );
    }


    private void viewModel(){
        ////request
        loadingView.setVisibility(View.VISIBLE);
        thisViewModel.getHokmKarRequest(userId);
        thisViewModel.getHokmKarFollowers(userId);

        thisViewModel.hokmKarRequestLiveData.observe(this,hokmKarRequestModel -> {
            loadingView.setVisibility(View.GONE);
            setRecyclerRequest(hokmKarRequestModel);
        });

        thisViewModel.hokmKarFollowersLiveData.observe(this , hokmKarFollowersModel -> {
            loadingView.setVisibility(View.GONE);
            setRecyclerFollowers(hokmKarFollowersModel);
        });
    }

    private void setRecyclerRequest(HokmKarRequestModel hokmKarRequestModel){
        requestAdapter = new HokmKarRequestAdapter(getContext() , hokmKarRequestModel.getData() );
        rv_request.setAdapter(requestAdapter);
        rv_request.setLayoutManager(new LinearLayoutManager(getContext() , RecyclerView.VERTICAL , false));
    }

    private void setRecyclerFollowers(HokmKarFollowersModel followersModel){
        followersAdapter = new HokmKarFollowersAdapter(getContext() , followersModel.getData() );
        rv_followers.setAdapter(followersAdapter);
        rv_followers.setLayoutManager(new LinearLayoutManager(getContext() , RecyclerView.VERTICAL , false));
    }


    @Override
    public void onStop() {
        hideButtonSendIndo.setValue(false);
        super.onStop();
    }
}