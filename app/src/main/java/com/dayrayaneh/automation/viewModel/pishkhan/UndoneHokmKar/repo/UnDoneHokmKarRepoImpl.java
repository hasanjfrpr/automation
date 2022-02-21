package com.dayrayaneh.automation.viewModel.pishkhan.UndoneHokmKar.repo;

import com.dayrayaneh.automation.model.pishkhan.UnDoneHokmKar.Count.UnDoneHokmkarCountMode;
import com.dayrayaneh.automation.model.pishkhan.UnDoneHokmKar.UnDoneHokmKarModel;
import com.dayrayaneh.automation.technology.httpclient.ApiInstance;
import com.dayrayaneh.automation.technology.httpclient.ApiService;
import com.google.gson.JsonObject;

import io.reactivex.Single;

public class UnDoneHokmKarRepoImpl implements UnDoneHokmKarRepo {

    private ApiService apiService;
    private JsonObject jsonObject;

    @Override
    public Single<UnDoneHokmKarModel> getUnDoneHokmKar(String startDate, String endDate, int personId) {
        apiService = ApiInstance.getApiInstance();
        jsonObject = new JsonObject();
        jsonObject.addProperty("startDate", startDate + " 00:00:00.000");
        jsonObject.addProperty("endDate", endDate + " 23:59:59.000");
        jsonObject.addProperty("personId", personId);
        return apiService.getUnDoneHokmKar(jsonObject);
    }

    @Override
    public Single<UnDoneHokmkarCountMode> getUnDoneHokmKarCount(String startDate, String endDate) {
        apiService = ApiInstance.getApiInstance();
        jsonObject = new JsonObject();
        jsonObject.addProperty("startDate", startDate + " 00:00:00.000");
        jsonObject.addProperty("endDate", endDate + " 23:59:59.000");
        return apiService.getUndonHokmKarCount(jsonObject);
    }
}
