package com.liveservey.di.builder;

import com.liveservey.ui.login.LoginActivity;
import com.liveservey.ui.login.LoginActivityModule;
import com.liveservey.ui.signup.SignUpActivity;
import com.liveservey.ui.signup.SignUpActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity bindLoginActivity();


    @ContributesAndroidInjector(modules = SignUpActivityModule.class)
    abstract SignUpActivity bindSignUpActivity();
}
