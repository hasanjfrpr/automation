package com.dayrayaneh.automation.view.pishkhanItemView.ticket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.base.BaseActivity;
import com.dayrayaneh.automation.view.pishkhanItemView.ticket.fragment.FilterTicketFragment;
import com.dayrayaneh.automation.view.pishkhanItemView.ticket.fragment.TicketDetailsFragment;
import com.dayrayaneh.automation.view.pishkhanItemView.ticket.fragment.TicketMainFragment;
import com.dayrayaneh.automation.viewModel.pishkhan.ticket.TicketViewModel;

public class TicketActivity extends BaseActivity {

    private TicketViewModel thisViewModel;
    private Toolbar toolbar;
    private ImageView back;
    private View loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        init();
        event();
    }


    private void init() {
        getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frame_ticket, new FilterTicketFragment(), "filterTicket").commit();
        thisViewModel = new ViewModelProvider(this).get(TicketViewModel.class);
        toolbar = findViewById(R.id.toolbar_ticket);
        toolbar.setTitle(getResources().getString(R.string.ticket));
        setSupportActionBar(toolbar);
        back = findViewById(R.id.IV_back_ticket);
        loadingView = findViewById(R.id.loading_view_ticket);

    }



    private void event() {
        back.setOnClickListener(v -> {
            if (getSupportFragmentManager().findFragmentByTag("ticketMain") != null){
                getSupportFragmentManager().popBackStack();
            }else if (getSupportFragmentManager().findFragmentByTag("filterTicket") != null){
                finish();
            }
        });

       FilterTicketFragment.show_loading.observe(this , isShow->{
           if (isShow){
               loadingView.setVisibility(View.VISIBLE);
           }else {
               loadingView.setVisibility(View.GONE);
           }
       });
       FilterTicketFragment.filterFragmentIsOn.observe(this,filterFragmentisOn->{
           if (filterFragmentisOn) {
               toolbar.setTitle(getResources().getString(R.string.filterTicket));
           }else{ toolbar.setTitle("");}
       });
        TicketMainFragment.ticketMainFragmentIsOn.observe(this,filterFragmentisOn->{
            if (filterFragmentisOn) {
                toolbar.setTitle(getResources().getString(R.string.ticket));
            }else{ toolbar.setTitle("");}
        });

        TicketDetailsFragment.isTicketDetailsIsOn.observe(this, filterFragmentisOn->{
            if (filterFragmentisOn) {
                toolbar.setTitle(getResources().getString(R.string.ticketDetails));
            }else{ toolbar.setTitle("");}
        });
        TicketDetailsFragment.showLoading.observe(this, isShow->{
            if (isShow){
                loadingView.setVisibility(View.VISIBLE);
            }else {
                loadingView.setVisibility(View.GONE);
            }
        });
    }



    @Override
    public void onBackPressed() {
      if (getSupportFragmentManager().findFragmentByTag("ticketMain") != null){
            getSupportFragmentManager().popBackStack();
        }else if (getSupportFragmentManager().findFragmentByTag("filterTicket") != null){
           finish();
        }
    }
}