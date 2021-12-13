package com.dayrayaneh.automation.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.base.ConstValue;

import java.io.IOException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class VoiceDialog extends DialogFragment {

    private ImageView playPause, close, next, previous;
    private TextView totalDuration, currentTime;
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_dialog_voice, null);
        builder.setView(view);
        init(view);
        event();
        playVoice(ConstValue.uniqueIdVoice);
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

    }

    public void playVoice(String url) {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("http://192.168.0.7/callreport/getaudio_auto.php?uniq="+url);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            Log.d("TAG_voice", "playVoice: " + e.getMessage());
        }


        if (mediaPlayer != null) {
            if (mediaPlayer.getDuration() == 0){
                Toast.makeText(getContext(), "صدایی ضبط نشده است", Toast.LENGTH_SHORT).show();
            }else{
                int min = (mediaPlayer.getDuration() / 1000) / 60;
                int second = (mediaPlayer.getDuration() / 1000) % 60;
                if (second < 10)
                    currentTime.setText(String.valueOf(min + ":" + "0" + second));
                else currentTime.setText(String.valueOf(min + ":" + second));

                seekBar.setMax(mediaPlayer.getDuration());
                totalDuration.setText(String.valueOf(min + ":" + second));

                Timer timer = new Timer();


                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (getActivity() == null) {
                            return;
                        } else {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (mediaPlayer.getDuration() >= mediaPlayer.getCurrentPosition()) {
                                        int mins = (mediaPlayer.getCurrentPosition() / 1000) / 60;
                                        int seconds = (mediaPlayer.getCurrentPosition() / 1000) % 60;
                                        if (seconds < 10)
                                            currentTime.setText(String.valueOf(mins + ":" + "0" + seconds));
                                        else currentTime.setText(String.valueOf(mins + ":" + seconds));
                                        seekBar.setProgress(mediaPlayer.getCurrentPosition());

                                    }
                                }
                            });
                        }

                    }
                }, 0, 1000);
            }


        }







        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
    {
        @Override
        public void onProgressChanged (SeekBar seekBar,int progress, boolean fromUser){
    }

        @Override
        public void onStartTrackingTouch (SeekBar seekBar){

    }

        @Override
        public void onStopTrackingTouch (SeekBar seekBar){
        mediaPlayer.seekTo(seekBar.getProgress());
    }
    });

    }

    public void pausePlayVoice(){
        if (mediaPlayer.isPlaying()){
            playPause.setImageDrawable(getContext().getDrawable(R.drawable.ic_play));
            mediaPlayer.pause();
        }else {
            playPause.setImageDrawable(getContext().getDrawable(R.drawable.ic_pause));
            mediaPlayer.start();
        }


    }

    public void skipForward(){
        if (mediaPlayer.getCurrentPosition() < mediaPlayer.getDuration()-10000){
        mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+10000);
        seekBar.setProgress(mediaPlayer.getCurrentPosition());}
    }

    public void skipBackWard(){
        if (mediaPlayer.getCurrentPosition() > 5000){
        mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-5000);
        seekBar.setProgress(mediaPlayer.getCurrentPosition());}
    }

    private void killMedia(){
        if (mediaPlayer != null){
            if (mediaPlayer.isPlaying()){
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }else {
                mediaPlayer.release();
                mediaPlayer =null;
            }

        }
    }

    private void event() {
        close.setOnClickListener(v -> {
            killMedia();
            dismiss();
        });

        playPause.setOnClickListener(v->{
            pausePlayVoice();
        });
        next.setOnClickListener(v->{
            skipForward();
        });
        previous.setOnClickListener(v->{
            skipBackWard();
        });
    }

}
