package com.dayrayaneh.automation.viewModel.pishkhan.Voice.repo;

import com.dayrayaneh.automation.model.pishkhan.voicePoshtibani.VoiceModel;
import com.google.gson.JsonObject;

import io.reactivex.Single;
import retrofit2.http.Body;

public interface VoiceRepo {

    Single<VoiceModel> getVoice(String startDate , String endDate , String serial ,String mobile);
}
