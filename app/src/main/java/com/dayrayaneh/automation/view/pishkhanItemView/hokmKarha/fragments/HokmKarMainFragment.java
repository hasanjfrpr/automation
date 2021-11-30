package com.dayrayaneh.automation.view.pishkhanItemView.hokmKarha.fragments;

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

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.adapter.pishkhan.hokmKar.HokmKarMainAdapter;
import com.dayrayaneh.automation.base.BaseFragment;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.model.pishkhan.HokmKar.HokmKarModel;
import com.dayrayaneh.automation.view.pishkhanItemView.hokmKarha.HokmKarhaActivity;
import com.dayrayaneh.automation.viewModel.pishkhan.HokmKar.HokmKarViewModel;

public class HokmKarMainFragment extends BaseFragment implements HokmKarMainAdapter.HokmKarMainEvent {


    private HokmKarViewModel thisViewModel;
    private RecyclerView recyclerView;
    private HokmKarMainAdapter adapter_main;
    private View loadingView;
    public  MutableLiveData<Boolean> loadinLiveData = new MutableLiveData();
    private View showEmpty;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_hokm_kar_main, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        setViewModel();
    }

    private void init(View view) {

        thisViewModel = new ViewModelProvider(this).get(HokmKarViewModel.class);
        recyclerView = view.findViewById(R.id.RV_hokmKar_main);
        loadingView = view.findViewById(R.id.loading_view);
        showEmpty = view.findViewById(R.id.showEmpty);

    }

    public void setViewModel(){
        loadinLiveData.setValue(true);
        thisViewModel.getHokmKar(ConstValue.startDate , ConstValue.endDate);
        thisViewModel.hokmKarLiveData.observe(getViewLifecycleOwner(), hokmKarModel -> {
            if (hokmKarModel.getData().size() < 1){
                showEmpty.setVisibility(View.VISIBLE);
                loadinLiveData.setValue(false);
                recyclerView.setVisibility(View.GONE);
            }else {
                showEmpty.setVisibility(View.GONE);
                setupRecyclerView(hokmKarModel);
                loadinLiveData.setValue(false);
            }
        });
    }


    private void setupRecyclerView(HokmKarModel hokmKarModel){
        adapter_main = new HokmKarMainAdapter(getContext() , hokmKarModel.getData());
        adapter_main.event = this;
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(adapter_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext() , RecyclerView.VERTICAL , false));
    }


    @Override
    public void getHokmKarMainId(int userId) {

        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("mainFragment").replace(R.id.frameLayout_hokmKar , new HokmKarDetailFragment(userId) , "hokmKarDetail").commit();
    }
}