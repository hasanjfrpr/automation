package com.dayrayaneh.automation.view.pishkhanItemView.bazaryabi.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.adapter.pishkhan.pishkhan_bazaryabi.BazaryabiMainAdapter;
import com.dayrayaneh.automation.base.BaseFragment;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.base.Keys;
import com.dayrayaneh.automation.model.pishkhan.pishkhan_bazaryabi.count.BazaryabiMainModel;
import com.dayrayaneh.automation.model.pishkhan.pishkhan_bazaryabi.count.DataItem;
import com.dayrayaneh.automation.viewModel.pishkhan.bazaryabi.BazaryabiViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class BazaryabiMainListFragment extends BaseFragment implements BazaryabiMainAdapter.BazarabiMainEvent{


    private RecyclerView recyclerView;
    private BazaryabiMainAdapter adapter;
    private BazaryabiViewModel bazaryabiViewModel ;
    private int companyId;

    public BazaryabiMainListFragment(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_bazaryabi_main_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        viewModel();
    }


    private void init(View view){
        recyclerView = view.findViewById(R.id.RV_bazaryabi_main_list);
        bazaryabiViewModel = new ViewModelProvider(this).get(BazaryabiViewModel.class);

    }




    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setRecyclerView(BazaryabiMainModel bazaryabiMainModel){
        adapter = new BazaryabiMainAdapter(getContext() , bazaryabiMainModel.getData());
        adapter.event=this;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext() , RecyclerView.VERTICAL , false));
    }

    private void viewModel(){
        bazaryabiViewModel.getBazarYabiMainCount(ConstValue.startDate , ConstValue.endDate ,companyId );
        bazaryabiViewModel.bazaryabiMainLiveData.observe(this,bazaryabiMainModel -> {
            setRecyclerView(bazaryabiMainModel);
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onclickItemMainBazaryabi(int personelCode , String personalName) {



        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("bazaryabiMain")
                .replace(R.id.FrameLayout_bazaryabi,new BazaryabiDetailListFragment(personelCode , personalName),"bazaryabiDetailFragment")
                .commit();

    }


}