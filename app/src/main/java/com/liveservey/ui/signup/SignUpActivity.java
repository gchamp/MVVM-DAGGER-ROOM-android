package com.liveservey.ui.signup;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;

import com.liveservey.BR;
import com.liveservey.R;
import com.liveservey.databinding.ActivitySignUpBinding;
import com.liveservey.model.UserModel;
import com.liveservey.ui.base.BaseActivity;
import com.liveservey.ui.login.LoginActivity;
import com.liveservey.util.CommonUtils;

import dagger.android.AndroidInjection;

public class SignUpActivity extends BaseActivity<ActivitySignUpBinding,SignUpViewModel> implements SignUpNavigator {

    private ActivitySignUpBinding mBinding;
    private SignUpViewModel signUpViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        mBinding = getViewDataBinding();
        SignUpViewModel.Factor factor = new SignUpViewModel.Factor(this);
        signUpViewModel = ViewModelProviders.of(this,factor).get(SignUpViewModel.class);
        mBinding.setViewModel(signUpViewModel);
        signUpViewModel.setNavigator(this);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_sign_up;
    }

    @Override
    public SignUpViewModel getViewModel() {
        return signUpViewModel;
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
    public void signUp(UserModel userModel) {
        openNewActivity();
    }

    @Override
    public void openNewActivity() {

        Intent intent = new Intent(this,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }
}
