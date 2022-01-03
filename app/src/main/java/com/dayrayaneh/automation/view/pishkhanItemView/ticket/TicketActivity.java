package com.dayrayaneh.automation.view.pishkhanItemView.ticket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.base.BaseActivity;
import com.dayrayaneh.automation.view.pishkhanItemView.ticket.fragment.FilterTicketFragment;
import com.dayrayaneh.automation.viewModel.pishkhan.ticket.TicketViewModel;

public class TicketActivity extends BaseActivity {

    private TicketViewModel thisViewModel;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        init();
        viewMode();
    }

    private void init() {
        toolbar = findViewById(R.id.toolbar_ticket);
        toolbar.setTitle(getResources().getString(R.string.ticket));
        setSupportActionBar(toolbar);
        thisViewModel = new ViewModelProvider(this).get(TicketViewModel.class);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_ticket , new FilterTicketFragment()).commit();
    }

    private void viewMode(){
        thisViewModel.getTickets("2021-01-01","2021-12-12",304,
                true,true,true, true,true,
                true,true,"","",false,true,true );
        thisViewModel.ticketModelLiveData.observe(this,ticketModel -> {
            Log.i("TAG", "viewMode: "+ticketModel);
        });
    }
}