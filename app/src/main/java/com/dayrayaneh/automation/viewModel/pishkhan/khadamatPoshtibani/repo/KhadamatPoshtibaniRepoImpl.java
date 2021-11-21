package com.dayrayaneh.automation.viewModel.pishkhan.khadamatPoshtibani.repo;

import com.dayrayaneh.automation.model.pishkhan.khadamatPoshtibani.detail.KhadamatPoshtibaniDetailModel;
import com.dayrayaneh.automation.model.pishkhan.khadamatPoshtibani.mian.KhadamatPoshtibaniMainModel;
import com.dayrayaneh.automation.services.httpclient.ApiInstance;
import com.dayrayaneh.automation.services.httpclient.ApiService;
import com.google.gson.JsonObject;

import io.reactivex.Single;

public class KhadamatPoshtibaniRepoImpl implements KhadamatPoshtibaniRepo {

    private ApiService apiService = ApiInstance.getApiInstance();
    private JsonObject jsonObject = new JsonObject();

    @Override
    public Single<KhadamatPoshtibaniMainModel> getKhadamatPoshtibaniMain(String startDate, String endDate, String startTime, String endTime, int company) {

        jsonObject.addProperty("startDate" , startDate+" "+startTime);
        jsonObject.addProperty("endDate" , endDate+" "+endTime);
        jsonObject.addProperty("company" , company);


        return apiService.getKhadamatPoshtibaniMain(jsonObject);
    }

    @Override
    public Single<KhadamatPoshtibaniDetailModel> getKhadamatPoshtibaniDetail(String startDate, String endDate, String startTime, String endTime, int userId) {
        jsonObject.addProperty("startDate" , startDate+" "+startTime);
        jsonObject.addProperty("endDate" , endDate+" "+endTime);
        jsonObject.addProperty("company" , userId);


        return apiService.getKhadamatPoshtibaniDetail(jsonObject);
    }
}
