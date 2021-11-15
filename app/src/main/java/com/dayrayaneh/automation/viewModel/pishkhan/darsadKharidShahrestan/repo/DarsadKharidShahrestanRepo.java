package com.dayrayaneh.automation.viewModel.pishkhan.darsadKharidShahrestan.repo;

import com.dayrayaneh.automation.model.pishkhan.darsadKharidMoshtari.DarsadkharidMoshtariModel;
import com.dayrayaneh.automation.model.pishkhan.darsadKharidShahrestan.DarsadKharidShahrestanModel;

import io.reactivex.Single;

public interface DarsadKharidShahrestanRepo {

    Single<DarsadKharidShahrestanModel> getDarsadKharidShahrestan(String startDate , String EndDate , int productType);

}
