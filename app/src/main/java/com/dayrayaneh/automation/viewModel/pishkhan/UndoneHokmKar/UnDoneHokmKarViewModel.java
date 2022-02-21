package com.dayrayaneh.automation.viewModel.pishkhan.UndoneHokmKar;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dayrayaneh.automation.base.AutomationSingleObserver;
import com.dayrayaneh.automation.base.BaseViewModel;
import com.dayrayaneh.automation.model.pishkhan.UnDoneHokmKar.Count.UnDoneHokmkarCountMode;
import com.dayrayaneh.automation.model.pishkhan.UnDoneHokmKar.UnDoneHokmKarModel;
import com.dayrayaneh.automation.viewModel.pishkhan.UndoneHokmKar.repo.UnDoneHokmKarRepo;
import com.dayrayaneh.automation.viewModel.pishkhan.UndoneHokmKar.repo.UnDoneHokmKarRepoImpl;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UnDoneHokmKarViewModel extends BaseViewModel {
    public MutableLiveData<UnDoneHokmKarModel> unDoneHokmKarModelMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<UnDoneHokmkarCountMode> UndoneHokmKarCountLiveData = new MutableLiveData<>();
    private UnDoneHokmKarRepo repo =new UnDoneHokmKarRepoImpl();



    public void getUnDoneHokmKar(String startDate , String endDate , int personId ){
        repo.getUnDoneHokmKar(startDate , endDate , personId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutomationSingleObserver<UnDoneHokmKarModel>(compositeDisposable) {
                    @Override
                    public void onSuccess(@NonNull UnDoneHokmKarModel unDoneHokmKarModel) {
                        unDoneHokmKarModelMutableLiveData.setValue(unDoneHokmKarModel);
                    }
                });
    }

    public void getUnDoneHokmkarCount(String startDate , String endDate ){
        repo.getUnDoneHokmKarCount(startDate , endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutomationSingleObserver<UnDoneHokmkarCountMode>(compositeDisposable) {
                    @Override
                    public void onSuccess(@NonNull UnDoneHokmkarCountMode unDoneHokmkarCountMode) {
                        UndoneHokmKarCountLiveData.setValue(unDoneHokmkarCountMode);
                    }
                });
    }




}
