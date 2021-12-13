package com.dayrayaneh.automation.viewModel.pishkhan.darsadKharidMoshtari.repo;

import com.dayrayaneh.automation.model.pishkhan.darsadKharidMoshtari.DarsadkharidMoshtariModel;
import com.dayrayaneh.automation.model.pishkhan.darsadKharidMoshtari.details.DarsadKharidMoshtariDetailsModel;
import com.dayrayaneh.automation.view.pishkhanItemView.darsadKhardiMoshtari.DarsadKharidMoshtariDetailsActivity;

import io.reactivex.Single;

public interface DarsadKharidMoshtariRepo {

    Single<DarsadkharidMoshtariModel> getDarsadKharidMoshtari(String startDate , String EndDate , int productType);

    Single<DarsadKharidMoshtariDetailsModel> getDarsadKharidMoshtariDetails(String startDate , String EndDate , int productType , int moshtariId);


}
