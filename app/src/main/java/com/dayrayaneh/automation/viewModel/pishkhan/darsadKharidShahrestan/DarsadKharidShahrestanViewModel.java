package com.dayrayaneh.automation.viewModel.pishkhan.darsadKharidShahrestan;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dayrayaneh.automation.base.AutomationSingleObserver;
import com.dayrayaneh.automation.base.BaseViewModel;
import com.dayrayaneh.automation.model.pishkhan.darsadKharidShahrestan.DarsadKharidShahrestanModel;
import com.dayrayaneh.automation.model.pishkhan.darsadKharidShahrestan.details.DarsadKharidShahrestanDetailsModel;
import com.dayrayaneh.automation.viewModel.pishkhan.darsadKharidShahrestan.repo.DarsadKharidShahrestanRepo;
import com.dayrayaneh.automation.viewModel.pishkhan.darsadKharidShahrestan.repo.DarsadKharidShahrestanRepoImpl;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DarsadKharidShahrestanViewModel extends BaseViewModel {


    public MutableLiveData<DarsadKharidShahrestanModel> darsadKharidShahrestanLiveData = new MutableLiveData<>();
    public MutableLiveData<DarsadKharidShahrestanDetailsModel> darsadKharidShahrestanDetailsLiveData = new MutableLiveData<>();
    private DarsadKharidShahrestanRepo shahrestanRepo = new DarsadKharidShahrestanRepoImpl();


    public void getDarsadKharidShahrestan(String startDate , String endDate , int productType){

        shahrestanRepo.getDarsadKharidShahrestan(startDate , endDate ,productType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutomationSingleObserver<DarsadKharidShahrestanModel>(compositeDisposable) {
                    @Override
                    public void onSuccess(@NonNull DarsadKharidShahrestanModel darsadKharidShahrestanModel) {
                        darsadKharidShahrestanLiveData.setValue(darsadKharidShahrestanModel);
                    }
                });

    }

    public void getDarsadKharidShahrestanDetails(String startDate , String endDate , int productType , int cityId){

        shahrestanRepo.getDarsadKharidShahrestanDetails(startDate , endDate , productType , cityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutomationSingleObserver<DarsadKharidShahrestanDetailsModel>(compositeDisposable) {
                    @Override
                    public void onSuccess(@NonNull DarsadKharidShahrestanDetailsModel darsadKharidShahrestanDetailsModel) {
                        darsadKharidShahrestanDetailsLiveData.setValue(darsadKharidShahrestanDetailsModel);
                    }
                });

    }




}
