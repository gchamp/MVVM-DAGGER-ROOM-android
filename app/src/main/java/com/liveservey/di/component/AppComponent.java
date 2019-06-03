package com.liveservey.di.component;

import com.liveservey.MainApplication;
import com.liveservey.di.builder.ActivityBuilder;
import com.liveservey.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        /* Use AndroidInjectionModule.class if you're not using support library */
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBuilder.class})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(MainApplication application);
        AppComponent build();
    }
    void inject(MainApplication app);
}