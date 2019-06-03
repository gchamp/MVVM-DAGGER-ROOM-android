package com.liveservey.di.module;

import android.content.Context;

import com.liveservey.MainApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    Context provideContext(MainApplication application) {
        return application.getApplicationContext();
    }


}