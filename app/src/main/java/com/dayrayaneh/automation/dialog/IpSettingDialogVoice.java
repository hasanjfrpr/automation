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
import com.dayrayaneh.automation.base.ConstValue;
import com.google.android.material.button.MaterialButton;

import java.util.Objects;

public class IpSettingDialogVoice extends DialogFragment {

    private MaterialButton ok;
    private EditText et_ip, et_port;
    public EventIpDialogVoice eventIpDialogvoice;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_setting_ip_voice, null);
        builder.setView(view);
        ok = view.findViewById(R.id.Mbtn_ok_ip_dialog);
        et_ip = view.findViewById(R.id.ET_setting_ip);
        et_port = view.findViewById(R.id.ET_setting_port);

        et_ip.setText(ConstValue.ip_voice);
        et_port.setText(ConstValue.port_voice);


        ok.setOnClickListener(v -> {

            eventIpDialogvoice.eventVoice(Objects.requireNonNull(et_ip.getText()).toString().trim(), et_port.getText().toString().trim());
            dismiss();

        });


        return builder.create();
    }

    public interface EventIpDialogVoice {
        void eventVoice(String ip, String port);
    }
}
