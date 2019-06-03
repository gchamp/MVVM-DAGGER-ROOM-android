package com.demo.di.builder;

import com.demo.ui.login.LoginActivity;
import com.demo.ui.login.LoginActivityModule;
import com.demo.ui.signup.SignUpActivity;
import com.demo.ui.signup.SignUpActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity bindLoginActivity();


    @ContributesAndroidInjector(modules = SignUpActivityModule.class)
    abstract SignUpActivity bindSignUpActivity();
}
