package com.dayrayaneh.automation.view.pishkhanItemView.unDoneHokmKar.fragment;

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
import com.dayrayaneh.automation.adapter.pishkhan.UndoneHokmKar.UnDoneHokmKarAdapter;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.model.pishkhan.UnDoneHokmKar.UnDoneHokmKarModel;
import com.dayrayaneh.automation.viewModel.pishkhan.UndoneHokmKar.UnDoneHokmKarViewModel;

public class DetailsUnDoneHokmKarFragment extends Fragment {

    private int personId;
    private String name;
    private UnDoneHokmKarViewModel thisViewModel;
    private UnDoneHokmKarAdapter adapter;
    private RecyclerView recyclerView;
    private TextView personName ;
    private ImageView back;
    private View loading;
    public static MutableLiveData<Boolean> isHide = new MutableLiveData<>();


    public DetailsUnDoneHokmKarFragment(int personId , String  name) {
        this.personId = personId;
        this.name = name;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_un_done_hokm_kar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        viewModel();
        event();
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.RV_undone_hokmKar_details);
        thisViewModel = new ViewModelProvider(this).get(UnDoneHokmKarViewModel.class);
        personName = view.findViewById(R.id.TV_personName_undonHokmKar_detials);
        back = view.findViewById(R.id.IV_back_undon_details);
        personName = view.findViewById(R.id.TV_personName_undonHokmKar_detials);
        personName.setText(name);
        loading = view.findViewById(R.id.loadin_details_undon);

    }


    private void viewModel() {
        loading.setVisibility(View.VISIBLE);
        thisViewModel.getUnDoneHokmKar(ConstValue.startDate , ConstValue.endDate , personId);
        thisViewModel.unDoneHokmKarModelMutableLiveData.observe(this,unDoneHokmKarModel -> {
            loading.setVisibility(View.INVISIBLE);

            if (unDoneHokmKarModel.getData().size() < 1){
            }else {
                setRecyclerView(unDoneHokmKarModel);
            }
        });
    }



    private void event(){
        back.setOnClickListener(view -> {
            getActivity().getSupportFragmentManager().popBackStack();
        });
    }

    private void setRecyclerView(UnDoneHokmKarModel unDoneHokmKarModel) {
        adapter = new UnDoneHokmKarAdapter(getContext() , unDoneHokmKarModel.getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL , false));
    }

    @Override
    public void onStart() {
        super.onStart();
        isHide.setValue(true);
    }

    @Override
    public void onStop() {
        super.onStop();
        isHide.setValue(false);
    }
}