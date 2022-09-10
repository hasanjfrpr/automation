package com.dayrayaneh.automation.view.pishkhanItemView.bazaryabi.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.adapter.pishkhan.pishkhan_bazaryabi.BazaryabiDetailAdapter;
import com.dayrayaneh.automation.base.BaseFragment;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.viewModel.pishkhan.bazaryabi.BazaryabiViewModel;

public class BazaryabiDetailListFragment extends BaseFragment implements BazaryabiDetailAdapter.EventClickPygiri {

    private RecyclerView recyclerView;
    private ImageView btn_back_to_main;
    private BazaryabiViewModel bazaryabiViewModel;
    private BazaryabiDetailAdapter adapter;
    private SharedPreferences sharedPreferences;
    private BazaryabiMainListFragment bazaryabiMainListFragment;
    private TextView title;
    private int id;
    private String personalName;
    public static MutableLiveData<Boolean> showEmpty = new MutableLiveData<>();

    public BazaryabiDetailListFragment(int id , String personalName) {
        this.id = id;
        this.personalName = personalName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bazaryabi_detail_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        setupRecyclerView();
        setTitleAndPersonalNameInView();
    }


    private void init(View view) {
        recyclerView = view.findViewById(R.id.RV_bazaryabi_detail_list);
        title  = view.findViewById(R.id.TV_bazaryabi_detail_title);
        bazaryabiViewModel = new ViewModelProvider(this).get(BazaryabiViewModel.class);
        sharedPreferences = getContext().getSharedPreferences("date", Context.MODE_PRIVATE);
        adapter = new BazaryabiDetailAdapter();
        adapter.eventClickPygiri = this;

    }
    private void setTitleAndPersonalNameInView(){
        title.setText(getResources().getString(R.string.joziatBazaryabi)+" "+ personalName);
    }



    private void setupRecyclerView() {


        showEmpty.setValue(true);
        int personelCode = id;
        bazaryabiViewModel.getBazarYabiDetail(ConstValue.startDate, ConstValue.endDate, personelCode);
        bazaryabiViewModel.bazaryabiDetailLiveData.observe(this, bazaryabiDetailModel -> {
            showEmpty.setValue(false);
            adapter = new BazaryabiDetailAdapter(getContext(), bazaryabiDetailModel.getData());
            adapter.eventClickPygiri = this;
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
            recyclerView.setAdapter(adapter);


        });
    }

    @Override
    public void onStop() {
        showEmpty.setValue(false);
        super.onStop();
    }

    @Override
    public void onClick(int id) {
        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("s")
                .hide(this)
                .add(R.id.FrameLayout_bazaryabi,new PaygiriFragment(id),"paygiriFragment")
                .commit();
    }
}