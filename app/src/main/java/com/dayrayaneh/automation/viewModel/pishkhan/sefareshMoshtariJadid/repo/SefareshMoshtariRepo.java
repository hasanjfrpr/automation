package com.dayrayaneh.automation.viewModel.pishkhan.sefareshMoshtariJadid.repo;

import com.dayrayaneh.automation.model.pishkhan.sefareshMoshtariJadid.SefareshMoshtariModel;
import com.google.gson.JsonObject;

import io.reactivex.Single;
import retrofit2.http.Body;

public interface SefareshMoshtariRepo {



    Single<SefareshMoshtariModel> getSefareshMoshtariJadid(String startDate , String endDate);

}
