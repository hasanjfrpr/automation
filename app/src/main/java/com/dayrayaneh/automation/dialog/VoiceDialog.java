package com.dayrayaneh.automation.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.speech.tts.Voice;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.service.VoiceService;

import java.io.IOException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class VoiceDialog extends DialogFragment {

    private ImageView playPause, close, next, previous;
    private TextView totalDuration, currentTime;
    private SeekBar seekBar;
    private VoiceService voiceService;
    private boolean isBound = false;
    private ProgressBar progressBar;
    private LinearLayout linearLayout;
    private boolean isCheck = true;




    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            VoiceService.LocalBinder binder = (VoiceService.LocalBinder) iBinder;
            voiceService = ((VoiceService.LocalBinder) iBinder).getService();
            voiceService.playVoice(ConstValue.uniqueIdVoice);
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_dialog_voice, null);
        builder.setView(view);
        init(view);
        event();
        progressBar.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);

//        VoiceService.isPlayLiveData.observe(this, isPlay -> {
//            if (isPlay) {
//                progressBar.setVisibility(View.GONE);
//                linearLayout.setVisibility(View.VISIBLE);
//                playPause.setImageDrawable(getContext().getDrawable(R.drawable.ic_pause));
//
//            } else {
//                progressBar.setVisibility(View.GONE);
//                linearLayout.setVisibility(View.VISIBLE);
//                playPause.setImageDrawable(getContext().getDrawable(R.drawable.ic_play));
//            }
//        });




        VoiceService.currentTimeLiveData.observe(this, current -> {
////////////////////////////////////////get total duration and set progress loadingView//////////////////
            if (isBound){
                if (voiceService.setDuration() == 0){
                    Toast.makeText(getContext(), "مکالمه ای ضبط نشده است", Toast.LENGTH_SHORT).show();
                }else {
                    int min = (voiceService.setDuration() / 1000) / 60;
                    int second = (voiceService.setDuration() / 1000) % 60;
                    if (second < 10)
                        totalDuration.setText(String.valueOf(min + ":" + "0" + second));
                    else if (min<10)
                        totalDuration.setText(String.valueOf("0"+min + ":" + second));
                    else if (min<10 && second<10)
                        totalDuration.setText(String.valueOf("0"+min + ":" +"0"+ second));
                    else totalDuration.setText(String.valueOf(min + ":" + second));

                    seekBar.setMax(voiceService.setDuration());
                }


                if (voiceService.isPlaying()) {
                    progressBar.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                    playPause.setImageDrawable(getContext().getDrawable(R.drawable.ic_pause));

                } else {
                    progressBar.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                    playPause.setImageDrawable(getContext().getDrawable(R.drawable.ic_play));
                }


            }

            ///////////////////////set current time//////////////////

            int mins = (current / 1000) / 60;
            int seconds = (current / 1000) % 60;
            if (seconds < 10 && mins>10)
                currentTime.setText(String.valueOf(mins + ":" + "0" + seconds));
            else if (mins<10 && seconds>10)
                currentTime.setText(String.valueOf("0"+mins + ":" + seconds));
            else if (mins<10 && seconds<10)
                currentTime.setText(String.valueOf("0"+mins + ":" +"0"+ seconds));
            else currentTime.setText(String.valueOf(mins + ":" + seconds));

            seekBar.setProgress(current);
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                if (isBound){
                    voiceService.seekToPos(seekBar.getProgress());
                }
            }
        });

        return builder.create();
    }


    private void init(View view) {
        playPause = view.findViewById(R.id.IV_dialog_voice_play);
        close = view.findViewById(R.id.IV_dialog_voice_close);
        next = view.findViewById(R.id.IV_dialog_voice_next);
        previous = view.findViewById(R.id.IV_dialog_voice_previous);
        totalDuration = view.findViewById(R.id.TV_dialog_voice_totalTime);
        currentTime = view.findViewById(R.id.TV_dialog_voice_currentTime);
        seekBar = view.findViewById(R.id.seekBar);
        progressBar = view.findViewById(R.id.progress_voice);
        linearLayout = view.findViewById(R.id.lineare_container_voice);

    }


    private void event() {
        close.setOnClickListener(v -> {

            if (isBound) {
                voiceService.killMedia();
            }
            dismiss();
            progressBar.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.GONE);

        });

        playPause.setOnClickListener(v -> {

            if (isBound) {
                voiceService.pausePlayVoice();
            }
        });
        next.setOnClickListener(v -> {

            if (isBound) {
                voiceService.skipForward();
            }
        });
        previous.setOnClickListener(v -> {

            if (isBound) {
                voiceService.skipBackWard();
            }
        });
    }



    @Override
    public void onStart() {
        super.onStart();
        Intent intent = new Intent(getContext(), VoiceService.class);
        getActivity().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }



    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        getActivity().unbindService(serviceConnection);
        isBound = false;
    }

}
