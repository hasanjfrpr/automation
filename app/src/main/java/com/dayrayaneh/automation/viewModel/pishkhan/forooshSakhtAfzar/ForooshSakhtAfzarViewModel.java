package com.dayrayaneh.automation.viewModel.pishkhan.forooshSakhtAfzar;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dayrayaneh.automation.base.AutomationSingleObserver;
import com.dayrayaneh.automation.base.BaseView;
import com.dayrayaneh.automation.base.BaseViewModel;
import com.dayrayaneh.automation.model.pishkhan.forooshSakhtAfzar.ForooshSakhtAfzarModel;
import com.dayrayaneh.automation.viewModel.pishkhan.forooshSakhtAfzar.repo.ForooshSakhtAfzarRepo;
import com.dayrayaneh.automation.viewModel.pishkhan.forooshSakhtAfzar.repo.ForooshSakhtAfzarRepoImpl;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ForooshSakhtAfzarViewModel extends BaseViewModel {


    public MutableLiveData<ForooshSakhtAfzarModel> forooshSakhtAfzarLiveData = new MutableLiveData<>();
    private ForooshSakhtAfzarRepo repo = new ForooshSakhtAfzarRepoImpl();


    public void getForooshSakhtAfzar(String startDate , String endDate , List<Integer> categories){
        repo.getForooshSakhtAfzar(startDate,endDate,categories)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutomationSingleObserver<ForooshSakhtAfzarModel>(compositeDisposable) {
                    @Override
                    public void onSuccess(@NonNull ForooshSakhtAfzarModel forooshSakhtAfzarModel) {
                        forooshSakhtAfzarLiveData.setValue(forooshSakhtAfzarModel);
                    }
                });
    }

}
