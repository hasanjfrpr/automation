package com.dayrayaneh.automation.view.login;

import android.animation.Animator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.airbnb.lottie.LottieAnimationView;
import com.dayrayaneh.automation.base.AutomationSingleObserver;
import com.dayrayaneh.automation.base.BaseActivity;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.base.Keys;
import com.dayrayaneh.automation.dialog.ErrorIpDialog;
import com.dayrayaneh.automation.dialog.ErrorUnAccessDialog;
import com.dayrayaneh.automation.model.login.LoginModel;
import com.dayrayaneh.automation.utils.Utils;
import com.dayrayaneh.automation.view.main.MainActivity;
import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.view.setting.SettingActivity;
import com.dayrayaneh.automation.viewModel.login.LoginViewModel;
import com.dayrayaneh.automation.viewModel.login.LoginViewModelFactory;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends BaseActivity {

    private EditText username, password;
    private MaterialButton login_btn;
    private String S_username ;
    private String S_password;
    private LoginViewModel loginViewModel;
    private LottieAnimationView successLottie;
    private LinearLayout setting;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private SharedPreferences sharedPreferences ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        event();

    }

    private void init() {
        username = findViewById(R.id.ET_username);
        password = findViewById(R.id.ET_password);
        login_btn = findViewById(R.id.Mbtn_login);
        successLottie = findViewById(R.id.lottie_success);
        setting = findViewById(R.id.IV_setting);
        sharedPreferences= getSharedPreferences("default_setting",MODE_PRIVATE);
        /// init viewModel
        loginViewModel = new ViewModelProvider(this,new LoginViewModelFactory()).get(LoginViewModel.class);

    }

    private void event() {

        login_btn.setOnClickListener(v -> {
            ///// check pattern EditText for login

            S_username = username.getText().toString().trim();
            S_password = password.getText().toString().trim();
            Utils.hideKeyboard(this);
            if (S_username.matches("")) {
                username.setError("این فیلد الزامی است");
                Toast.makeText(this, "تمامی فیلد ها باید تکمیل شوند", Toast.LENGTH_SHORT).show();
            } else if (S_password.matches("")) {
                password.setError("این فیلد الزامی است");
                Toast.makeText(this, "تمامی فیلد ها باید تکمیل شوند", Toast.LENGTH_SHORT).show();
            }else if (sharedPreferences.getString("ip","").isEmpty() || sharedPreferences.getString("ip","").equals("")){
                ErrorIpDialog errorIpDialog = new ErrorIpDialog() ;
                errorIpDialog.show(getSupportFragmentManager() , "");

            } else if (!ConstValue.ip.equals(ConstValue.ip_base) || !ConstValue.ip.equals(sharedPreferences.getString("ip" , null))){
              Snackbar.make(login_btn , "آدرس Ip اشتباه است",Snackbar.LENGTH_LONG).show();
            }else{
                ////////send username and password to server
                viewModel();
            }

        });

        setting.setOnClickListener(v -> {
           Intent intent = new Intent(this, SettingActivity.class);
           intent.putExtra(Keys.DATA , "fromLogin");
           startActivity(intent);
        });

    }

    private void showAccessAnimation(LoginModel loginModel){

        ///show animation and after that go to mainActivity


        successLottie.setAnimation(R.raw.lottie_success);
        successLottie.setRepeatCount(0);
        successLottie.playAnimation();
        successLottie.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("username", S_username);
                intent.putExtra(Keys.DATA,  loginModel);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


    }

    private void viewModel(){

        successLottie.setAnimation(R.raw.lottie_loading);
        successLottie.setVisibility(View.VISIBLE);
       successLottie.setRepeatCount(1000);
        successLottie.playAnimation();
        loginViewModel.sendUsername(S_username,S_password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutomationSingleObserver<LoginModel>(compositeDisposable) {
                    @Override
                    public void onSuccess(@NonNull LoginModel loginModel) {
                        boolean error = loginModel.getStatus().isIsError();
                        if (!error){
                            //// if access is true , show  animation and hide keyboard
                            showAccessAnimation(loginModel);
                            ///save token to tokenContainer
              ConstValue.tokenContainer = loginModel.getData().get(0).getToken();
              ConstValue.accessItemIdList = loginModel.getData().get(0).getAcceccMenuIdArray();
              ConstValue.isAdminLis = loginModel.getData().get(0).getAcceccGroupIdArray();

                        }else{
                            Snackbar.make(LoginActivity.this, username, loginModel.getStatus().getMessage(), Snackbar.LENGTH_LONG).show();
                             successLottie.setVisibility(View.INVISIBLE);
                        }

                    }
                });


    }


    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}

