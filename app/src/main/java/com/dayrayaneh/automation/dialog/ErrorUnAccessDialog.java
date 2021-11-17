package com.dayrayaneh.automation.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.dayrayaneh.automation.R;
import com.google.android.material.button.MaterialButton;

public class ErrorUnAccessDialog extends DialogFragment {

    private MaterialButton clos;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_errro_unaccess,null);
        builder.setView(view);

        clos = view.findViewById(R.id.Mbtn_back_unAccess_error);
        clos.setOnClickListener(v -> {
            dismiss();
        });


        return builder.create();


    }
}
