package com.dayrayaneh.automation.view.setting;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.base.BaseActivity;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.dialog.IpSettingDialog;
import com.dayrayaneh.automation.dialog.IpSettingDialogVoice;
import com.dayrayaneh.automation.dialog.RestartDialog;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;


public class SettingActivity extends BaseActivity implements View.OnClickListener, IpSettingDialog.EventIpDialog, RestartDialog.EventRestartDialog, IpSettingDialogVoice.EventIpDialogVoice {

    private FrameLayout fontContainer;
    private LinearLayout ipContainer;
    private LinearLayout ipContainerVoice;
    private TextView font;
    private String[] fontList;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Toolbar toolbar;
    private ImageView ic_back;
    private String comeFrom;
    private IpSettingDialog ipSettingDialog;
    private IpSettingDialogVoice ipSettingDialogVoice;
    private TextView ip, port, ip_voice, port_voice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        init();
        getDefaultUserIp();


    }


    private void init() {
        font = findViewById(R.id.TV_setting_font);
        ic_back = findViewById(R.id.IV_setting_back);
        ic_back.setOnClickListener(this);
        fontContainer = findViewById(R.id.setting_font_frame);
        ipContainer = findViewById(R.id.setting_ip_frame);
        ipContainer.setOnClickListener(this);
        ipContainerVoice = findViewById(R.id.setting_ip_frame_voice);
        ipContainerVoice.setOnClickListener(this);
        toolbar = findViewById(R.id.toolbar_setting);
        toolbar.setTitle(getResources().getString(R.string.setting));
        setSupportActionBar(toolbar);
        fontContainer.setOnClickListener(this);
        sharedPreferences = this.getSharedPreferences("default_setting", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        fontList = getResources().getStringArray(R.array.fontArray);
        font.setText(fontList[sharedPreferences.getInt("fontId", 0)]);
        ip = findViewById(R.id.TV_setting_ip);
        port = findViewById(R.id.TV_setting_port);
        ip_voice = findViewById(R.id.TV_setting_ip_voice);
        port_voice = findViewById(R.id.TV_setting_port_voice);

    }

    private void showAlertdialog(String[] items) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.selectFont));
        builder.setSingleChoiceItems(items, sharedPreferences.getInt("fontId", 0), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        font.setText(fontList[0]);
                        editor.putInt("fontId", 0);
                        editor.putString("font", "fonts/iransans.ttf");
                        setFont("fonts/iransans.ttf");
                        showDialogForRestart();
                        break;

                    case 1:
                        font.setText(fontList[1]);
                        editor.putInt("fontId", 1);
                        editor.putString("font", "fonts/hekayat.ttf");
                        setFont("fonts/hekayat.ttf");
                        showDialogForRestart();
                        break;

                    case 2:
                        font.setText(fontList[2]);
                        editor.putInt("fontId", 2);
                        editor.putString("font", "fonts/yekan.ttf");
                        setFont("fonts/yekan.ttf");
                        showDialogForRestart();
                        break;

                }
                dialog.dismiss();
                editor.apply();
            }
        });
        builder.create().show();
    }

    private void setFont(String fontAddress) {
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath(fontAddress)
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());
    }


    private void getDefaultUserIp() {

        this.ip.setText(sharedPreferences.getString("ip", ""));
        this.port.setText(sharedPreferences.getString("port", ""));

        this.ip_voice.setText(sharedPreferences.getString("ip_Voice", ""));
        this.port_voice.setText(sharedPreferences.getString("port_Voice", ""));


    }

    private void restartApp(Context cnt) {
        Intent i = cnt.getPackageManager().getLaunchIntentForPackage(cnt.getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        cnt.startActivity(i);
        finish();

    }

    private void showAlertDialogIp() {
        ipSettingDialog = new IpSettingDialog();
        ipSettingDialog.eventIpDialog = this;
        ipSettingDialog.show(getSupportFragmentManager(), "");
    }

    private void showAlertDialogIpVoice() {
        ipSettingDialogVoice = new IpSettingDialogVoice();
        ipSettingDialogVoice.eventIpDialogvoice = this;
        ipSettingDialogVoice.show(getSupportFragmentManager(), "");
    }

    private void showDialogForRestart() {
        RestartDialog restartDialog = new RestartDialog();
        restartDialog.restartDialog = this;
        restartDialog.show(getSupportFragmentManager(), "");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_font_frame:
                showAlertdialog(fontList);

                break;
            case R.id.IV_setting_back:
                finish();
                break;
            case R.id.setting_ip_frame:
                showAlertDialogIp();
                break;
            case R.id.setting_ip_frame_voice:
                showAlertDialogIpVoice();
                break;
        }
    }


    @Override
    public void restartEvent() {
        restartApp(getBaseContext());
    }


    @Override
    public void event(String ip, String port) {
        this.ip.setText(ip);
        this.port.setText(port);
        editor.putString("ip", ip);
        editor.putString("port", port);
        editor.apply();
        ConstValue.ip = ip;
        ConstValue.port = port;

    }

    @Override
    public void eventVoice(String ip, String port) {
        this.ip_voice.setText(ip);
        this.port_voice.setText(port);
        editor.putString("ip_Voice", ip);
        editor.putString("port_Voice", port);
        editor.apply();
        ConstValue.ip_voice = ip;
        ConstValue.port_voice = port;

    }
}