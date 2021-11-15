package com.dayrayaneh.automation.viewModel.pishkhan.darsadSefareshat;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dayrayaneh.automation.base.AutomationSingleObserver;
import com.dayrayaneh.automation.base.BaseViewModel;
import com.dayrayaneh.automation.model.pishkhan.darsadSefareshat.DarsadSefareshatModel;
import com.dayrayaneh.automation.viewModel.pishkhan.darsadSefareshat.repo.DarsadSefareshatRepo;
import com.dayrayaneh.automation.viewModel.pishkhan.darsadSefareshat.repo.DarsadSefareshatRepoImpl;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DarsadSefareshatViewModel  extends BaseViewModel {

    public MutableLiveData<DarsadSefareshatModel> darsadSefareshatLiveData = new MutableLiveData<>();
    private DarsadSefareshatRepo repo = new DarsadSefareshatRepoImpl();


    public void getDarsadSefareshat(String startDate , String endDate){
        repo.getDarsadSefareshat(startDate,endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutomationSingleObserver<DarsadSefareshatModel>(compositeDisposable) {
                    @Override
                    public void onSuccess(@NonNull DarsadSefareshatModel darsadSefareshatModel) {
                        darsadSefareshatLiveData.setValue(darsadSefareshatModel);
                    }
                });
    }
}
