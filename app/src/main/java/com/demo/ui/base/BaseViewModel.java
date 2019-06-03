package com.demo.ui.base;

import android.arch.lifecycle.ViewModel;

import java.lang.ref.WeakReference;


public abstract class BaseViewModel<M> extends ViewModel {

    private WeakReference<M> mNavigator;

    public BaseViewModel(){}


    public void setNavigator(M mNavigator) {
        this.mNavigator = new WeakReference<M>(mNavigator);
    }

    public M getNavigator(){
        return mNavigator.get();
    }
}