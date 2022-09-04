package com.dayrayaneh.automation.viewModel.pishkhan.lidha;

import com.dayrayaneh.automation.model.pishkhan.lidModel.LidModel;
import com.dayrayaneh.automation.technology.httpclient.ApiInstance;
import com.dayrayaneh.automation.technology.httpclient.ApiService;
import com.google.gson.JsonObject;

import io.reactivex.Single;

public class LidRepositoryImpl implements LidRepository{

   private  ApiService apiService = ApiInstance.getApiInstance();
    private JsonObject jsonObject;

    @Override
    public Single<LidModel> getLids(String startDate, String endDate, int company) {
        jsonObject = new JsonObject();
        jsonObject.addProperty("startDate" , startDate+" 00:00:00.000");
        jsonObject.addProperty("endDate" , endDate+" 23:59:59.000");
        jsonObject.addProperty("company" , company);
        return apiService.getLid(jsonObject);
    }
}
