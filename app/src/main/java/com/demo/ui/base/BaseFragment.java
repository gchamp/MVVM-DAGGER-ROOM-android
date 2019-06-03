package com.demo.ui.base;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class BaseFragment<T extends ViewDataBinding, V extends ViewModel> extends Fragment {

    private BaseActivity mActivity;
    private T mViewDataBinding;
    private V mViewModel;


    public abstract int getBindingVariableId();

    @LayoutRes
    public abstract int getLayoutResource();

    public abstract V getViewModel();

    public T getViewDataBinding(){
        return mViewDataBinding;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity){
            mActivity = (BaseActivity)context;
            mActivity.onFragmentAttached();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = getViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewDataBinding = DataBindingUtil.inflate(inflater,getLayoutResource(),container,false);
        return mViewDataBinding.getRoot();
    }

    @Override
    public void onDetach() {
        mActivity=null;
        super.onDetach();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewDataBinding.setVariable(getBindingVariableId(),mViewModel);
        mViewDataBinding.executePendingBindings();
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }


    public void hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }
    }
    public boolean isNetworkConnected() {
        return mActivity != null && mActivity.isNetworkConnected();
    }
    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }

}
