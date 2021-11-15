package com.dayrayaneh.automation.view.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.adapter.mainListadapter.MainAdapter;
import com.dayrayaneh.automation.base.BaseActivity;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.base.Keys;
import com.dayrayaneh.automation.model.login.LoginModel;
import com.dayrayaneh.automation.model.pishKhanModel.PishKhanModel;
import com.dayrayaneh.automation.model.mainListModel.MainListModel;
import com.dayrayaneh.automation.view.setting.SettingActivity;
import com.google.android.material.navigation.NavigationView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

public class MainActivity extends BaseActivity {


    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private TextView date, time, username_nav, username_bottom;
    private List<String> titles = new ArrayList<>();
    private List<Integer> icon = new ArrayList<>();
    private List<PishKhanModel> pishKhanModelList = new ArrayList<>();
    private List<MainListModel> mainListModelList = new ArrayList<>();
    private RecyclerView mainRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer_layout);
        init();
        setupNavigationDrawer();
        setDateAndTime();
        setupMainRecyclerView();
        getUsername();


    }

    private void init() {

        toolbar = findViewById(R.id.toolbar_main);
        toolbar.setTitle(getResources().getString(R.string.automation));
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        date = findViewById(R.id.TV_date);
        time = findViewById(R.id.TV_time);
//        gridView = findViewById(R.id.grid);
        View nav_header = navigationView.getHeaderView(0);
        username_nav = nav_header.findViewById(R.id.TV_username_navDrawer);
        username_bottom = findViewById(R.id.TV_username_bottom_main);
        mainRecyclerView = findViewById(R.id.RV_main);

    }

    private void setupNavigationDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open, R.string.close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("WrongConstant")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Toast.makeText(MainActivity.this, "home", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.exitFromApp:
                        System.exit(0);
                        break;

                    case R.id.exitFromAccount:
                        Toast.makeText(MainActivity.this, "exitFromAccount", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.setting:
                        startActivity(new Intent(MainActivity.this, SettingActivity.class));
                        break;

                }
                drawerLayout.closeDrawer(Gravity.START);
                return false;
            }
        });
    }

    private void setDateAndTime() {
        //use library for get date
        PersianDate pdate = new PersianDate();
        PersianDateFormat pdformater1 = new PersianDateFormat("Y/m/d");
        String dateee = pdformater1.format(pdate);//1396/05/20
        date.setText(dateee);

        ///get current time

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
                        Date currentLocalTime = cal.getTime();
                        DateFormat date = new SimpleDateFormat("HH:mm:ss ");
                        date.setTimeZone(TimeZone.getDefault());
                        String localTime = date.format(currentLocalTime);
                        time.setText(localTime);
                    }
                });
            }
        }, 0, 1000);


    }

    private void setupMainRecyclerView() {

        mainListModelList = new ArrayList<>();
        mainListModelList.add(new MainListModel("پیشخوان مدیریت"));
        mainListModelList.add(new MainListModel("گزارش لیدها "));
        MainAdapter adapter = new MainAdapter(mainListModelList, this);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mainRecyclerView.setAdapter(adapter);


    }


    private void getUsername() {
        Bundle bundle;
        bundle = getIntent().getExtras();
        if (bundle != null) {

           LoginModel loginModel = bundle.getParcelable(Keys.DATA);
            String username = loginModel.getData().get(0).getUserName();
            if (!username.equals("") || username!=null) {
                username_nav.setText(username);
                username_bottom.setText(username);
            }
        }


    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawer(Gravity.START);
        }else if (ConstValue.menuIsOpen){
            setupMainRecyclerView();
            ConstValue.menuIsOpen = false;
        }else if (!ConstValue.menuIsOpen || drawerLayout.isDrawerOpen(Gravity.END)){
            super.onBackPressed();
        }

    }


}