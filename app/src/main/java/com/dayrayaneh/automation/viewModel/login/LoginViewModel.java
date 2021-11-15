package com.dayrayaneh.automation.viewModel.login;

import androidx.lifecycle.MutableLiveData;

import com.dayrayaneh.automation.base.BaseViewModel;
import com.dayrayaneh.automation.model.login.LoginModel;
import com.dayrayaneh.automation.viewModel.login.repo.LoginRepository;
import com.dayrayaneh.automation.viewModel.login.repo.LoginRepositoryImpl;

import io.reactivex.Single;

public class LoginViewModel extends BaseViewModel {



    private LoginRepository loginRepository = new LoginRepositoryImpl();
    public MutableLiveData<LoginModel> loginModelLiveData = new MutableLiveData<>();



    public Single<LoginModel> sendUsername(String username, String password) {
        return loginRepository.sendUsername(username,password);
    }

}
