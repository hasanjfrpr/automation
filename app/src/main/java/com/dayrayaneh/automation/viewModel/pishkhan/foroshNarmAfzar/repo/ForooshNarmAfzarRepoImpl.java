package com.dayrayaneh.automation.viewModel.pishkhan.foroshNarmAfzar.repo;

import com.dayrayaneh.automation.model.pishkhan.forooshNarmAfzar.ForooshNarmAfzarModel;
import com.dayrayaneh.automation.services.httpclient.ApiInstance;
import com.dayrayaneh.automation.services.httpclient.ApiService;
import com.google.gson.JsonObject;
import com.google.protobuf.Api;

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
}
