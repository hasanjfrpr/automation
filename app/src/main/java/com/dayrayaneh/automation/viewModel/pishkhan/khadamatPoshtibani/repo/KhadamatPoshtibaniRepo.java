package com.dayrayaneh.automation.viewModel.pishkhan.khadamatPoshtibani.repo;

import com.dayrayaneh.automation.model.pishkhan.khadamatPoshtibani.detail.KhadamatPoshtibaniDetailModel;
import com.dayrayaneh.automation.model.pishkhan.khadamatPoshtibani.mian.KhadamatPoshtibaniMainModel;
import com.google.gson.JsonObject;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface KhadamatPoshtibaniRepo {




    Single<KhadamatPoshtibaniMainModel> getKhadamatPoshtibaniMain(String startDate ,String endDate , String startTime , String endTime , int company);

    Single<KhadamatPoshtibaniDetailModel> getKhadamatPoshtibaniDetail(String startDate ,String endDate , String startTime , String endTime , int userId);


}
