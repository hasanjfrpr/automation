package com.dayrayaneh.automation.viewModel.pishkhan.tamdidQarardad.repo;

import com.dayrayaneh.automation.model.pishkhan.tamdidQarardad.TamdidGharardadModel;
import com.dayrayaneh.automation.model.pishkhan.tamdidQarardad.compare.TamdidGharardadCompareModel;

import io.reactivex.Single;

public interface TamdidGharardadRepo {


    Single<TamdidGharardadModel> getTamdidGharardad(String startDate , String endDate);


    Single<TamdidGharardadCompareModel> getTamdidGharardadCompare(String startDate , String endDate ,String startDateCompare , String endDateCompare , int noeService);
}
