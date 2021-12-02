package com.dayrayaneh.automation.viewModel.pishkhan.tedadHokmKar.repo;

import com.dayrayaneh.automation.model.pishkhan.tedadHokmKarha.count.TedadHokmKarCountModel;
import com.dayrayaneh.automation.model.pishkhan.tedadHokmKarha.details.TedadHokmKarDetailsModel;
import com.dayrayaneh.automation.services.httpclient.ApiInstance;
import com.dayrayaneh.automation.services.httpclient.ApiService;
import com.google.gson.JsonObject;

import io.reactivex.Single;

public class TedadHokmKarRepoImpl implements TedadHokmKarRepo{


    private ApiService apiService = ApiInstance.getApiInstance();
    private JsonObject jsonObject = new JsonObject();


    @Override
    public Single<TedadHokmKarCountModel> getTedadHokmKarCount(String startDate, String endDate) {
        jsonObject.addProperty("startDate" , startDate+" 00:00:00.000");
        jsonObject.addProperty("endDate" , endDate+" 23:59:59.000");
        return apiService.getTedadHokmKarCount(jsonObject);
    }

    @Override
    public Single<TedadHokmKarDetailsModel> getTedadHokmKarDetail(String startDate, String endDate, int personalCode) {
        jsonObject.addProperty("startDate" , startDate+" 00:00:00.000");
        jsonObject.addProperty("endDate" , endDate+" 23:59:59.000");
        jsonObject.addProperty("PersonCode" , personalCode);
        return apiService.getTedadHokmKarDetail(jsonObject);
    }
}
