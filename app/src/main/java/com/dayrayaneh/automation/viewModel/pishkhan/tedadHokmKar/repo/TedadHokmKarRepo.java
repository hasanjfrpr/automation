package com.dayrayaneh.automation.viewModel.pishkhan.tedadHokmKar.repo;

import com.dayrayaneh.automation.model.pishkhan.tedadHokmKarha.count.TedadHokmKarCountModel;
import com.dayrayaneh.automation.model.pishkhan.tedadHokmKarha.details.TedadHokmKarDetailsModel;
import com.google.gson.JsonObject;

import io.reactivex.Single;
import retrofit2.http.Body;

public interface TedadHokmKarRepo {


    Single<TedadHokmKarCountModel> getTedadHokmKarCount(String startDate , String endDate );
    Single<TedadHokmKarDetailsModel> getTedadHokmKarDetail(String startDate , String endDate , int personalCode);

}
