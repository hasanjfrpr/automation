package com.dayrayaneh.automation.viewModel.pishkhan.HokmKar.repo;

import com.dayrayaneh.automation.model.pishkhan.HokmKar.HokmKarModel;
import com.dayrayaneh.automation.model.pishkhan.HokmKar.followers.HokmKarFollowersModel;
import com.dayrayaneh.automation.model.pishkhan.HokmKar.request.HokmKarRequestModel;
import com.dayrayaneh.automation.services.httpclient.ApiInstance;
import com.dayrayaneh.automation.services.httpclient.ApiService;
import com.google.gson.JsonObject;

import io.reactivex.Single;

public class HokmKarRepoImpl implements HokmKarRepo{

    private ApiService apiService = ApiInstance.getApiInstance();
    private JsonObject jsonObject = new JsonObject();

    @Override
    public Single<HokmKarModel> getHokmKar(String startDate, String endDate) {
        jsonObject.addProperty("startDate" , startDate+" 00:00:00.000");
        jsonObject.addProperty("endDate" , endDate+" 23:59:59.000");
        return apiService.getHokmKar(jsonObject);
    }

    @Override
    public Single<HokmKarRequestModel> getHokmKarRequest(int userId) {
        jsonObject.addProperty("HokmKarId" , userId);
        return apiService.getHokmKarRequestModel(jsonObject);
    }

    @Override
    public Single<HokmKarFollowersModel> getHokmKarFollowers(int userId) {
        jsonObject.addProperty("HokmKarId" , userId);
        return apiService.getHokmKarFollowersModel(jsonObject);
    }
}
