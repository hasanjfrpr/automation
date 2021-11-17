package com.dayrayaneh.automation.viewModel.pishkhan.forooshSakhtAfzar.repo;

import com.dayrayaneh.automation.model.pishkhan.forooshSakhtAfzar.ForooshSakhtAfzarModel;

import java.util.List;

import io.reactivex.Single;

public interface ForooshSakhtAfzarRepo {

    Single<ForooshSakhtAfzarModel> getForooshSakhtAfzar(String startDate , String endDate ,List<Integer> categories);
}
