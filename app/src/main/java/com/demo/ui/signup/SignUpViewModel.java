package com.demo.ui.signup;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.demo.R;
import com.demo.model.UserModel;
import com.demo.presenter.UserRepositoryListener;
import com.demo.repository.UserRepository;
import com.demo.ui.base.BaseViewModel;
import com.demo.util.CommonUtils;

public class SignUpViewModel extends BaseViewModel<SignUpNavigator> implements UserRepositoryListener {

    private Context mContext;
    private boolean isRegistration = false;

    private ObservableField<String> userFirstName = new ObservableField<>();
    private ObservableField<String> userLastName = new ObservableField<>();
    private ObservableField<String> userEmail = new ObservableField<>();
    private ObservableField<String> userMobile = new ObservableField<>();
    private ObservableField<String> userPassword= new ObservableField<>();
    private ObservableField<String> userConfirmPassword= new ObservableField<>();

    public SignUpViewModel(){
        super();
    }

    public SignUpViewModel(Context mContext){
        this.mContext = mContext;
    }

    public boolean isValidate(){
        if (TextUtils.isEmpty(userFirstName.get())){
            CommonUtils.showToast(mContext,mContext.getResources().getString(R.string.please_enter_first_name));
            return false;
        }else if (TextUtils.isEmpty(userLastName.get())){
            CommonUtils.showToast(mContext,mContext.getResources().getString(R.string.please_enter_last_name));
            return false;
        }else if (TextUtils.isEmpty(userEmail.get())){
            CommonUtils.showToast(mContext,mContext.getResources().getString(R.string.please_enter_email));
            return false;
        }else if (!CommonUtils.isEmailValid(userEmail.get())){
            CommonUtils.showToast(mContext,mContext.getResources().getString(R.string.please_valid_email));
            return false;
        }else if (TextUtils.isEmpty(userMobile.get())){
            CommonUtils.showToast(mContext,mContext.getResources().getString(R.string.please_enter_mobile));
            return false;
        }else if (userMobile.get().length()<10){
            CommonUtils.showToast(mContext,mContext.getResources().getString(R.string.please_valid_mobile));
            return false;
        }else if (TextUtils.isEmpty(userPassword.get())){
            CommonUtils.showToast(mContext,mContext.getResources().getString(R.string.please_enter_password));
        }else if (userPassword.get().length()<8){
            CommonUtils.showToast(mContext,mContext.getResources().getString(R.string.please_valid_password));
            return false;
        }else if (TextUtils.isEmpty(userConfirmPassword.get())){
            CommonUtils.showToast(mContext,mContext.getResources().getString(R.string.please_confirm_password));
            return false;
        }else if (!userConfirmPassword.get().equalsIgnoreCase(userPassword.get())){
            CommonUtils.showToast(mContext,mContext.getResources().getString(R.string.confirm_password_same));
            return false;
        }
        return true;
    }

    public ObservableField<String> getUserFirstName() {
        return userFirstName;
    }

    public ObservableField<String> getUserLastName() {
        return userLastName;
    }


    public ObservableField<String> getUserEmail() {
        return userEmail;
    }

    public ObservableField<String> getUserMobile() {
        return userMobile;
    }

    public ObservableField<String> getUserPassword() {
        return userPassword;
    }

    public ObservableField<String> getUserConfirmPassword() {
        return userConfirmPassword;
    }



    public void registerUser(){
        if (isValidate()){
            try {
                isRegistration = true;
                UserRepository userRepository = new UserRepository(mContext,this);
                userRepository.getExistUser(userEmail.get());
            } catch (Exception e) {
                e.printStackTrace();
                getNavigator().handleError(e);
            }
        }
    }

    public void alreadyRegis(){
        getNavigator().openNewActivity();
    }

    public void insertNewUser(UserModel userModel) {
        isRegistration = false;
        UserRepository userRepository = new UserRepository(mContext,this);
        userRepository.insert(userModel);
    }

    @Override
    public void onGetUser(UserModel userModel) {
        if (userModel != null){
            CommonUtils.showToast(mContext,mContext.getResources().getString(R.string.email_already_exist));
        }else if (!isRegistration){
            getNavigator().signUp(userModel);
        }else {
            userModel = new UserModel();
            userModel.setUserFirstName(userFirstName.get());
            userModel.setUserLastName(userLastName.get());
            userModel.setUserName(userFirstName.get()+" "+userLastName.get());
            userModel.setUserEmail(userEmail.get());
            userModel.setUserMobile(userMobile.get());
            userModel.setPassword(userPassword.get());
            userModel.setIsActive(1);
            userModel.setCreateAt(System.currentTimeMillis());
            userModel.setLatitude(28);
            userModel.setLongitude(77);
            insertNewUser(userModel);
        }
    }

    public static class Factor extends ViewModelProvider.NewInstanceFactory{
        @NonNull
        private Context mContext;
        public Factor(Context mContext){
            this.mContext = mContext;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new SignUpViewModel(mContext);
        }
    }

}
