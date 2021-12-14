package com.dayrayaneh.automation.viewModel.pishkhan.Voice.repo;

import com.dayrayaneh.automation.model.pishkhan.voicePoshtibani.VoiceModel;
import com.dayrayaneh.automation.services.httpclient.ApiInstance;
import com.dayrayaneh.automation.services.httpclient.ApiService;
import com.google.gson.JsonObject;

import io.reactivex.Single;

public class VoiceRepoImpl implements VoiceRepo{

    private JsonObject jsonObject;
    private ApiService apiService ;

    @Override
    public Single<VoiceModel> getVoice(String startDate, String endDate, String serial, String mobile) {
        jsonObject = new JsonObject();
        jsonObject.addProperty("startDate" , startDate+" 00:00:00.000");
        jsonObject.addProperty("endDate" , endDate+" 23:59:59.000");
        jsonObject.addProperty("serial" , serial);
        jsonObject.addProperty("mobile" , mobile);
        apiService = ApiInstance.getApiInstance();
        return apiService.getVoice(jsonObject);
    }
}
