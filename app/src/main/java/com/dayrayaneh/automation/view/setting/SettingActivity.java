package com.dayrayaneh.automation.view.setting;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.provider.FontsContractCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.fonts.FontFamily;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.base.App;
import com.dayrayaneh.automation.base.BaseActivity;
import com.dayrayaneh.automation.services.httpclient.ApiInstance;
import com.dayrayaneh.automation.services.httpclient.ApiService;
import com.dayrayaneh.automation.view.main.MainActivity;

import org.greenrobot.eventbus.EventBus;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;


public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private FrameLayout fontContainer;
    private TextView font;
    private String[] fontList;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Toolbar toolbar;
    private ImageView ic_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        init();






    }


    private void init() {
        font = findViewById(R.id.TV_setting_font);
        ic_back = findViewById(R.id.IV_setting_back);
        ic_back.setOnClickListener(this);
        fontContainer = findViewById(R.id.setting_font_frame);
        toolbar = findViewById(R.id.toolbar_setting);
        toolbar.setTitle(getResources().getString(R.string.setting));
        setSupportActionBar(toolbar);
        fontContainer.setOnClickListener(this);
        sharedPreferences = this.getSharedPreferences("default_setting", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        fontList = getResources().getStringArray(R.array.fontArray);
        font.setText(fontList[sharedPreferences.getInt("fontId", 0)]);

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
                        restartApp(getBaseContext());
                        break;

                    case 1:
                        font.setText(fontList[1]);
                        editor.putInt("fontId", 1);
                        editor.putString("font", "fonts/hekayat.ttf");
                        setFont("fonts/hekayat.ttf");
                        restartApp(getBaseContext());

                        break;

                    case 2:
                        font.setText(fontList[2]);
                        editor.putInt("fontId", 2);
                        editor.putString("font", "fonts/vasir.ttf");
                        setFont("fonts/vasir.ttf");
                        restartApp(getBaseContext());
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

    private void restartApp(Context cnt){
            Intent i = cnt.getPackageManager().getLaunchIntentForPackage(cnt.getPackageName());
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            cnt.startActivity(i);
            finish();

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
        }
    }


}