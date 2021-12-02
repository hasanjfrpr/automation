package com.dayrayaneh.automation.viewModel.pishkhan.tedadHokmKar;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dayrayaneh.automation.base.AutomationSingleObserver;
import com.dayrayaneh.automation.base.BaseView;
import com.dayrayaneh.automation.base.BaseViewModel;
import com.dayrayaneh.automation.model.pishkhan.tedadHokmKarha.count.TedadHokmKarCountModel;
import com.dayrayaneh.automation.model.pishkhan.tedadHokmKarha.details.TedadHokmKarDetailsModel;
import com.dayrayaneh.automation.viewModel.pishkhan.tedadHokmKar.repo.TedadHokmKarRepo;
import com.dayrayaneh.automation.viewModel.pishkhan.tedadHokmKar.repo.TedadHokmKarRepoImpl;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TedadHokmKarViewModel extends BaseViewModel {


    public MutableLiveData<TedadHokmKarCountModel> tedadHokmKarCountLiveData = new MutableLiveData<>();
    public MutableLiveData<TedadHokmKarDetailsModel> tedadHokmKarDetailLiveData = new MutableLiveData<>();
    private TedadHokmKarRepo repo = new TedadHokmKarRepoImpl();


    public void getTedadHokmKarhaCount(String startDate , String endDate){
        repo.getTedadHokmKarCount(startDate , endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutomationSingleObserver<TedadHokmKarCountModel>(compositeDisposable) {
                    @Override
                    public void onSuccess(@NonNull TedadHokmKarCountModel tedadHokmKarCountModel) {
                        tedadHokmKarCountLiveData.setValue(tedadHokmKarCountModel);
                    }
                });

    }

    public void getTedadHokmKarhaDetails(String startDate , String endDate , int personalCode){
        repo.getTedadHokmKarDetail(startDate , endDate , personalCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutomationSingleObserver<TedadHokmKarDetailsModel>(compositeDisposable) {
                    @Override
                    public void onSuccess(@NonNull TedadHokmKarDetailsModel tedadHokmKarDetailsModel) {
                     tedadHokmKarDetailLiveData.setValue(tedadHokmKarDetailsModel);
                    }
                });

    }

}
