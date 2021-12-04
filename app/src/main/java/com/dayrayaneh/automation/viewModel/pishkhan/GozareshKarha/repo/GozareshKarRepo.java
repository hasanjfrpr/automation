package com.dayrayaneh.automation.viewModel.pishkhan.GozareshKarha.repo;

import com.dayrayaneh.automation.model.pishkhan.Gozareshkar.count.GozareshKarCountModel;
import com.dayrayaneh.automation.model.pishkhan.Gozareshkar.details.GozareshKarDetailsModel;
import com.dayrayaneh.automation.model.pishkhan.Gozareshkar.personalName.PersonalListModel;
import com.google.gson.JsonObject;

import io.reactivex.Single;
import retrofit2.http.Body;


public interface GozareshKarRepo {


    Single<PersonalListModel> getPersonaList();


    Single<GozareshKarCountModel> getGozareshKarCount(String startDate , String endDate , int personId);


    Single<GozareshKarDetailsModel> getGozareshKarDetail(String startDate , String endDate , int personId , int userCode);
}
