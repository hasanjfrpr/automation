package com.dayrayaneh.automation.view.pishkhanItemView.gozareshKar.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.adapter.pishkhan.gozareshKar.detail.GozareshKarDetailsAdapter;
import com.dayrayaneh.automation.base.BaseFragment;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.model.pishkhan.Gozareshkar.details.GozareshKarDetailsModel;
import com.dayrayaneh.automation.viewModel.pishkhan.GozareshKarha.GozareshKarViewModel;
import com.dayrayaneh.automation.viewModel.pishkhan.GozareshKarha.repo.GozareshKarRepo;

public class GozareshkarDetailFragment extends BaseFragment {

    private int userCode;
    private View emptyLayout;
    private RecyclerView recyclerView;
    private GozareshKarViewModel viewModel;
    private GozareshKarDetailsAdapter adapter;

    public GozareshkarDetailFragment(int userCode) {
        this.userCode = userCode;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_gozareshkar_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
        viewModel();
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.RV_gozareshKar_details);
        emptyLayout = view.findViewById(R.id.showEmpty);
        viewModel = new ViewModelProvider(this).get(GozareshKarViewModel.class);

    }

    private void viewModel(){
        viewModel.getGozareshKarDetial(ConstValue.startDate , ConstValue.endDate , "" , userCode);
        viewModel.gozareshKarDetailLiveData.observe(this , gozareshKarDetailsModel -> {
            setupRecycler(gozareshKarDetailsModel);
        });
    }

    private void setupRecycler(GozareshKarDetailsModel gozareshKarDetailsModel) {
        adapter = new GozareshKarDetailsAdapter(getContext() , gozareshKarDetailsModel.getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext() , RecyclerView.VERTICAL , false));
    }
}