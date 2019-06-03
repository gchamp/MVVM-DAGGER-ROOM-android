package com.demo.di.component;

import com.demo.MainApplication;
import com.demo.di.builder.ActivityBuilder;
import com.demo.di.module.AppModule;

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