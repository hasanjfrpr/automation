package com.dayrayaneh.automation.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.dayrayaneh.automation.R;
import com.google.android.material.button.MaterialButton;

public class SearchVoiceDialog extends DialogFragment {
    private EditText serial , mobile;
    private CheckBox checkBox;
    private MaterialButton sendInfo;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view  = LayoutInflater.from(getContext()).inflate(R.layout.dialog_search_voice,null);
        builder.setView(view);
        serial = view.findViewById(R.id.ET_Dialog_searchVoice_serial);
        mobile = view.findViewById(R.id.ET_Dialog_searchVoice_mobile);
        sendInfo = view.findViewById(R.id.Mbtn_sendInfo_voice);
        checkBox = view.findViewById(R.id.checkbox_all_searchVoiceDialog);
        return builder.create();
    }
}
