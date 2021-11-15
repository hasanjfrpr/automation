package com.dayrayaneh.automation.viewModel.pishkhan.bazaryabi;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dayrayaneh.automation.base.AutomationSingleObserver;
import com.dayrayaneh.automation.base.BaseViewModel;
import com.dayrayaneh.automation.model.pishkhan.pishkhan_bazaryabi.count.BazaryabiMainModel;
import com.dayrayaneh.automation.model.pishkhan.pishkhan_bazaryabi.detail.BazaryabiDetailModel;
import com.dayrayaneh.automation.viewModel.pishkhan.bazaryabi.repo.BazaryabiRepository;
import com.dayrayaneh.automation.viewModel.pishkhan.bazaryabi.repo.BazaryabiRepositoryImpl;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BazaryabiViewModel extends BaseViewModel {


    public MutableLiveData<BazaryabiMainModel> bazaryabiMainLiveData = new MutableLiveData<>();
    public MutableLiveData<BazaryabiDetailModel> bazaryabiDetailLiveData = new MutableLiveData<>();

    private BazaryabiRepository bazaryabiRepository = new BazaryabiRepositoryImpl();




    public void getBazarYabiMainCount(String fromDate , String toDate , int company){
        bazaryabiRepository.getBazaryabiCount(fromDate , toDate , company )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutomationSingleObserver<BazaryabiMainModel>(compositeDisposable) {
                    @Override
                    public void onSuccess(@NonNull BazaryabiMainModel bazaryabiMainModel) {
                        bazaryabiMainLiveData.setValue(bazaryabiMainModel);
                    }
                });
    }

    public void getBazarYabiDetail(String fromDate , String toDate , int personalCode){
        bazaryabiRepository.getBazaryabiDetail(fromDate , toDate , personalCode )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutomationSingleObserver<BazaryabiDetailModel>(compositeDisposable) {
                    @Override
                    public void onSuccess(@NonNull BazaryabiDetailModel bazaryabiDetailModel) {
                        bazaryabiDetailLiveData.setValue(bazaryabiDetailModel);
                    }
                });
    }




}
