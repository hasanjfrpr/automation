package com.dayrayaneh.automation.view.pishkhanItemView.gozareshKar.Fragment;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
    private RecyclerView.LayoutManager mLayoutManager;
    private Parcelable parcelable;
    public static MutableLiveData<Boolean> showLoading = new MutableLiveData<>();
    private LinearLayout root;
    private Bundle mBundleRecyclerViewState;



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
        root = view.findViewById(R.id.Root_gozareshKarMain);

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

    @SuppressLint("NotifyDataSetChanged")
    private void setupRecyclerView(GozareshKarCountModel countModel){
        adapter = new GozareshKarMainAdapter(getContext() , countModel.getData());
        adapter.event = this;
        recyclerView.setAdapter(adapter);
        mLayoutManager = new LinearLayoutManager(getContext() , RecyclerView.VERTICAL , false);
        recyclerView.setLayoutManager(mLayoutManager);

        GozareshKarActivity.searchTextLiveData.observe(this,text->{
            adapter.getFilter().filter(text);
            adapter.notifyDataSetChanged();
        });
    }


    @Override
    public void onClickItem(int userCode , String name) {
        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("")
                .add(R.id.frameContainer_gozareshkar , new GozareshkarDetailFragment(userCode , name),"detailGozareshKar")
                .hide(this)
                .commit();

    }



}