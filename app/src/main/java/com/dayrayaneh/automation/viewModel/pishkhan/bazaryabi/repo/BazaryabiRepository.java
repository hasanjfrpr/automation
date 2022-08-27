package com.dayrayaneh.automation.viewModel.pishkhan.bazaryabi.repo;

import com.dayrayaneh.automation.model.pishkhan.pishkhan_bazaryabi.count.BazaryabiMainModel;
import com.dayrayaneh.automation.model.pishkhan.pishkhan_bazaryabi.detail.BazaryabiDetailModel;
import com.dayrayaneh.automation.model.pishkhan.pishkhan_bazaryabi.paygiri.PaygiriModel;

import io.reactivex.Single;

public interface BazaryabiRepository {

    Single<BazaryabiMainModel> getBazaryabiCount(String fromDate , String toDate , int company);

    Single<BazaryabiDetailModel> getBazaryabiDetail(String fromDate , String toDate , int personalCode);

    Single<PaygiriModel> getPaygiri(int id);
}
