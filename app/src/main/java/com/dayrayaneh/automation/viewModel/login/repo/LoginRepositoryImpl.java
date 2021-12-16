package com.dayrayaneh.automation.viewModel.login.repo;

import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.model.login.LoginModel;
import com.dayrayaneh.automation.services.httpclient.ApiInstance;
import com.dayrayaneh.automation.services.httpclient.ApiService;
import com.google.gson.JsonObject;

import io.reactivex.Single;

public class LoginRepositoryImpl implements LoginRepository{

   ApiService apiService ;
   private JsonObject jsonObject = new JsonObject();



   @Override
   public Single<LoginModel> sendUsername(String username, String password) {
       apiService = ApiInstance.getApiInstanceOnce();
      jsonObject.addProperty("username" , username);
      jsonObject.addProperty("password",password);
      return apiService.sendUsername(jsonObject );

   }


}
