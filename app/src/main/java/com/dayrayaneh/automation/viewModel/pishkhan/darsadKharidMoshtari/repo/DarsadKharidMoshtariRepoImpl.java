package com.dayrayaneh.automation.viewModel.pishkhan.darsadKharidMoshtari.repo;

import com.dayrayaneh.automation.model.pishkhan.darsadKharidMoshtari.DarsadkharidMoshtariModel;
import com.dayrayaneh.automation.services.httpclient.ApiInstance;
import com.dayrayaneh.automation.services.httpclient.ApiService;
import com.google.gson.JsonObject;

import io.reactivex.Single;

public class DarsadKharidMoshtariRepoImpl implements DarsadKharidMoshtariRepo{

    private ApiService apiService = ApiInstance.getApiInstance();
    private JsonObject jsonObject = new JsonObject() ;

    @Override
    public Single<DarsadkharidMoshtariModel> getDarsadKharidMoshtari(String startDate, String endDate, int productType) {
        jsonObject.addProperty("startDate" , startDate+" 00:00:00.000");
        jsonObject.addProperty("endDate" , endDate+" 23:59:59.000");
        jsonObject.addProperty("ProductType" , productType);
        return apiService.getDarsadKharidMoshtari(jsonObject);
    }
}
