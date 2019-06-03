package com.demo.di.module;

import android.content.Context;

import com.demo.MainApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    Context provideContext(MainApplication application) {
        return application.getApplicationContext();
    }


}