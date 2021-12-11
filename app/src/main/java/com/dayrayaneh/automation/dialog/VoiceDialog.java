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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.dayrayaneh.automation.R;

import java.io.IOException;

public class VoiceDialog extends DialogFragment {

    private ImageView playPause , close , next , previous;
    private TextView totalDuration , currentTime;
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private Handler handler;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder =new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_dialog_voice,null);
        builder.setView(view);
        init(view);
        event();
        return builder.create();
    }


    private void init(View view){
        playPause = view.findViewById(R.id.IV_dialog_voice_play);
        close = view.findViewById(R.id.IV_dialog_voice_close);
        next = view.findViewById(R.id.IV_dialog_voice_next);
        previous = view.findViewById(R.id.IV_dialog_voice_previous);
        totalDuration = view.findViewById(R.id.TV_dialog_voice_totalTime);
        currentTime = view.findViewById(R.id.TV_dialog_voice_currentTime);
        seekBar = view.findViewById(R.id.seekBar);

    }

    public void playVoice(String url)  {
        Uri uri = Uri.parse("https://irsv.upmusics.com/99/Mohsen%20Yeganeh%20-%20Shahr%20Khakestari%20%20(128).mp3");
       mediaPlayer = new MediaPlayer();
        mediaPlayer.reset();

        try {
            mediaPlayer.setDataSource("http://192.168.0.7/callreport/getaudio_auto.php?uniq=1639032036");
            mediaPlayer.prepare();
        } catch (IOException e) {
            Log.d("TAG_voice", "playVoice: "+e.getMessage());
        }
        mediaPlayer.start();
//       if (mediaPlayer != null){
//            mediaPlayer.stop();
//            mediaPlayer.release();
//        }else{
//            try {
//                mediaPlayer.prepare();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//           seekBar.setMax(mediaPlayer.getDuration());
//           mediaPlayer.start();
//           totalDuration.setText(mediaPlayer.getDuration());
//
//           handler = new Handler();
//           handler.postDelayed(new Runnable() {
//               @Override
//               public void run() {
//                   currentTime.setText(mediaPlayer.getCurrentPosition());
//               }
//           },1000);
//        }
//


    }

    public void pausePlayVoice(){
//        if (mediaPlayer.isPlaying()){
//            playPause.setImageDrawable(getContext().getDrawable(R.drawable.ic_play));
//            mediaPlayer.pause();
//        }else {
//            playPause.setImageDrawable(getContext().getDrawable(R.drawable.ic_pause));
//            mediaPlayer.start();
//        }
        playVoice("");

    }

    public void skipForward(){
        if (mediaPlayer.getCurrentPosition() < mediaPlayer.getDuration()-5000){
        mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+5000);
        seekBar.setProgress(mediaPlayer.getCurrentPosition());}
    }

    public void skipBackWard(){
        if (mediaPlayer.getCurrentPosition() > 5000){
        mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-5000);
        seekBar.setProgress(mediaPlayer.getCurrentPosition());}
    }


    private void event() {
        close.setOnClickListener(v -> {
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
