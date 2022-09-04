package com.dayrayaneh.automation.viewModel.pishkhan.lidha;

import com.dayrayaneh.automation.model.pishkhan.lidModel.LidModel;

import io.reactivex.Single;

public interface LidRepository {

    Single<LidModel> getLids(String startDate , String endDate , int company);

}
