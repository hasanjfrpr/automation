package com.dayrayaneh.automation.view.pishkhanItemView.ticket.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.utils.Utils;

public class FilterTicketFragment extends Fragment {

    private TextView startDate , endDate;
    private EditText title , description;
    private CheckBox unChecked ,pending , queue , doing , stopped , finished,canceled,myTicket , currentTicket , allTicket;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filter_ticket, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        setDate();
    }

    private void init(View view) {
        startDate = view.findViewById(R.id.TV_startDate_filter_ticket);
        endDate = view.findViewById(R.id.TV_endDate_filter_ticket );
        title = view.findViewById(R.id.ET_ticket_title);
        description = view.findViewById(R.id.ET_ticket_description);
        unChecked = view.findViewById(R.id.checkbox_unChecked);
        pending = view.findViewById(R.id.checkbox_checking);
        queue = view.findViewById(R.id.checkbox_inline);
        doing = view.findViewById(R.id.checkbox_doing);
        stopped = view.findViewById(R.id.checkbox_stopped);
        finished = view.findViewById(R.id.checkbox_finish);
        canceled = view.findViewById(R.id.checkbox_archive);
        myTicket = view.findViewById(R.id.checkbox_myTicket);
        allTicket = view.findViewById(R.id.checkbox_allTicket);
        currentTicket = view.findViewById(R.id.checkbox_currentTicket);

    }

    private void setDate(){
        Utils.setDate(startDate , endDate , getActivity());
    }
}