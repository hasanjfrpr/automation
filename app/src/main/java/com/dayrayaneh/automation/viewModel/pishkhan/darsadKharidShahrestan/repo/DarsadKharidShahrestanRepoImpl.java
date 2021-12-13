package com.dayrayaneh.automation.viewModel.pishkhan.darsadKharidShahrestan.repo;

import com.dayrayaneh.automation.model.pishkhan.darsadKharidMoshtari.DarsadkharidMoshtariModel;
import com.dayrayaneh.automation.model.pishkhan.darsadKharidShahrestan.DarsadKharidShahrestanModel;
import com.dayrayaneh.automation.model.pishkhan.darsadKharidShahrestan.details.DarsadKharidShahrestanDetailsModel;
import com.dayrayaneh.automation.services.httpclient.ApiInstance;
import com.dayrayaneh.automation.services.httpclient.ApiService;
import com.google.gson.JsonObject;

import io.reactivex.Single;

public class DarsadKharidShahrestanRepoImpl implements DarsadKharidShahrestanRepo{


    private ApiService apiService = ApiInstance.getApiInstance();
    private JsonObject jsonObject = new JsonObject() ;


    @Override
    public Single<DarsadKharidShahrestanModel> getDarsadKharidShahrestan(String startDate, String EndDate, int productType) {
        jsonObject.addProperty("startDate" , startDate+" 00:00:00.000");
        jsonObject.addProperty("endDate" , EndDate+" 23:59:59.000");
        jsonObject.addProperty("ProductType" , productType);
        return apiService.getDarsadKharidShahrestan(jsonObject);
    }

    @Override
    public Single<DarsadKharidShahrestanDetailsModel> getDarsadKharidShahrestanDetails(String startDate, String EndDate, int productType, int cityId) {
        jsonObject.addProperty("startDate" , startDate+" 00:00:00.000");
        jsonObject.addProperty("endDate" , EndDate+" 23:59:59.000");
        jsonObject.addProperty("ProductType" , productType);
        jsonObject.addProperty("cityId" , cityId);
        return apiService.getDarsadKharidShahrestanDetails(jsonObject);

    }


}
