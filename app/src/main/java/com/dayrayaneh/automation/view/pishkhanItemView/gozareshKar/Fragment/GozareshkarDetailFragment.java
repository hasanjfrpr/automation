package com.dayrayaneh.automation.view.pishkhanItemView.gozareshKar.Fragment;

import android.content.res.Configuration;
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
import com.dayrayaneh.automation.adapter.pishkhan.gozareshKar.detail.GozareshKarDetailsAdapter;
import com.dayrayaneh.automation.base.BaseFragment;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.model.pishkhan.Gozareshkar.details.GozareshKarDetailsModel;
import com.dayrayaneh.automation.view.pishkhanItemView.gozareshKar.GozareshKarActivity;
import com.dayrayaneh.automation.viewModel.pishkhan.GozareshKarha.GozareshKarViewModel;
import com.dayrayaneh.automation.viewModel.pishkhan.GozareshKarha.repo.GozareshKarRepo;

import org.w3c.dom.Text;

public class GozareshkarDetailFragment extends BaseFragment {

    private int userCode;
    private View emptyLayout , loadingView;
    private RecyclerView recyclerView;
    private GozareshKarViewModel viewModel;
    private GozareshKarDetailsAdapter adapter;
    public static MutableLiveData<Boolean> showAndHide = new MutableLiveData<>();
    private TextView startDate , endDate;
    private ImageView back;
    private String name;
    private TextView tv_name;

    public GozareshkarDetailFragment(int userCode , String name) {
        this.userCode = userCode;
        this.name = name;
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
        event();
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.RV_gozareshKar_details);
        emptyLayout = view.findViewById(R.id.showEmpty);
        startDate = view.findViewById(R.id.TV_showStartDate_gozareshKar_detail);
        endDate = view.findViewById(R.id.TV_showEndDate_gozareshkar_detail);
        back = view.findViewById(R.id.IV_back_detail_gozareshKar);
        loadingView = view.findViewById(R.id.loading_view);
        tv_name = view.findViewById(R.id.TV_gozareshKar_detail_name);
        tv_name.setText(name);
        viewModel = new ViewModelProvider(this).get(GozareshKarViewModel.class);

    }
    private void event(){
        startDate.setText("از "+ConstValue.startDatePersian);
        endDate.setText("تا "+ConstValue.endDatePersian);

        back.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().popBackStack();

        });
    }

    private void viewModel(){
        loadingView.setVisibility(View.VISIBLE);
        viewModel.getGozareshKarDetial(ConstValue.startDate , ConstValue.endDate ,  GozareshKarActivity.isCheck==true?GozareshKarActivity.personId :GozareshKarActivity.personIdHelp , userCode);
        viewModel.gozareshKarDetailLiveData.observe(this , gozareshKarDetailsModel -> {
            setupRecycler(gozareshKarDetailsModel);
            loadingView.setVisibility(View.GONE);
        });
    }

    private void setupRecycler(GozareshKarDetailsModel gozareshKarDetailsModel) {
        adapter = new GozareshKarDetailsAdapter(getContext() , gozareshKarDetailsModel.getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext() , RecyclerView.VERTICAL , false));
    }
    @Override
    public void onStart() {
        super.onStart();
        showAndHide.setValue(true);
    }

    @Override
    public void onStop() {
        super.onStop();
        showAndHide.setValue(false);
    }

}