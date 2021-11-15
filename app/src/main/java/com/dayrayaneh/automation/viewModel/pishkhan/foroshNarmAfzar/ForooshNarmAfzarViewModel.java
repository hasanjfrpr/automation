package com.dayrayaneh.automation.viewModel.pishkhan.foroshNarmAfzar;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dayrayaneh.automation.base.AutomationSingleObserver;
import com.dayrayaneh.automation.base.BaseViewModel;
import com.dayrayaneh.automation.model.pishkhan.forooshNarmAfzar.ForooshNarmAfzarModel;
import com.dayrayaneh.automation.viewModel.pishkhan.foroshNarmAfzar.repo.ForooshNarmAfzarRepo;
import com.dayrayaneh.automation.viewModel.pishkhan.foroshNarmAfzar.repo.ForooshNarmAfzarRepoImpl;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ForooshNarmAfzarViewModel  extends BaseViewModel {

    public MutableLiveData<ForooshNarmAfzarModel> forooshNarmAfzarLiveData = new MutableLiveData<>();
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


}
