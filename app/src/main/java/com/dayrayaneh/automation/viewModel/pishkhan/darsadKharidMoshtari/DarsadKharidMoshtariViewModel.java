package com.dayrayaneh.automation.viewModel.pishkhan.darsadKharidMoshtari;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dayrayaneh.automation.base.AutomationSingleObserver;
import com.dayrayaneh.automation.base.BaseViewModel;
import com.dayrayaneh.automation.model.pishkhan.darsadKharidMoshtari.DarsadkharidMoshtariModel;
import com.dayrayaneh.automation.model.pishkhan.darsadKharidMoshtari.details.DarsadKharidMoshtariDetailsModel;
import com.dayrayaneh.automation.viewModel.pishkhan.darsadKharidMoshtari.repo.DarsadKharidMoshtariRepo;
import com.dayrayaneh.automation.viewModel.pishkhan.darsadKharidMoshtari.repo.DarsadKharidMoshtariRepoImpl;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DarsadKharidMoshtariViewModel extends BaseViewModel {


    public MutableLiveData<DarsadkharidMoshtariModel> darsadkharidMoshtariLiveData = new MutableLiveData<>();
    public MutableLiveData<DarsadKharidMoshtariDetailsModel> darsadkharidMoshtariDetailLiveData = new MutableLiveData<>();
    private DarsadKharidMoshtariRepo repo = new DarsadKharidMoshtariRepoImpl();




    public void getDarsadKharidMoshtari(String startDate , String endDate , int productType){
        repo.getDarsadKharidMoshtari(startDate,endDate,productType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutomationSingleObserver<DarsadkharidMoshtariModel>(compositeDisposable) {
                    @Override
                    public void onSuccess(@NonNull DarsadkharidMoshtariModel darsadkharidMoshtariModel) {
                        darsadkharidMoshtariLiveData.setValue(darsadkharidMoshtariModel);
                    }
                });
    }

    public void getDarsadKharidMoshtariDetails(String startDate , String endDate , int productType , int MoshtariId){
        repo.getDarsadKharidMoshtariDetails(startDate , endDate , productType , MoshtariId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutomationSingleObserver<DarsadKharidMoshtariDetailsModel>(compositeDisposable) {
                    @Override
                    public void onSuccess(@NonNull DarsadKharidMoshtariDetailsModel darsadKharidMoshtariDetailsModel) {
                        darsadkharidMoshtariDetailLiveData.setValue(darsadKharidMoshtariDetailsModel);
                    }
                });
    }

}
