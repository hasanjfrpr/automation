package com.dayrayaneh.automation.viewModel.pishkhan.tamdidQarardad.repo;

import com.dayrayaneh.automation.model.pishkhan.tamdidQarardad.TamdidGharardadModel;
import com.dayrayaneh.automation.services.httpclient.ApiInstance;
import com.dayrayaneh.automation.services.httpclient.ApiService;
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
}
