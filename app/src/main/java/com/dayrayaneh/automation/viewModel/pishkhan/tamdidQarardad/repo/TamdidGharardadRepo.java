package com.dayrayaneh.automation.viewModel.pishkhan.tamdidQarardad.repo;

import com.dayrayaneh.automation.model.pishkhan.tamdidQarardad.TamdidGharardadModel;

import io.reactivex.Single;

public interface TamdidGharardadRepo {


    Single<TamdidGharardadModel> getTamdidGharardad(String startDate , String endDate);
}
