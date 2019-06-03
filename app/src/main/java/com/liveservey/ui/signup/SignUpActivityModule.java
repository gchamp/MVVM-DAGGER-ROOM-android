package com.liveservey.ui.signup;

import dagger.Module;
import dagger.Provides;

@Module
public class SignUpActivityModule {
    @Provides
    SignUpViewModel provideLoginViewModel() {
        return new SignUpViewModel();
    }
}