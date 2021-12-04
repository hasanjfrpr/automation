package com.dayrayaneh.automation.view.pishkhanItemView.gozareshKar.Fragment;

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

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.adapter.pishkhan.gozareshKar.main.GozareshKarMainAdapter;
import com.dayrayaneh.automation.base.BaseFragment;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.model.pishkhan.Gozareshkar.count.GozareshKarCountModel;
import com.dayrayaneh.automation.view.pishkhanItemView.gozareshKar.GozareshKarActivity;
import com.dayrayaneh.automation.viewModel.pishkhan.GozareshKarha.GozareshKarViewModel;

public class GozareshKarMainFragment extends BaseFragment implements GozareshKarMainAdapter.ClickItemEvent {

    private GozareshKarViewModel viewModel;
    private RecyclerView recyclerView;
    private View emptyShow;
    private GozareshKarMainAdapter adapter;
    public static MutableLiveData<Boolean> showLoading = new MutableLiveData<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gozaresh_kar_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        viewModel();
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.RV_gozareshKar_main);
        emptyShow = view.findViewById(R.id.showEmpty);
        viewModel = new ViewModelProvider(this).get(GozareshKarViewModel.class);
    }

    public void viewModel(){
        showLoading.setValue(true);
        viewModel.getGozareshkarCount(ConstValue.startDate , ConstValue.endDate , GozareshKarActivity.isCheck ? GozareshKarActivity.personId :GozareshKarActivity.personIdHelp);
        viewModel.gozareshKarCountLiveData.observe(this , countModel -> {
            if(countModel.getData().size() <1){
                emptyShow.setVisibility(View.VISIBLE);
            }else{
                emptyShow.setVisibility(View.GONE);
                setupRecyclerView(countModel);
            }
            showLoading.setValue(false);

        });
    }

    private void setupRecyclerView(GozareshKarCountModel countModel){
        adapter = new GozareshKarMainAdapter(getContext() , countModel.getData());
        adapter.event = this;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext() , RecyclerView.VERTICAL , false));
    }

    @Override
    public void onClickItem(int userCode) {
        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frameContainer_gozareshkar , new GozareshkarDetailFragment(userCode),"detailGozareshKar").commit();
    }


}