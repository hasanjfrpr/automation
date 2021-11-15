package com.dayrayaneh.automation.viewModel.pishkhan.darsadSefareshat.repo;

import com.dayrayaneh.automation.model.pishkhan.darsadSefareshat.DarsadSefareshatModel;

import io.reactivex.Single;

public interface DarsadSefareshatRepo {




    Single<DarsadSefareshatModel> getDarsadSefareshat(String startDate , String endDate);

}
