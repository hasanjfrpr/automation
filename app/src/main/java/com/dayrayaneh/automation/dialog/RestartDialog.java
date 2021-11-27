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

public class RestartDialog extends DialogFragment {

    private MaterialButton restart;
    private MaterialButton cancel;
    public EventRestartDialog restartDialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_restart,null);
        builder.setView(view);
        restart = view.findViewById(R.id.Mbtn_restart);
        cancel = view.findViewById(R.id.Mbtn_cancel);

        cancel.setOnClickListener(v -> {
            dismiss();
        });
        restart.setOnClickListener(v -> {
            restartDialog.restartEvent();
        });

        return builder.create();
    }

    public interface EventRestartDialog{
        void restartEvent();
    }
}
