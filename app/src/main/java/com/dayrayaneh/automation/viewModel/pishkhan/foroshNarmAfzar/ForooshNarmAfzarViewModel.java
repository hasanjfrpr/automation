package com.dayrayaneh.automation.viewModel.pishkhan.foroshNarmAfzar;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dayrayaneh.automation.base.AutomationSingleObserver;
import com.dayrayaneh.automation.base.BaseViewModel;
import com.dayrayaneh.automation.model.pishkhan.forooshNarmAfzar.ForooshNarmAfzarModel;
import com.dayrayaneh.automation.model.pishkhan.forooshNarmAfzar.compare.ForooshNarmAfzarCompareModel;
import com.dayrayaneh.automation.viewModel.pishkhan.foroshNarmAfzar.repo.ForooshNarmAfzarRepo;
import com.dayrayaneh.automation.viewModel.pishkhan.foroshNarmAfzar.repo.ForooshNarmAfzarRepoImpl;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ForooshNarmAfzarViewModel  extends BaseViewModel {

    public MutableLiveData<ForooshNarmAfzarModel> forooshNarmAfzarLiveData = new MutableLiveData<>();
    public MutableLiveData<ForooshNarmAfzarCompareModel> forooshNarmAfzarCompareLiveData = new MutableLiveData<>();
    private ForooshNarmAfzarRepo repo = new ForooshNarmAfzarRepoImpl();



    public void getForooshNarmAfzar(String startDate , String endDate){
        repo.getForooshNarmAfzar(startDate , endDate )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutomationSingleObserver<ForooshNarmAfzarModel>(compositeDisposable) {
                    @Override
                    public void onSuccess(@NonNull ForooshNarmAfzarModel forooshNarmAfzarModel) {
                        forooshNarmAfzarLiveData.setValue(forooshNarmAfzarModel);
                    }
                });
    }

    public void getForooshNarmAfzarCompare(String startDate , String endDate , String startDateCompare , String endDateCompare){
        repo.getForooshNarmAfzarCompare(startDate , endDate , startDateCompare , endDateCompare)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutomationSingleObserver<ForooshNarmAfzarCompareModel>(compositeDisposable) {
                    @Override
                    public void onSuccess(@NonNull ForooshNarmAfzarCompareModel forooshNarmAfzarCompareModel) {
                        forooshNarmAfzarCompareLiveData.setValue(forooshNarmAfzarCompareModel);
                    }
                });
    }


}
