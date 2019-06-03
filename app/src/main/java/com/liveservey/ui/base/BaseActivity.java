package com.liveservey.ui.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.liveservey.util.NetworkUtils;

import dagger.android.AndroidInjection;

public abstract class BaseActivity<T extends ViewDataBinding , V extends BaseViewModel> extends  AppCompatActivity implements BaseFragment.Callback{


    private T mViewDataBinding;
    private V mViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        performDataBinding();

    }

    public abstract int getLayoutResource();

    public abstract V getViewModel();

    public abstract int getBindingVariable();


    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    public void performDependencyInjection() {
        AndroidInjection.inject(this);
    }


    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutResource());
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

}
