package com.dayrayaneh.automation.viewModel.pishkhan.tamdidQarardad;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dayrayaneh.automation.base.AutomationSingleObserver;
import com.dayrayaneh.automation.base.BaseViewModel;
import com.dayrayaneh.automation.model.pishkhan.tamdidQarardad.TamdidGharardadModel;
import com.dayrayaneh.automation.model.pishkhan.tamdidQarardad.compare.TamdidGharardadCompareModel;
import com.dayrayaneh.automation.viewModel.pishkhan.tamdidQarardad.repo.TamdidGharardadRepo;
import com.dayrayaneh.automation.viewModel.pishkhan.tamdidQarardad.repo.TamdidGharardadRepoImpl;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TamdidGharardadViewModel extends BaseViewModel {


    public MutableLiveData<TamdidGharardadModel> tamdidGharardadLiveData = new MutableLiveData<>() ;
    public MutableLiveData<TamdidGharardadCompareModel> tamdidGharardadCompareLiveData = new MutableLiveData<>() ;
    private TamdidGharardadRepo repo = new TamdidGharardadRepoImpl()    ;




    public void getTamdidGharardad(String startDate , String endDate){
        repo.getTamdidGharardad(startDate , endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutomationSingleObserver<TamdidGharardadModel>(compositeDisposable) {
                    @Override
                    public void onSuccess(@NonNull TamdidGharardadModel tamdidGharardadModel) {
                        tamdidGharardadLiveData.setValue(tamdidGharardadModel);
                    }
                });
    }


    public void getTamdidGharardadCompare(String startDate , String endDate , String startDateCompare , String endDateCompare , int noeService){
        repo.getTamdidGharardadCompare(startDate , endDate , startDateCompare , endDateCompare , noeService)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutomationSingleObserver<TamdidGharardadCompareModel>(compositeDisposable) {
                    @Override
                    public void onSuccess(@NonNull TamdidGharardadCompareModel tamdidGharardadCompareModel) {
                        tamdidGharardadCompareLiveData.setValue(tamdidGharardadCompareModel);
                    }
                });
    }




}
