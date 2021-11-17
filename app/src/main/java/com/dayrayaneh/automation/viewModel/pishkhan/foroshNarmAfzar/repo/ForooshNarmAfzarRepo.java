package com.dayrayaneh.automation.viewModel.pishkhan.foroshNarmAfzar.repo;

import com.dayrayaneh.automation.model.pishkhan.forooshNarmAfzar.ForooshNarmAfzarModel;
import com.dayrayaneh.automation.model.pishkhan.forooshNarmAfzar.compare.ForooshNarmAfzarCompareModel;
import com.google.gson.JsonObject;

import io.reactivex.Single;
import retrofit2.http.Body;

public interface ForooshNarmAfzarRepo {


    Single<ForooshNarmAfzarModel> getForooshNarmAfzar(String startDate , String endDate);


    Single<ForooshNarmAfzarCompareModel> getForooshNarmAfzarCompare(String startDate , String endDate ,String startDateCompare , String endDateCompare);
}
