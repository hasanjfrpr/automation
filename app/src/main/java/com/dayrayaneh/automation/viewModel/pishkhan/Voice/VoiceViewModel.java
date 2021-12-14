package com.dayrayaneh.automation.viewModel.pishkhan.Voice;

import android.speech.tts.Voice;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dayrayaneh.automation.base.AutomationSingleObserver;
import com.dayrayaneh.automation.base.BaseViewModel;
import com.dayrayaneh.automation.model.pishkhan.voicePoshtibani.VoiceModel;
import com.dayrayaneh.automation.viewModel.pishkhan.Voice.repo.VoiceRepo;
import com.dayrayaneh.automation.viewModel.pishkhan.Voice.repo.VoiceRepoImpl;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class VoiceViewModel extends BaseViewModel {

    public MutableLiveData<VoiceModel> voiceLiveData = new MutableLiveData<>();
    private VoiceRepo repo = new VoiceRepoImpl();


    public void getVoice(String startDate , String endDate , String serial ,String mobile){
        repo.getVoice(startDate  , endDate , serial , mobile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutomationSingleObserver<VoiceModel>(compositeDisposable) {
                    @Override
                    public void onSuccess(@NonNull VoiceModel voiceModel) {
                        voiceLiveData.setValue(voiceModel);
                    }
                });

    }
}
