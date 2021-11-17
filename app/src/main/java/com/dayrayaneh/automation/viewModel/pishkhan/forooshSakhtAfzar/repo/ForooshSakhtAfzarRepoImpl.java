package com.dayrayaneh.automation.viewModel.pishkhan.forooshSakhtAfzar.repo;

import com.dayrayaneh.automation.model.pishkhan.forooshSakhtAfzar.ForooshSakhtAfzarModel;
import com.dayrayaneh.automation.services.httpclient.ApiInstance;
import com.dayrayaneh.automation.services.httpclient.ApiService;
import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.Single;

public class ForooshSakhtAfzarRepoImpl implements ForooshSakhtAfzarRepo {

    private ApiService apiService = ApiInstance.getApiInstance();
    private JsonObject jsonObject = new JsonObject();

    @Override
    public Single<ForooshSakhtAfzarModel> getForooshSakhtAfzar(String startDate, String endDate, List<Integer> categories) {
        jsonObject.addProperty("startDate" , startDate+" 00:00:00.000");
        jsonObject.addProperty("startDate" , endDate+" 23:59:59.000");
        jsonObject.addProperty("categories" , String.valueOf(categories));
        return apiService.getForooshSakhtAfzar(jsonObject);
    }
}
