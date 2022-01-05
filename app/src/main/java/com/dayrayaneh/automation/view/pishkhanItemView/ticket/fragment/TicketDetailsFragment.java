package com.dayrayaneh.automation.view.pishkhanItemView.ticket.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.RecoverySystem;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.adapter.pishkhan.ticket.details.TicketDetailsAdapter;
import com.dayrayaneh.automation.base.BaseFragment;
import com.dayrayaneh.automation.model.pishkhan.tickets.detilas.TicketDetailsModel;
import com.dayrayaneh.automation.viewModel.pishkhan.ticket.TicketViewModel;


public class TicketDetailsFragment extends BaseFragment {

    private int id;
    private TicketViewModel thisViewModel;
    public static MutableLiveData<Boolean> showLoading=new MutableLiveData<>();
    public static MutableLiveData<Boolean> isTicketDetailsIsOn=new MutableLiveData<>();
    private TicketDetailsAdapter adapter;
    private RecyclerView recyclerView;

    public TicketDetailsFragment(int id) {
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ticket_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        viewModel();
    }

    private void init(View view) {
        thisViewModel = new ViewModelProvider(this).get(TicketViewModel.class);
        recyclerView = view.findViewById(R.id.RV_ticketDetails);
    }

    private void viewModel(){
        showLoading.setValue(true);
        thisViewModel.getTicketDetails(id);
        thisViewModel.ticketDetailsModelLiveData.observe(this,ticketDetailsModel -> {
            showLoading.setValue(false);
            setRecyclerView(ticketDetailsModel);
        });
    }

    private void setRecyclerView(TicketDetailsModel ticketDetailsModel) {
        adapter  = new TicketDetailsAdapter(getContext() , ticketDetailsModel.getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext() , RecyclerView.VERTICAL , false));
    }

    @Override
    public void onStart() {
        isTicketDetailsIsOn.setValue(true);
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        isTicketDetailsIsOn.setValue(false);
    }
}