package com.dayrayaneh.automation.viewModel.pishkhan.HokmKar.repo;

import com.dayrayaneh.automation.model.pishkhan.HokmKar.HokmKarModel;
import com.dayrayaneh.automation.model.pishkhan.HokmKar.followers.HokmKarFollowersModel;
import com.dayrayaneh.automation.model.pishkhan.HokmKar.request.HokmKarRequestModel;
import com.google.gson.JsonObject;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HokmKarRepo {


    Single<HokmKarModel> getHokmKar(String startDate , String endDate);


    Single<HokmKarRequestModel> getHokmKarRequest(int userId);


    Single<HokmKarFollowersModel> getHokmKarFollowers(int userId);
}
