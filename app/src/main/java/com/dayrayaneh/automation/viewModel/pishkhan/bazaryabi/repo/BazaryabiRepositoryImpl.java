package com.dayrayaneh.automation.viewModel.pishkhan.bazaryabi.repo;


import com.dayrayaneh.automation.model.pishkhan.pishkhan_bazaryabi.count.BazaryabiMainModel;
import com.dayrayaneh.automation.model.pishkhan.pishkhan_bazaryabi.detail.BazaryabiDetailModel;
import com.dayrayaneh.automation.services.httpclient.ApiInstance;
import com.dayrayaneh.automation.services.httpclient.ApiService;
import com.google.gson.JsonObject;

import io.reactivex.Single;

public class BazaryabiRepositoryImpl implements BazaryabiRepository{

    private ApiService apiService = ApiInstance.getApiInstance();
    private JsonObject jsonObject;


    @Override
    public Single<BazaryabiMainModel> getBazaryabiCount(String fromDate, String toDate, int company) {
        jsonObject = new JsonObject();
        jsonObject.addProperty("startDate" ,fromDate+" 00:00:00.000");
        jsonObject.addProperty("endDate" , toDate+" 23:59:59.000");
        jsonObject.addProperty("company" , company);
        return apiService.getBazaryabiCount(jsonObject);
    }

    @Override
    public Single<BazaryabiDetailModel> getBazaryabiDetail(String fromDate, String toDate, int personalCode) {
        jsonObject = new JsonObject();
        jsonObject.addProperty("startDate" ,fromDate+" 00:00:00.000");
        jsonObject.addProperty("endDate" , toDate+" 23:59:59.000");
        jsonObject.addProperty("UserCode" , personalCode);
        return apiService.getBazaryabiDetail(jsonObject);
    }
}
