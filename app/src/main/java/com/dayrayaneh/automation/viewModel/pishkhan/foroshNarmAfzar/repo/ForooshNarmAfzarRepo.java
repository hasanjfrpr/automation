package com.dayrayaneh.automation.viewModel.pishkhan.foroshNarmAfzar.repo;

import com.dayrayaneh.automation.model.pishkhan.forooshNarmAfzar.ForooshNarmAfzarModel;
import com.google.gson.JsonObject;

import io.reactivex.Single;
import retrofit2.http.Body;

public interface ForooshNarmAfzarRepo {


    Single<ForooshNarmAfzarModel> getForooshNarmAfzar(String startDate , String endDate);
}
