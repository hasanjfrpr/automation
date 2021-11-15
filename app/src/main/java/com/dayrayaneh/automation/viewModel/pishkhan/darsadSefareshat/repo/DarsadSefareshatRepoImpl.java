package com.dayrayaneh.automation.viewModel.pishkhan.darsadSefareshat.repo;

import com.dayrayaneh.automation.model.pishkhan.darsadSefareshat.DarsadSefareshatModel;
import com.dayrayaneh.automation.services.httpclient.ApiInstance;
import com.dayrayaneh.automation.services.httpclient.ApiService;
import com.google.gson.JsonObject;

import io.reactivex.Single;

public class DarsadSefareshatRepoImpl implements  DarsadSefareshatRepo{

   private ApiService apiService = ApiInstance.getApiInstance();
   private JsonObject jsonObject = new JsonObject() ;

    @Override
    public Single<DarsadSefareshatModel> getDarsadSefareshat(String startDate, String endDate) {
        jsonObject.addProperty("startDate" ,startDate+" 00:00:00.000");
        jsonObject.addProperty("endDate" ,endDate+" 23:59:59.000");
        return apiService.getDarsadSefareshat(jsonObject);
    }
}
