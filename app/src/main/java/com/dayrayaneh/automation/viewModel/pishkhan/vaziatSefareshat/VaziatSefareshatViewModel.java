package com.dayrayaneh.automation.viewModel.pishkhan.vaziatSefareshat;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dayrayaneh.automation.base.AutomationSingleObserver;
import com.dayrayaneh.automation.base.BaseViewModel;
import com.dayrayaneh.automation.model.pishkhan.vaziatSefareshat.VaziatSefareshatModel;
import com.dayrayaneh.automation.viewModel.pishkhan.vaziatSefareshat.repo.VaziatSefareshatRepo;
import com.dayrayaneh.automation.viewModel.pishkhan.vaziatSefareshat.repo.VaziatSefareshatRepoImpl;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class VaziatSefareshatViewModel extends BaseViewModel {

    public MutableLiveData<VaziatSefareshatModel> vaziatSefareshatleLiveData =new MutableLiveData<>();
    private VaziatSefareshatRepo repo = new VaziatSefareshatRepoImpl();



    public void getVaziatSefareshat(String startDate , String endDate){
        repo.getVaziatSefareshat(startDate,endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutomationSingleObserver<VaziatSefareshatModel>(compositeDisposable) {
                    @Override
                    public void onSuccess(@NonNull VaziatSefareshatModel vaziatSefareshatModel) {
                        vaziatSefareshatleLiveData.setValue(vaziatSefareshatModel);
                    }
                });
    }

}
