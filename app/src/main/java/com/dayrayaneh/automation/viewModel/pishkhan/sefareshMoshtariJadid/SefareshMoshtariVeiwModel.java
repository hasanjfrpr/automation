package com.dayrayaneh.automation.viewModel.pishkhan.sefareshMoshtariJadid;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dayrayaneh.automation.base.AutomationSingleObserver;
import com.dayrayaneh.automation.base.BaseViewModel;
import com.dayrayaneh.automation.model.pishkhan.sefareshMoshtariJadid.SefareshMoshtariModel;
import com.dayrayaneh.automation.viewModel.pishkhan.sefareshMoshtariJadid.repo.SefareshMoshtariRepo;
import com.dayrayaneh.automation.viewModel.pishkhan.sefareshMoshtariJadid.repo.SefareshMoshtariRepoImpl;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SefareshMoshtariVeiwModel extends BaseViewModel {


    private SefareshMoshtariRepo repo = new SefareshMoshtariRepoImpl();
    public MutableLiveData<SefareshMoshtariModel> sefareshMoshtariLiveData = new MutableLiveData<>();


    public void getSefareshMoshtariJaid(String startDate ,String endDate){

        repo.getSefareshMoshtariJadid(startDate , endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutomationSingleObserver<SefareshMoshtariModel>(compositeDisposable) {
                    @Override
                    public void onSuccess(@NonNull SefareshMoshtariModel sefareshMoshtariModel) {
                        sefareshMoshtariLiveData.setValue(sefareshMoshtariModel);
                    }
                });

    }
}
