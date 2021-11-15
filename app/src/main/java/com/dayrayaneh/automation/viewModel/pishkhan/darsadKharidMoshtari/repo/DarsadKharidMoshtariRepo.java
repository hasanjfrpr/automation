package com.dayrayaneh.automation.viewModel.pishkhan.darsadKharidMoshtari.repo;

import com.dayrayaneh.automation.model.pishkhan.darsadKharidMoshtari.DarsadkharidMoshtariModel;

import io.reactivex.Single;

public interface DarsadKharidMoshtariRepo {

    Single<DarsadkharidMoshtariModel> getDarsadKharidMoshtari(String startDate , String EndDate , int productType);
}
