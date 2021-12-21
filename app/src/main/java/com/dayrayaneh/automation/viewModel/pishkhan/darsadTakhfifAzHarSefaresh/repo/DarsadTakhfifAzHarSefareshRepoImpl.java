package com.dayrayaneh.automation.viewModel.pishkhan.darsadTakhfifAzHarSefaresh.repo;

import com.dayrayaneh.automation.model.pishkhan.darsadThakhfifAzHarSefaresh.DarsadTakhfifAzHarSefareshModel;
import com.dayrayaneh.automation.technology.httpclient.ApiInstance;
import com.dayrayaneh.automation.technology.httpclient.ApiService;
import com.google.gson.JsonObject;

import io.reactivex.Single;

public class DarsadTakhfifAzHarSefareshRepoImpl implements DarsadTakhfifAzHarSefareshRepo{


    private ApiService apiService = ApiInstance.getApiInstance();
    private JsonObject jsonObject = new JsonObject();


    @Override
    public Single<DarsadTakhfifAzHarSefareshModel> getDarsadTakhfifAzHarSefaresh(String startDate, String endDate) {
        jsonObject.addProperty("startDate", startDate+" 00:00:00.000");
        jsonObject.addProperty("endDate", endDate+" 23:59:59.000");
        return apiService.getDarsadTakhfifAzHarSefaresh(jsonObject);
    }
}
