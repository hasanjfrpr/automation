package com.dayrayaneh.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import com.dayrayaneh.automation.base.ConstValue;
import java.util.Timer;
import java.util.TimerTask;

public class VoiceService extends Service {

    private final IBinder mBinder = new LocalBinder();
    public  MediaPlayer mediaPlayer;
    public static MutableLiveData<Integer> currentTimeLiveData = new MutableLiveData<>();
    public static MutableLiveData<Boolean> isPlayLiveData = new MutableLiveData<>();



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);

    }

    public class LocalBinder extends Binder {
        public VoiceService getService(){
            return VoiceService.this;
        }
    }

    public void playVoice(String uniqueId) {
        mediaPlayer = new MediaPlayer();
        currentTimeLiveData.setValue(0);

        String urls = "http://"+ ConstValue.ip_voice+":"+ConstValue.port_voice+"/callreport/getaudio_auto.php?uniq="+uniqueId;
        try {
            mediaPlayer.setDataSource(urls);
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (Exception e) {
            Log.d("TAG_voice", "playVoice: " + e.getMessage());
        }



        if (mediaPlayer != null) {
            if (mediaPlayer.getDuration() == 0){
                Toast.makeText(this, "صدایی ضبط نشده است", Toast.LENGTH_SHORT).show();
            }else{

                Timer timer = new Timer();


                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                                    try {
                                        if (mediaPlayer.getDuration() >= mediaPlayer.getCurrentPosition() ) {
//
                                            currentTimeLiveData.postValue(mediaPlayer.getCurrentPosition());

                                        }
                                    }catch (Exception e){
                                        Log.e("voiceException",""+e );
                                    }

                        }


                }, 0, 1000);
            }


        }



    }



    public void pausePlayVoice(){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();

        }else {
            mediaPlayer.start();

        }


    }

    public boolean isPlaying(){
        return mediaPlayer.isPlaying();
    }

    public int setDuration(){
        return mediaPlayer.getDuration();
    }


    public void seekToPos (int pos){
        mediaPlayer.seekTo(pos);
    }

    public void skipForward(){
        if (mediaPlayer.getCurrentPosition() < mediaPlayer.getDuration()-10000){
            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+10000);
//            seekBar.setProgress(mediaPlayer.getCurrentPosition());
        }
    }

    public void skipBackWard(){
        if (mediaPlayer.getCurrentPosition() > 5000){
            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-5000);
        }
    }

    public void killMedia(){
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
}
