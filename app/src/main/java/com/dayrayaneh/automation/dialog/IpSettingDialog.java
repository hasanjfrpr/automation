package com.dayrayaneh.automation.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.dayrayaneh.automation.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class IpSettingDialog extends DialogFragment {

    private MaterialButton ok;
    private EditText textInputEditText;
    public EventIpDialog eventIpDialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_setting_ip,null);
        builder.setView(view);
        ok = view.findViewById(R.id.Mbtn_ok_ip_dialog);
        textInputEditText = view.findViewById(R.id.ET_setting_ip);

        ok.setOnClickListener(v -> {
            eventIpDialog.event(Objects.requireNonNull(textInputEditText.getText()).toString().trim());
            dismiss();
        });


        return builder.create();
    }

    public interface EventIpDialog{
        void event(String ip);
    }
}
