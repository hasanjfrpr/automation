package com.dayrayaneh.automation.viewModel.pishkhan.forooshSakhtAfzar.repo;

import com.dayrayaneh.automation.model.pishkhan.forooshSakhtAfzar.ForooshSakhtAfzarModel;
import com.dayrayaneh.automation.model.pishkhan.forooshSakhtAfzar.compare.ForooshSakhtAfzarCompareModel;
import com.dayrayaneh.automation.model.pishkhan.forooshSakhtAfzar.productCategories.ProductCategories;

import java.util.List;

import io.reactivex.Single;

public interface ForooshSakhtAfzarRepo {

    Single<ForooshSakhtAfzarModel> getForooshSakhtAfzar(String startDate , String endDate ,List<Integer> categories);

    Single<ProductCategories> getProductCategories();

    Single<ForooshSakhtAfzarCompareModel> getForooshSakhtAfzarCompare(String startDate , String endDate,String startDate2 , String endDate2 ,List<Integer> categories);


}
