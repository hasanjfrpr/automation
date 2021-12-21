package com.dayrayaneh.automation.viewModel.pishkhan.tamdidQarardad.repo;

import com.dayrayaneh.automation.model.pishkhan.tamdidQarardad.TamdidGharardadModel;
import com.dayrayaneh.automation.model.pishkhan.tamdidQarardad.compare.TamdidGharardadCompareModel;
import com.dayrayaneh.automation.technology.httpclient.ApiInstance;
import com.dayrayaneh.automation.technology.httpclient.ApiService;
import com.google.gson.JsonObject;

import io.reactivex.Single;

public class TamdidGharardadRepoImpl implements TamdidGharardadRepo {

    private ApiService apiService = ApiInstance.getApiInstance();
    private JsonObject jsonObject = new JsonObject();

    @Override
    public Single<TamdidGharardadModel> getTamdidGharardad(String startDate, String endDate) {
        jsonObject.addProperty("startDate" , startDate+" 00:00:00.000");
        jsonObject.addProperty("endDate" , endDate+" 23:59:59.000");
        return apiService.getTamdidGharardad(jsonObject);
    }

    @Override
    public Single<TamdidGharardadCompareModel> getTamdidGharardadCompare(String startDate, String endDate, String startDateCompare, String endDateCompare , int noeService) {
        jsonObject.addProperty("startDate1" , startDate+" 00:00:00.000");
        jsonObject.addProperty("endDate1" , endDate+" 23:59:59.000");
        jsonObject.addProperty("startDate2" , startDateCompare+" 00:00:00.000");
        jsonObject.addProperty("endDate2" , endDateCompare+" 23:59:59.000");
        jsonObject.addProperty("contractType" , noeService);
        return apiService.getTamdidGharardadCompare(jsonObject);
    }


}
