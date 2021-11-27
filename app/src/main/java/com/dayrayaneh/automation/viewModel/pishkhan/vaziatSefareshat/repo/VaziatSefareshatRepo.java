package com.dayrayaneh.automation.viewModel.pishkhan.vaziatSefareshat.repo;

import com.dayrayaneh.automation.model.pishkhan.vaziatSefareshat.VaziatSefareshatModel;
import com.google.gson.JsonObject;

import io.reactivex.Single;


public interface VaziatSefareshatRepo {


    Single<VaziatSefareshatModel> getVaziatSefareshat(String startDate , String endDate);
}
