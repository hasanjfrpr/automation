package com.dayrayaneh.automation.viewModel.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class LoginViewModelFactory implements ViewModelProvider.Factory {

    
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> aClass) {

        return (T) new LoginViewModel();

    }
}
