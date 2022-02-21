package com.dayrayaneh.automation.viewModel.pishkhan.UndoneHokmKar.repo;

import com.dayrayaneh.automation.model.pishkhan.UnDoneHokmKar.Count.UnDoneHokmkarCountMode;
import com.dayrayaneh.automation.model.pishkhan.UnDoneHokmKar.UnDoneHokmKarModel;

import io.reactivex.Single;

public interface UnDoneHokmKarRepo {

    Single<UnDoneHokmKarModel> getUnDoneHokmKar(String startDate , String endDate , int personId);
    Single<UnDoneHokmkarCountMode> getUnDoneHokmKarCount(String startDate , String endDate);
}
