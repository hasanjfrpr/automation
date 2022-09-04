package com.dayrayaneh.automation.viewModel.pishkhan.lidha;

import androidx.lifecycle.MutableLiveData;

import com.dayrayaneh.automation.base.AutomationSingleObserver;
import com.dayrayaneh.automation.base.BaseViewModel;
import com.dayrayaneh.automation.model.pishkhan.lidModel.LidModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LidhaViewModel extends BaseViewModel {

    public MutableLiveData<LidModel> lidModelMutableLiveData = new MutableLiveData<>();

    private  LidRepository repo =new LidRepositoryImpl();



    public void getLid(String startDate , String endDate , int company){
        repo.getLids(startDate , endDate , company)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutomationSingleObserver<LidModel>(compositeDisposable) {
                    @Override
                    public void onSuccess(LidModel lidModel) {
                        lidModelMutableLiveData.setValue(lidModel);
                    }
                });

    }


}
