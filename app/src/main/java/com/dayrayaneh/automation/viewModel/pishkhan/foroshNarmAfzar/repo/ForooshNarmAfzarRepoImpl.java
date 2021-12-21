package com.dayrayaneh.automation.viewModel.pishkhan.foroshNarmAfzar.repo;

import com.dayrayaneh.automation.model.pishkhan.forooshNarmAfzar.ForooshNarmAfzarModel;
import com.dayrayaneh.automation.model.pishkhan.forooshNarmAfzar.compare.ForooshNarmAfzarCompareModel;
import com.dayrayaneh.automation.technology.httpclient.ApiInstance;
import com.dayrayaneh.automation.technology.httpclient.ApiService;
import com.google.gson.JsonObject;

import io.reactivex.Single;

public class ForooshNarmAfzarRepoImpl implements ForooshNarmAfzarRepo{

    private ApiService apiService = ApiInstance.getApiInstance();
    private JsonObject jsonObject = new JsonObject();

    @Override
    public Single<ForooshNarmAfzarModel> getForooshNarmAfzar(String startDate, String endDate) {

        jsonObject.addProperty("startDate" , startDate+" 00:00:00.000");
        jsonObject.addProperty("endDate" , endDate+" 23:59:59.000");

        return apiService.getForooshNarmAfzar(jsonObject);
    }

    @Override
    public Single<ForooshNarmAfzarCompareModel> getForooshNarmAfzarCompare(String startDate, String endDate, String startDateCompare, String endDateCompare) {
        jsonObject.addProperty("startDate1" , startDate+" 00:00:00.000");
        jsonObject.addProperty("endDate1" , endDate+" 23:59:59.000");
        jsonObject.addProperty("startDate2" , startDateCompare+" 00:00:00.000");
        jsonObject.addProperty("endDate2" , endDateCompare+" 23:59:59.000");
        return apiService.getForooshNarmAfzarCompare(jsonObject);
    }
}
