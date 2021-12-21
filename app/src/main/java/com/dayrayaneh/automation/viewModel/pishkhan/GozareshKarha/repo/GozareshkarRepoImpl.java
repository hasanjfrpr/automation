package com.dayrayaneh.automation.viewModel.pishkhan.GozareshKarha.repo;

import com.dayrayaneh.automation.model.pishkhan.Gozareshkar.count.GozareshKarCountModel;
import com.dayrayaneh.automation.model.pishkhan.Gozareshkar.details.GozareshKarDetailsModel;
import com.dayrayaneh.automation.model.pishkhan.Gozareshkar.personalName.PersonalListModel;
import com.dayrayaneh.automation.technology.httpclient.ApiInstance;
import com.dayrayaneh.automation.technology.httpclient.ApiService;
import com.google.gson.JsonObject;

import io.reactivex.Single;

public class GozareshkarRepoImpl implements GozareshKarRepo{

    private ApiService apiService = ApiInstance.getApiInstance();
    private JsonObject jsonObject = new JsonObject();

    @Override
    public Single<PersonalListModel> getPersonaList() {
        return apiService.getPersonaList();
    }

    @Override
    public Single<GozareshKarCountModel> getGozareshKarCount(String startDate, String endDate, int personId) {
        jsonObject.addProperty("startDate" , startDate+" 00:00:00.000");
        jsonObject.addProperty("endDate" , endDate+" 23:59:59.000");
        jsonObject.addProperty("personId" , personId);
        return apiService.getGozareshKarCount(jsonObject);
    }

    @Override
    public Single<GozareshKarDetailsModel> getGozareshKarDetail(String startDate, String endDate, int personId, int userCode) {
        jsonObject.addProperty("startDate" , startDate+" 00:00:00.000");
        jsonObject.addProperty("endDate" , endDate+" 23:59:59.000");
        jsonObject.addProperty("personId" , personId);
        jsonObject.addProperty("userCode" , userCode);
        return apiService.getGozareshKarDetail(jsonObject);
    }
}
