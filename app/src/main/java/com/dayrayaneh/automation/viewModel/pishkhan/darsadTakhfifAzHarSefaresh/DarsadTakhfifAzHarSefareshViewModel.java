package com.dayrayaneh.automation.viewModel.pishkhan.darsadTakhfifAzHarSefaresh;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dayrayaneh.automation.base.AutomationSingleObserver;
import com.dayrayaneh.automation.base.BaseViewModel;
import com.dayrayaneh.automation.model.pishkhan.darsadThakhfifAzHarSefaresh.DarsadTakhfifAzHarSefareshModel;
import com.dayrayaneh.automation.viewModel.pishkhan.darsadTakhfifAzHarSefaresh.repo.DarsadTakhfifAzHarSefareshRepo;
import com.dayrayaneh.automation.viewModel.pishkhan.darsadTakhfifAzHarSefaresh.repo.DarsadTakhfifAzHarSefareshRepoImpl;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DarsadTakhfifAzHarSefareshViewModel extends BaseViewModel {

    public MutableLiveData<DarsadTakhfifAzHarSefareshModel> darsadTakhfifAzHarSefareshLiveData = new MutableLiveData<>() ;
    private DarsadTakhfifAzHarSefareshRepo repo = new DarsadTakhfifAzHarSefareshRepoImpl();



    public void getDarsadTakhfifAzHarSefaresh(String startDate , String endDate){
        repo.getDarsadTakhfifAzHarSefaresh(startDate,endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutomationSingleObserver<DarsadTakhfifAzHarSefareshModel>(compositeDisposable) {
                    @Override
                    public void onSuccess(@NonNull DarsadTakhfifAzHarSefareshModel darsadTakhfifAzHarSefareshModel) {
                        darsadTakhfifAzHarSefareshLiveData.setValue(darsadTakhfifAzHarSefareshModel);
                    }
                });
    }


}
