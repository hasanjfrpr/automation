package com.dayrayaneh.automation.viewModel.pishkhan.UndoneHokmKar;

import android.app.slice.Slice;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dayrayaneh.automation.base.AutomationSingleObserver;
import com.dayrayaneh.automation.base.BaseViewModel;
import com.dayrayaneh.automation.model.pishkhan.UnDoneHokmKar.UnDoneHokmKarModel;
import com.dayrayaneh.automation.viewModel.pishkhan.UndoneHokmKar.repo.UnDoneHokmKarRepo;
import com.dayrayaneh.automation.viewModel.pishkhan.UndoneHokmKar.repo.UnDoneHokmKarRepoImpl;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UnDoneHokmKarViewModel extends BaseViewModel {
    public MutableLiveData<UnDoneHokmKarModel> unDoneHokmKarModelMutableLiveData = new MutableLiveData<>();
    private UnDoneHokmKarRepo repo =new UnDoneHokmKarRepoImpl();



    public void getUnDoneHokmKar(String startDate , String endDate){
        repo.getUnDoneHokmKar(startDate , endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutomationSingleObserver<UnDoneHokmKarModel>(compositeDisposable) {
                    @Override
                    public void onSuccess(@NonNull UnDoneHokmKarModel unDoneHokmKarModel) {
                        unDoneHokmKarModelMutableLiveData.setValue(unDoneHokmKarModel);
                    }
                });
    }


}
