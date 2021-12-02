package com.dayrayaneh.automation.view.pishkhanItemView.tedadHokmKarha.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.adapter.pishkhan.tedadHokmKar.count.TedadHokmKarCountAdapter;
import com.dayrayaneh.automation.base.BaseFragment;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.model.pishkhan.tedadHokmKarha.count.TedadHokmKarCountModel;
import com.dayrayaneh.automation.viewModel.pishkhan.tedadHokmKar.TedadHokmKarViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class TedadHokmKarMainFragment extends BaseFragment implements TedadHokmKarCountAdapter.Event{


    private RecyclerView recyclerView;
    private TedadHokmKarViewModel viewModel;
    private TedadHokmKarCountAdapter adapter;
    public static MutableLiveData<Boolean> mustShowLoading = new MutableLiveData<>();
    private View emptyLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_tedad_hokm_kar_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        viewModel();

    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.RV_tedadHokmkarha_main);
        viewModel = new ViewModelProvider(this).get(TedadHokmKarViewModel.class);
        emptyLayout = view.findViewById(R.id.showEmpty);
    }

    public void  viewModel(){
        mustShowLoading.setValue(true);
        viewModel.getTedadHokmKarhaCount(ConstValue.startDate , ConstValue.endDate);
        viewModel.tedadHokmKarCountLiveData.observe(this,tedadHokmKarCountModel -> {

            if (tedadHokmKarCountModel.getData().size() <1){
                emptyLayout.setVisibility(View.VISIBLE);
                mustShowLoading.setValue(false);
            }else {
                emptyLayout.setVisibility(View.GONE);
                mustShowLoading.setValue(false);
                setRecyclerView(tedadHokmKarCountModel);
            }


        });
    }

    private void setRecyclerView(TedadHokmKarCountModel tedadHokmKarCountModel) {
        adapter = new TedadHokmKarCountAdapter(getContext() , tedadHokmKarCountModel.getData()  );
        adapter.event = this;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext() , RecyclerView.VERTICAL , false));
    }



    @Override
    public void clickEvent(int personalCode) {
        getFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frameContainer_tedadHokmKar , new TedadHokmKarDetailFragment(personalCode),"detailsTedad").commit();
    }

    @Override
    public void onStart() {
        super.onStart();

    }


}