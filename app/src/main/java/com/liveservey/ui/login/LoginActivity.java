package com.liveservey.ui.login;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;

import com.liveservey.BR;
import com.liveservey.R;
import com.liveservey.databinding.ActivityLoginBinding;
import com.liveservey.model.UserModel;
import com.liveservey.ui.base.BaseActivity;
import com.liveservey.util.CommonUtils;

import dagger.android.AndroidInjection;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding,LoginViewModel> implements LoginNavigator{

    private LoginViewModel mLoginViewModel;
    private ActivityLoginBinding mBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        mBinding = getViewDataBinding();
        LoginViewModel.Factory factory= new LoginViewModel.Factory(this);
        mLoginViewModel = ViewModelProviders.of(this,factory).get(LoginViewModel.class);
        mBinding.setViewModel(mLoginViewModel);
        mLoginViewModel.setNavigator(this);


    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    public LoginViewModel getViewModel() {
        return mLoginViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }


    @Override
    public void handleError(Throwable throwable) {
        CommonUtils.showToast(this,throwable.getMessage());
    }

    @Override
    public void login(UserModel userModel) {
        openMainActivity(userModel);
    }

    @Override
    public void openMainActivity(UserModel userModel) {
       /* Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();*/
    }
}

