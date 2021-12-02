package com.dayrayaneh.automation.viewModel.pishkhan.GozareshKarha;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dayrayaneh.automation.base.AutomationSingleObserver;
import com.dayrayaneh.automation.base.BaseView;
import com.dayrayaneh.automation.base.BaseViewModel;
import com.dayrayaneh.automation.model.pishkhan.Gozareshkar.count.GozareshKarCountModel;
import com.dayrayaneh.automation.model.pishkhan.Gozareshkar.details.GozareshKarDetailsModel;
import com.dayrayaneh.automation.model.pishkhan.Gozareshkar.personalName.PersonalListModel;
import com.dayrayaneh.automation.viewModel.pishkhan.GozareshKarha.repo.GozareshKarRepo;
import com.dayrayaneh.automation.viewModel.pishkhan.GozareshKarha.repo.GozareshkarRepoImpl;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GozareshKarViewModel extends BaseViewModel  {

    public MutableLiveData<GozareshKarCountModel> gozareshKarCountLiveData = new MutableLiveData<>();
    public MutableLiveData<GozareshKarDetailsModel> gozareshKarDetailLiveData = new MutableLiveData<>();
    public MutableLiveData<PersonalListModel> personalListLiveData = new MutableLiveData<>();
    private GozareshKarRepo repo = new GozareshkarRepoImpl();


    public void getGozareshkarCount(String startDate , String endDate , String personId){
        repo.getGozareshKarCount(startDate , endDate , personId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutomationSingleObserver<GozareshKarCountModel>(compositeDisposable) {
                    @Override
                    public void onSuccess(@NonNull GozareshKarCountModel gozareshKarCountModel) {
                        gozareshKarCountLiveData.setValue(gozareshKarCountModel);
                    }
                });
    }

    public void getGozareshKarDetial(String startDate , String endDate , String personId , int userCode){
        repo.getGozareshKarDetail(startDate , endDate , personId , userCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutomationSingleObserver<GozareshKarDetailsModel>(compositeDisposable) {
                    @Override
                    public void onSuccess(@NonNull GozareshKarDetailsModel gozareshKarDetailsModel) {
                        gozareshKarDetailLiveData.setValue(gozareshKarDetailsModel);
                    }
                });
    }

    public void getPersonalList(){
        repo.getPersonaList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutomationSingleObserver<PersonalListModel>(compositeDisposable) {
                    @Override
                    public void onSuccess(@NonNull PersonalListModel personalListModel) {
                        personalListLiveData.setValue(personalListModel);
                    }
                });
    }
}
