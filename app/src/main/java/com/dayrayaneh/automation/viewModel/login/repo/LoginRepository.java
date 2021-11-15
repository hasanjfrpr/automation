package com.dayrayaneh.automation.viewModel.login.repo;

import com.dayrayaneh.automation.model.login.LoginModel;

import io.reactivex.Single;

public interface LoginRepository {



    Single<LoginModel> sendUsername(String username , String password);

}
