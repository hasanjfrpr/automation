package com.dayrayaneh.automation.viewModel.pishkhan.darsadKharidShahrestan;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dayrayaneh.automation.base.AutomationSingleObserver;
import com.dayrayaneh.automation.base.BaseViewModel;
import com.dayrayaneh.automation.model.pishkhan.darsadKharidShahrestan.DarsadKharidShahrestanModel;
import com.dayrayaneh.automation.viewModel.pishkhan.darsadKharidShahrestan.repo.DarsadKharidShahrestanRepo;
import com.dayrayaneh.automation.viewModel.pishkhan.darsadKharidShahrestan.repo.DarsadKharidShahrestanRepoImpl;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DarsadKharidShahrestanViewModel extends BaseViewModel {


    public MutableLiveData<DarsadKharidShahrestanModel> darsadKharidShahrestanLiveData = new MutableLiveData<>();
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


}
