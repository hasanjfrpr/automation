package com.dayrayaneh.automation.viewModel.pishkhan.forooshSakhtAfzar.repo;

import com.dayrayaneh.automation.model.pishkhan.forooshSakhtAfzar.ForooshSakhtAfzarModel;
import com.dayrayaneh.automation.model.pishkhan.forooshSakhtAfzar.compare.ForooshSakhtAfzarCompareModel;
import com.dayrayaneh.automation.model.pishkhan.forooshSakhtAfzar.productCategories.ProductCategories;
import com.dayrayaneh.automation.technology.httpclient.ApiInstance;
import com.dayrayaneh.automation.technology.httpclient.ApiService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.Single;

public class ForooshSakhtAfzarRepoImpl implements ForooshSakhtAfzarRepo {

    private ApiService apiService = ApiInstance.getApiInstance();
    private JsonObject jsonObject = new JsonObject();

    @Override
    public Single<ForooshSakhtAfzarModel> getForooshSakhtAfzar(String startDate, String endDate, List<Integer> categories) {
        jsonObject.addProperty("startDate" , startDate+" 00:00:00.000");
        jsonObject.addProperty("endDate" , endDate+" 23:59:59.000");

        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < categories.size(); i++) {
            jsonArray.add(categories.get(i));
        }
        jsonObject.add("categories",jsonArray);
        return apiService.getForooshSakhtAfzar(jsonObject);
    }

    @Override
    public Single<ProductCategories> getProductCategories() {
        return apiService.getProductCategories();
    }

    @Override
    public Single<ForooshSakhtAfzarCompareModel> getForooshSakhtAfzarCompare(String startDate, String endDate, String startDate2, String endDate2, List<Integer> categories) {
        jsonObject.addProperty("startDate1" , startDate+" 00:00:00.000");
        jsonObject.addProperty("endDate1" , endDate+" 23:59:59.000");
        jsonObject.addProperty("startDate2" , startDate2+" 00:00:00.000");
        jsonObject.addProperty("endDate2" , endDate2+" 23:59:59.000");

        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < categories.size(); i++) {
            jsonArray.add(categories.get(i));
        }
        jsonObject.add("categories",jsonArray);
        return apiService.getForooshSakhtAfzarCompare(jsonObject);
    }
}
