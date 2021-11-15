package com.dayrayaneh.automation.viewModel.pishkhan.darsadTakhfifAzHarSefaresh.repo;

import com.dayrayaneh.automation.model.pishkhan.darsadThakhfifAzHarSefaresh.DarsadTakhfifAzHarSefareshModel;
import com.google.gson.JsonObject;

import io.reactivex.Single;
import retrofit2.http.Body;

public interface DarsadTakhfifAzHarSefareshRepo {


    Single<DarsadTakhfifAzHarSefareshModel> getDarsadTakhfifAzHarSefaresh(String startDate , String endDate);
}
