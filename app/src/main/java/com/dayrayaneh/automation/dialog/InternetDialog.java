package com.dayrayaneh.automation.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.MutableLiveData;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.base.InternetConnection;
import com.dayrayaneh.automation.utils.Utils;
import com.google.android.material.button.MaterialButton;

public class InternetDialog extends DialogFragment {

    private MaterialButton tryAgain;
    private MaterialButton close;
    private InternetConnection internetConnection = new InternetConnection();
    public MutableLiveData<Boolean> connectedLiveData = new MutableLiveData<>();


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_network_check, null);
        builder.setView(view);

        tryAgain = view.findViewById(R.id.tryAgain_networkCheck);
        close = view.findViewById(R.id.close_networkCheck);

        close.setOnClickListener(v -> {
            dismiss();
        });

        tryAgain.setOnClickListener(v -> {
            dismiss();
           if ( Utils.checkConnectivity(getContext())){
               Toast.makeText(getContext(), "اتصال انجام شد", Toast.LENGTH_SHORT).show();
           }else {
               connectedLiveData.postValue(false);
           }

        });

        return builder.create();
    }
}
