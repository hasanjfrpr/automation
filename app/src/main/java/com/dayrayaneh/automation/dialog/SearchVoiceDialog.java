package com.dayrayaneh.automation.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.dayrayaneh.automation.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

public class SearchVoiceDialog extends DialogFragment {

    private EditText serial , mobile;
    private CheckBox checkBox;
    private MaterialButton sendInfo;
    private boolean isCheck;
    private String serial_s;
    private String mobile_s;
    public VoiceDialogEvent event;

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
        config();
        return builder.create();
    }

    private void config(){



        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    serial.setVisibility(View.GONE);
                    mobile.setVisibility(View.GONE);
                    isCheck = true;
                }else {
                    serial.setVisibility(View.VISIBLE);
                    mobile.setVisibility(View.VISIBLE);
                    isCheck = false;
                }
            }
        });


        sendInfo.setOnClickListener(view -> {

            if (isCheck){
                serial_s="";
                mobile_s="";
                event.sendInfoClick(serial_s , mobile_s);
                dismiss();
            }else {
                if (serial.getText().toString().trim().matches("") && mobile.getText().toString().trim().matches("")) {
                    Snackbar.make(getContext(), getActivity().findViewById(R.id.fab_voice), " برای جستوجو حداقل یک فیلد باید تکمیل شود", Snackbar.LENGTH_LONG).show();
                } else {
                    serial_s = serial.getText().toString().trim();
                    mobile_s = mobile.getText().toString().trim();
                    event.sendInfoClick(serial_s,mobile_s);
                    dismiss();
                }

            }
        });
    }


    public interface VoiceDialogEvent{
        void sendInfoClick(String serial , String mobile);
    }
}
