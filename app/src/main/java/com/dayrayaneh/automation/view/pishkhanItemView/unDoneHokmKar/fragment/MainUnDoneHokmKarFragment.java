package com.dayrayaneh.automation.view.pishkhanItemView.unDoneHokmKar.fragment;

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
import android.widget.TextView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.adapter.pishkhan.UndoneHokmKar.count.UnDoneHokmkarCountAdapter;
import com.dayrayaneh.automation.base.BaseFragment;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.model.pishkhan.UnDoneHokmKar.Count.UnDoneHokmkarCountMode;
import com.dayrayaneh.automation.viewModel.pishkhan.UndoneHokmKar.UnDoneHokmKarViewModel;

public class MainUnDoneHokmKarFragment extends BaseFragment implements UnDoneHokmkarCountAdapter.OnItemClick {

    private UnDoneHokmKarViewModel thisViewModel;
    private RecyclerView recyclerView;
    private UnDoneHokmkarCountAdapter adapter;
    private View loading, empty;
    private TextView tv_kol_undone ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_un_done_hokm_kar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        viewModel();
    }


    private void init(View view) {
        thisViewModel = new ViewModelProvider(this).get(UnDoneHokmKarViewModel.class);
        recyclerView = view.findViewById(R.id.RV_main_undon_hokmkar);
        loading = view.findViewById(R.id.loading_undon_main);
        empty = view.findViewById(R.id.empty_stateSddd);
        tv_kol_undone = view.findViewById(R.id.TV_tedadKol_undone_hokmKar);
    }

    public void viewModel() {
        loading.setVisibility(View.VISIBLE);
        thisViewModel.getUnDoneHokmkarCount(ConstValue.startDate, ConstValue.endDate);
        thisViewModel.UndoneHokmKarCountLiveData.observe(this, unDoneHokmkarCountMode -> {
            loading.setVisibility(View.INVISIBLE);
            calculateAllUndoneHokmKar(unDoneHokmkarCountMode);
            if (unDoneHokmkarCountMode.getData().size() < 1) {
                empty.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.INVISIBLE);
            } else {
                empty.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                setrecyclerView(unDoneHokmkarCountMode);
            }
        });
    }

        private void calculateAllUndoneHokmKar(UnDoneHokmkarCountMode unDoneHokmKarModel) {
        int kol= 0;

        for (int i = 0; i < unDoneHokmKarModel.getData().size(); i++) {
            kol += unDoneHokmKarModel.getData().get(i).getCount();
        }

        tv_kol_undone.setText(String.valueOf(kol));

    }

    private void setrecyclerView(UnDoneHokmkarCountMode unDoneHokmkarCountMode) {
        adapter = new UnDoneHokmkarCountAdapter(getContext(), unDoneHokmkarCountMode.getData());
        adapter.onItemClick = this;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
    }

    @Override
    public void OnItemClicked(int personId, String name) {
        getActivity().getSupportFragmentManager().beginTransaction().hide(this).addToBackStack("").add(R.id.frame_undone_hokmkar, new DetailsUnDoneHokmKarFragment(personId, name), "detailUndon").commit();
    }
}