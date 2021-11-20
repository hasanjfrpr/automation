package com.dayrayaneh.automation.viewModel.pishkhan.HokmKar;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dayrayaneh.automation.base.AutomationSingleObserver;
import com.dayrayaneh.automation.base.BaseViewModel;
import com.dayrayaneh.automation.model.pishkhan.HokmKar.HokmKarModel;
import com.dayrayaneh.automation.model.pishkhan.HokmKar.followers.HokmKarFollowersModel;
import com.dayrayaneh.automation.model.pishkhan.HokmKar.request.HokmKarRequestModel;
import com.dayrayaneh.automation.viewModel.pishkhan.HokmKar.repo.HokmKarRepo;
import com.dayrayaneh.automation.viewModel.pishkhan.HokmKar.repo.HokmKarRepoImpl;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HokmKarViewModel extends BaseViewModel {


    public MutableLiveData<HokmKarModel> hokmKarLiveData = new MutableLiveData<>();
    public MutableLiveData<HokmKarRequestModel> hokmKarRequestLiveData = new MutableLiveData<>()  ;
    public MutableLiveData<HokmKarFollowersModel> hokmKarFollowersLiveData = new MutableLiveData<>()  ;
    private HokmKarRepo repo = new HokmKarRepoImpl();


    public void getHokmKar(String startDate , String endDate){
        repo.getHokmKar(startDate , endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutomationSingleObserver<HokmKarModel>(compositeDisposable) {
                    @Override
                    public void onSuccess(@NonNull HokmKarModel hokmKarModel) {
                        hokmKarLiveData.setValue(hokmKarModel);
                    }
                });
    }

    public void getHokmKarRequest(int userId){
        repo.getHokmKarRequest(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutomationSingleObserver<HokmKarRequestModel>(compositeDisposable) {
                    @Override
                    public void onSuccess(@NonNull HokmKarRequestModel hokmKarRequestModel) {
                        hokmKarRequestLiveData.setValue(hokmKarRequestModel);
                    }
                });
    }

    public void getHokmKarFollowers(int UserId){
        repo.getHokmKarFollowers(UserId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutomationSingleObserver<HokmKarFollowersModel>(compositeDisposable) {
                    @Override
                    public void onSuccess(@NonNull HokmKarFollowersModel hokmKarFollowersModel) {
                        hokmKarFollowersLiveData.setValue(hokmKarFollowersModel);
                    }
                });
    }






}
