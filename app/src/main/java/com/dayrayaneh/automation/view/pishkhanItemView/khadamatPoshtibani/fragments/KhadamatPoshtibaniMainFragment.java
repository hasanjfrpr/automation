package com.dayrayaneh.automation.view.pishkhanItemView.khadamatPoshtibani.fragments;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.airbnb.lottie.L;
import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.adapter.pishkhan.khadamatPoshtibani.main.KhadamatPoshtibaniMainAdapter;
import com.dayrayaneh.automation.base.BaseFragment;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.model.pishkhan.khadamatPoshtibani.mian.KhadamatPoshtibaniMainModel;
import com.dayrayaneh.automation.viewModel.pishkhan.khadamatPoshtibani.KhadamatPoshtibaniViewModel;

import java.util.Objects;


public class KhadamatPoshtibaniMainFragment extends BaseFragment implements KhadamatPoshtibaniMainAdapter.ClickItemEvent {

    private RecyclerView rv_main;
    private KhadamatPoshtibaniViewModel thisViewModel;
    private KhadamatPoshtibaniMainAdapter adapter;
    public static MutableLiveData<Boolean> showLoadingLiveData = new MutableLiveData<>()  ;
    private View showEmpty;
    private LinearLayout row;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        return inflater.inflate(R.layout.fragment_khadamat_poshtibani_main, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
        viewModel();

    }

    private void init(View view) {
        rv_main = view.findViewById(R.id.RV_pishKhan_khadamat_poshtibani_main);
        thisViewModel = new ViewModelProvider(this).get(KhadamatPoshtibaniViewModel.class);
        row = view.findViewById(R.id.linearLayout_main_khadamatPoshtibani);
        showEmpty = view.findViewById(R.id.showEmpty);

    }


    public void viewModel(){
        showLoadingLiveData.setValue(true);

        thisViewModel.getKhadamatPoshtibaniMain(ConstValue.startDate , ConstValue.endDate , ConstValue.startTime , ConstValue.endTime ,ConstValue.companyId);
        thisViewModel.khadamatPoshtibaniMainLiveData.observe(getViewLifecycleOwner() , khadamatPoshtibaniMainModel -> {
           if (khadamatPoshtibaniMainModel.getData().size() < 1){
               showEmpty.setVisibility(View.VISIBLE);
               showLoadingLiveData.setValue(false);
           }else {
               showEmpty.setVisibility(View.GONE);
               setRecycler(khadamatPoshtibaniMainModel);
               showLoadingLiveData.setValue(false);
           }

        });
    }

    private void setRecycler(KhadamatPoshtibaniMainModel model){
        adapter = new KhadamatPoshtibaniMainAdapter(getContext() , model.getData());
        adapter.event = this;
        rv_main.setAdapter(adapter);
        rv_main.setLayoutManager(new LinearLayoutManager(getContext() , RecyclerView.VERTICAL, false));

    }




    @Override
    public void onclickRecyclerItem(int userId) {
       getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("addMain").replace(R.id.frameLayout_khadamatPoshtibani , new KhadamatPoshtibaniDetailFragment(userId),"khadamatDetial").commit();


    }
}