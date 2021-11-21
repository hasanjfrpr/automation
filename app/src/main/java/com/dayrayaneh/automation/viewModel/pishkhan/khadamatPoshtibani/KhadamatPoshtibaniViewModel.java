package com.dayrayaneh.automation.viewModel.pishkhan.khadamatPoshtibani;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dayrayaneh.automation.base.AutomationSingleObserver;
import com.dayrayaneh.automation.base.BaseViewModel;
import com.dayrayaneh.automation.model.pishkhan.khadamatPoshtibani.detail.KhadamatPoshtibaniDetailModel;
import com.dayrayaneh.automation.model.pishkhan.khadamatPoshtibani.mian.KhadamatPoshtibaniMainModel;
import com.dayrayaneh.automation.viewModel.pishkhan.khadamatPoshtibani.repo.KhadamatPoshtibaniRepo;
import com.dayrayaneh.automation.viewModel.pishkhan.khadamatPoshtibani.repo.KhadamatPoshtibaniRepoImpl;

import java.lang.invoke.MutableCallSite;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class KhadamatPoshtibaniViewModel extends BaseViewModel {



    public MutableLiveData<KhadamatPoshtibaniMainModel> khadamatPoshtibaniMainLiveData = new MutableLiveData<>() ;
    public MutableLiveData<KhadamatPoshtibaniDetailModel> khadamatPoshtibaniDetaileLiveData = new MutableLiveData<>() ;
    private KhadamatPoshtibaniRepo repo = new KhadamatPoshtibaniRepoImpl();


    public void getKhadamatPoshtibaniMain(String startDate, String endDate , String startTime , String endTime , int company){
        repo.getKhadamatPoshtibaniMain(startDate , endDate , startTime ,endTime , company)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutomationSingleObserver<KhadamatPoshtibaniMainModel>(compositeDisposable) {
                    @Override
                    public void onSuccess(@NonNull KhadamatPoshtibaniMainModel khadamatPoshtibaniMainModel) {
                        khadamatPoshtibaniMainLiveData.setValue(khadamatPoshtibaniMainModel);
                    }
                });
    }


    public void getKhadamatPoshtibaniDetail(String startDate, String endDate , String startTime , String endTime , int userId){
        repo.getKhadamatPoshtibaniDetail(startDate , endDate , startTime ,endTime , userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutomationSingleObserver<KhadamatPoshtibaniDetailModel>(compositeDisposable {
                    @Override
                    public void onSuccess(@NonNull KhadamatPoshtibaniDetailModel khadamatPoshtibaniDetailModel) {
                        khadamatPoshtibaniDetaileLiveData.setValue(khadamatPoshtibaniDetailModel);
                    }
                });
    }


}
