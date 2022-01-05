package com.dayrayaneh.automation.view.pishkhanItemView.ticket.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.base.BaseFragment;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.utils.Utils;
import com.dayrayaneh.automation.viewModel.pishkhan.ticket.TicketViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

public class FilterTicketFragment extends BaseFragment {

    private TextView startDate , endDate;
    private EditText title , description;
    private CheckBox unChecked ,pending , queue , doing , stopped , finished,canceled,myTicket , currentTicket , allTicket;
    private boolean b_unChecked ,b_pending ,b_queue , b_doing , b_stopped , b_finished, b_canceled, b_myTicket , b_currentTicket , b_allTicket;
    private MaterialButton filter , removeFilter;
    private String s_title , s_description;
    private TicketViewModel thisViewModel;
    public static MutableLiveData<Boolean> show_loading = new MutableLiveData<>();
    public static MutableLiveData<Boolean> filterFragmentIsOn = new MutableLiveData<>();
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
        event();
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
        filter = view.findViewById(R.id.Mbtn_ticket_filter);
        removeFilter = view.findViewById(R.id.Mbtn_ticket_removeFilter);
        thisViewModel = new ViewModelProvider(this).get(TicketViewModel.class);
        defaultValue();
    }

    private void defaultValue(){
       /* b_unChecked ,b_pending ,b_queue , b_doing , b_stopped , b_finished, b_canceled, b_myTicket , b_currentTicket , b_allTicket*/
        b_unChecked =true;
        b_pending = true;
        b_queue = true;
        b_doing = true;
        b_stopped = true;
        b_finished = true;
        b_canceled =true;
        b_myTicket=false;
        b_currentTicket = false;
        b_allTicket=false;
    }

    private void setDate(){
        Utils.setDate(startDate , endDate , getActivity());
    }

    private void event(){
        filter.setOnClickListener(v->{
            Utils.hideKeyboard(getActivity());
            s_title = title.getText().toString().trim();
            s_description = description.getText().toString().trim();
            viewModel();
        });

        removeFilter.setOnClickListener(v->{
            Utils.hideKeyboard(getActivity());
            removeFilter();
        });


        ////only admin access(look allTicket)
        if (!ConstValue.isAdminLis.contains(1)){
            b_allTicket=false;
            allTicket.setClickable(false);
            allTicket.setTextColor(getContext().getResources().getColor(R.color.gray));
        }


        unChecked.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            b_unChecked = isChecked;
        });
        pending.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            b_pending = isChecked;
        });
        finished.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            b_finished=isChecked;
        });
        canceled.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            b_canceled=isChecked;
        });
        doing.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            b_doing =isChecked;
        });
        stopped.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            b_stopped = isChecked;
        });
        queue.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            b_queue = isChecked;
        });
        myTicket.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            b_myTicket=isChecked;
        });
        currentTicket.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            b_currentTicket=isChecked;
        });
        allTicket.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            b_allTicket=isChecked;
        });


    }

    private void viewModel() {
        show_loading.setValue(true);
      if (thisViewModel.ticketModelLiveData !=null){
          thisViewModel.ticketModelLiveData.getValue();
      }
        thisViewModel.getTickets(ConstValue.startDate , ConstValue.endDate , ConstValue.userCode , b_unChecked ,b_pending ,b_queue, b_doing ,b_stopped,b_finished,b_canceled,s_title,s_description,b_myTicket,b_currentTicket,b_allTicket );
        thisViewModel.ticketModelLiveData.observe(this,ticketModel -> {

            if (ticketModel.getData().size() == 0){
                show_loading.setValue(false);
                Snackbar.make(filter ,"با اعمال این فیلتر داده ای یافت نشد!!",Snackbar.LENGTH_SHORT).show();
            }else {
                show_loading.setValue(false);
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frame_ticket, new TicketMainFragment(ticketModel), "ticketMain").commit();
            }
        });
    }

    private void removeFilter(){
        defaultValue();
        unChecked.setChecked(true);
        pending.setChecked(true);
        finished.setChecked(true);
        doing.setChecked(true);
        queue.setChecked(true);
        allTicket.setChecked(false);
        currentTicket.setChecked(false);
        myTicket.setChecked(false);
        stopped.setChecked(true);
        canceled.setChecked(true);
        title.setText("");
        description.setText("");
        PersianDate mDate = new PersianDate();
        PersianDateFormat format = new PersianDateFormat("Y/m/d");
        String currentDate = format.format(mDate);
            startDate.setText(currentDate);
            endDate.setText(currentDate);
            ////save default persianDate
            ConstValue.startDatePersian = currentDate;
            ConstValue.endDatePersian = currentDate;
            ///// default gregorian date
            ConstValue.startDate =   Utils.convertPersianDateToFormatOfServer(mDate.getShYear() , mDate.getShMonth() , mDate.getShDay());
            ConstValue.endDate =   Utils.convertPersianDateToFormatOfServer(mDate.getShYear() , mDate.getShMonth() , mDate.getShDay());



    }

    @Override
    public void onStart() {
        filterFragmentIsOn.setValue(true);
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        filterFragmentIsOn.setValue(false);
    }
}