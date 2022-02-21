package com.dayrayaneh.automation.base;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.view.login.LoginActivity;
import com.dayrayaneh.automation.view.main.MainActivity;
import com.google.android.material.button.MaterialButton;

public class CrashHandlerActivity extends AppCompatActivity {


    TextView crash;
    MaterialButton sendCrash, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctash_handler);
        init();
        event();
    }


    private void init() {
        crash = findViewById(R.id.TV_crash);
        sendCrash = findViewById(R.id.Mbtn_sendCrash);
        back = findViewById(R.id.Mbtn_Crash_back);
    }

    private void event() {
        crash.setText(getIntent().getExtras().getString("error"));
        back.setOnClickListener(v -> {
            Intent setIntent = new Intent(this, LoginActivity.class);
            setIntent.addCategory(Intent.CATEGORY_HOME);
            setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(setIntent);
            finish();
        });
        sendCrash.setOnClickListener(view -> {
//            Intent intent = new Intent(Intent.ACTION_SEND);
//            intent.putExtra(Intent.EXTRA_TEXT , crash.getText() );
//            intent.setType("text/plain");
//            startActivity(Intent.createChooser(intent,null));
//

            ///////////////////////
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"hsn.jafarpoor@gmail.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT, "گزارش خطا");
            intent.putExtra(Intent.EXTRA_TEXT, getIntent().getExtras().getString("error"));
            startActivity(Intent.createChooser(intent, "Send email"));

        });
    }

}