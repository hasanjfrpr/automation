package com.dayrayaneh.automation.view.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
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
import com.dayrayaneh.automation.view.login.LoginActivity;
import com.dayrayaneh.automation.view.pishkhanItemView.VoicePoshtibani.VoicePoshtibaniActivity;
import com.dayrayaneh.automation.view.pishkhanItemView.bazaryabi.BazaryabiActivity;
import com.dayrayaneh.automation.view.pishkhanItemView.darsadKhardiMoshtari.DarsadKharidMoshtariActivity;
import com.dayrayaneh.automation.view.pishkhanItemView.darsadKharidShahrestanha.DarsadKharidShahrestanActiviy;
import com.dayrayaneh.automation.view.pishkhanItemView.darsadSefareshat.DarsadSefareshatActivity;
import com.dayrayaneh.automation.view.pishkhanItemView.darsadTakhfifAzHarSefaresh.DarsadTakhfifAzHarSefareshActivity;
import com.dayrayaneh.automation.view.pishkhanItemView.foroshNarmAfZar.ForooshNarmAfzarActivity;
import com.dayrayaneh.automation.view.pishkhanItemView.foroshSakhtAfzar.ForooshSakhtAfzarActivity;
import com.dayrayaneh.automation.view.pishkhanItemView.gozareshKar.GozareshKarActivity;
import com.dayrayaneh.automation.view.pishkhanItemView.hokmKarha.HokmKarhaActivity;
import com.dayrayaneh.automation.view.pishkhanItemView.khadamatPoshtibani.KhadamatPoshtibaniActivity;
import com.dayrayaneh.automation.view.pishkhanItemView.sefarashMoshtariJadid.SefareshMoshtariJadidActivity;
import com.dayrayaneh.automation.view.pishkhanItemView.tamdidGharardad.TamdidGharardadActivity;
import com.dayrayaneh.automation.view.pishkhanItemView.tedadHokmKarha.TedadHokmKarhaActivity;
import com.dayrayaneh.automation.view.pishkhanItemView.ticket.TicketActivity;
import com.dayrayaneh.automation.view.pishkhanItemView.vaziatSefaresh.VaziatSefareshActivity;
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

public class MainActivity extends BaseActivity implements MainAdapter.OnclickItemSubRecycler {


    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private TextView date, time, username_nav, username_bottom;
    private List<String> titles = new ArrayList<>();
    private List<Integer> icon = new ArrayList<>();
    private List<PishKhanModel> pishKhanModelList = new ArrayList<>();
    private List<MainListModel> mainListModelList = new ArrayList<>();
    private RecyclerView mainRecyclerView;
    private FrameLayout frameLayout;


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
        toolbar.setTitleTextColor(getResources().getColor(R.color.design_default_color_on_primary));
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
        frameLayout = findViewById(R.id.frameLayout);
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
                        break;

                    case R.id.exitFromApp:
                        System.exit(0);
                        break;

                    case R.id.exitFromAccount:
                        Intent i = new Intent(MainActivity.this, LoginActivity.class);
                        ConstValue.tokenContainer = null;
                        ConstValue.endDate = null;
                        ConstValue.startDate = null;
                        ConstValue.accessItemIdList = null;
                        ConstValue.isAdminLis = null;
                        ConstValue.endDatePersian = null;
                        ConstValue.startDatePersian = null;
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        finish();
                        break;

                    case R.id.setting:
                        Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                        intent.putExtra(Keys.DATA, "fromMain");
                        startActivity(intent);
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

        MainAdapter adapter = new MainAdapter(mainListModelList, this);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mainRecyclerView.setAdapter(adapter);
        adapter.onclickItemSubRecycler = this;


    }


    private void getUsername() {
        Bundle bundle;
        bundle = getIntent().getExtras();
        if (bundle != null) {

            LoginModel loginModel = bundle.getParcelable(Keys.DATA);
            String username = loginModel.getData().get(0).getUserName();
            String name = loginModel.getData().get(0).getName();
            if (!username.equals("") || username != null) {
                username_nav.setText(username);

            }
            if (!name.equals("") || name != null) {
                username_bottom.setText(name);

            }
        }


    }


    @SuppressLint("WrongConstant")
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawer(Gravity.START);
        } else if (ConstValue.menuIsOpen) {
            setupMainRecyclerView();
            ConstValue.menuIsOpen = false;
        } else if (!ConstValue.menuIsOpen || drawerLayout.isDrawerOpen(Gravity.END)) {
            super.onBackPressed();
        }

    }


    @Override
    public void onclick(int id) {

        switch (id) {
            case 6:
                startActivity(new Intent(this, ForooshNarmAfzarActivity.class));

                break;
            case 7:
                startActivity(new Intent(this, ForooshSakhtAfzarActivity.class));



                break;
            case 8:
                startActivity(new Intent(this, TamdidGharardadActivity.class));


                break;
            case 3:
                startActivity(new Intent(this, KhadamatPoshtibaniActivity.class));


                break;
            case 1:
                startActivity(new Intent(this, HokmKarhaActivity.class));


                break;
            case 2:
                startActivity(new Intent(this, BazaryabiActivity.class));


                break;
            case 9:
                startActivity(new Intent(this, SefareshMoshtariJadidActivity.class));


                break;
            case 10:
                startActivity(new Intent(this, VaziatSefareshActivity.class));


                break;
            case 11:
                startActivity(new Intent(this, DarsadKharidMoshtariActivity.class));


                break;
            case 5:
                startActivity(new Intent(this, GozareshKarActivity.class));


                break;
            case 12:
                startActivity(new Intent(this, DarsadTakhfifAzHarSefareshActivity.class));


                break;
            case 13:
                startActivity(new Intent(this, DarsadSefareshatActivity.class));

                break;
            case 4:
                startActivity(new Intent(this, TedadHokmKarhaActivity.class));

                break;
            case 14:
                startActivity(new Intent(this, DarsadKharidShahrestanActiviy.class));
                break;
            case 15:
                startActivity(new Intent(this, VoicePoshtibaniActivity.class));
                break;
            case 16:
                startActivity(new Intent(this, TicketActivity.class));
                break;


        }
    }
}