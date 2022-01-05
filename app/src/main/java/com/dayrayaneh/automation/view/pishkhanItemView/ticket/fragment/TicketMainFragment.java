package com.dayrayaneh.automation.view.pishkhanItemView.ticket.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.adapter.pishkhan.ticket.TicketAdapter;
import com.dayrayaneh.automation.base.BaseFragment;
import com.dayrayaneh.automation.model.pishkhan.tickets.TicketModel;

import java.util.ArrayList;
import java.util.List;

public class TicketMainFragment extends BaseFragment implements TicketAdapter.TicketItem {

    private TicketModel ticketModels ;
    private RecyclerView recyclerView;
    private TicketAdapter adapter;
    public static MutableLiveData<Boolean> ticketMainFragmentIsOn = new MutableLiveData<>();

    public TicketMainFragment(TicketModel ticketModels) {
        this.ticketModels = ticketModels;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ticket_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        setupRecyclerView();
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.RV_ticketMain);
    }

    private void setupRecyclerView(){
        adapter = new TicketAdapter(getContext() , ticketModels.getData());
        adapter.ticketItem = this;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext() , RecyclerView.VERTICAL ,false));
    }
    @Override
    public void onStart() {
        ticketMainFragmentIsOn.setValue(true);
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        ticketMainFragmentIsOn.setValue(false);
    }

    @Override
    public void ticketItemClick(int id) {
        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frame_ticket , new TicketDetailsFragment(id),"ticketDetails").commit();
    }
}