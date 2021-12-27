package com.dayrayaneh.automation.view.pishkhanItemView.tedadHokmKarha.fragment;

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

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.adapter.pishkhan.tedadHokmKar.count.TedadHokmKarCountAdapter;
import com.dayrayaneh.automation.adapter.pishkhan.tedadHokmKar.details.TedadHokmKarDetailsAdapter;
import com.dayrayaneh.automation.base.BaseFragment;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.model.pishkhan.tedadHokmKarha.details.TedadHokmKarDetailsModel;
import com.dayrayaneh.automation.viewModel.pishkhan.tedadHokmKar.TedadHokmKarViewModel;

public class TedadHokmKarDetailFragment extends BaseFragment {

    private int personalCode;
    private RecyclerView recyclerView;
    private TedadHokmKarViewModel viewModel;
    private TedadHokmKarDetailsAdapter adapter;
    public static MutableLiveData<Boolean> mustHide = new MutableLiveData<>();
    private ImageView back;
    private TextView startDate , endDate , TV_name;
    private View loadingView , emptyLayout;
    private String name;

    public TedadHokmKarDetailFragment(int personalCode ,  String name) {
        this.personalCode = personalCode;
        this.name = name;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tedad_hokm_kar_detail, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mustHide.setValue(true);
        init(view);
        viewModel();
        event();


    }

    private void event() {
        back.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().popBackStack();
        });

        startDate.setText("از "+ConstValue.startDatePersian);
        endDate.setText("تا "+ConstValue.endDatePersian);
        TV_name.setText(name);
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.RV_tedadHokmkarha_details);
        back = view.findViewById(R.id.IV_back_detail_tedadHokmKar);
        startDate= view.findViewById(R.id.TV_showStartDate_tedadHokmKar_detail);
        endDate = view.findViewById(R.id.TV_showEndDate_tedadHokmKar_detail);
        viewModel = new ViewModelProvider(this).get(TedadHokmKarViewModel.class);
        loadingView = view.findViewById(R.id.loading_view);
        emptyLayout = view.findViewById(R.id.showEmpty);
        TV_name = view.findViewById(R.id.Tv_tedadHokmKar_name);
    }




    public void viewModel() {
        loadingView.setVisibility(View.VISIBLE);
        viewModel.getTedadHokmKarhaDetails(ConstValue.startDate , ConstValue.endDate , personalCode);
        viewModel.tedadHokmKarDetailLiveData.observe(this , tedadHokmKarDetailsModel -> {
            if (tedadHokmKarDetailsModel.getData().size() < 1){
                loadingView.setVisibility(View.GONE);
                emptyLayout.setVisibility(View.VISIBLE);
            }else {
                emptyLayout.setVisibility(View.GONE);
                loadingView.setVisibility(View.GONE);
                setRecyclerView(tedadHokmKarDetailsModel);
            }

        });
    }

    private void setRecyclerView(TedadHokmKarDetailsModel tedadHokmKarDetailsModel) {

        adapter = new TedadHokmKarDetailsAdapter(getContext(),tedadHokmKarDetailsModel.getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext() , RecyclerView.VERTICAL,false));
    }


    @Override
    public void onStop() {
        mustHide.setValue(false);
        super.onStop();
    }
}