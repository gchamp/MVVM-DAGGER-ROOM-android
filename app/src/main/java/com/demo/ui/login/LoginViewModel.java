package com.demo.ui.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.demo.R;
import com.demo.model.UserModel;
import com.demo.repository.UserRepository;
import com.demo.ui.base.BaseViewModel;
import com.demo.util.CommonUtils;

public class LoginViewModel extends BaseViewModel<LoginNavigator> {

    private Context mContext;
    private MutableLiveData<UserModel> mUser;
    private ObservableField<String> userPassword= new ObservableField<>();
    private ObservableField<String> userEmail= new ObservableField<>();

    public LoginViewModel(){
        super();
    }

    public LoginViewModel(Context mContext){
        this.mContext = mContext;
    }

    public ObservableField<String> getUserPassword() {
        return userPassword;
    }

    public ObservableField<String> getUserEmail() {
        return userEmail;
    }

    public LiveData<UserModel> getUser() {
        if (mUser==null){
            mUser = new MutableLiveData<>();
        }
        return mUser;
    }


    public void login(){
        if (isValidateEmail()){
            try {
                UserModel userModel = getExistUser(userEmail.get(),userPassword.get());
                if (userModel != null){
                    getNavigator().login(userModel);
                }else {
                    CommonUtils.showToast(mContext,mContext.getResources().getString(R.string.user_does_not_exist));
                }
            } catch (Exception e) {
                getNavigator().handleError(e);
            }
        }
    }

    public boolean isValidateEmail(){
        if (TextUtils.isEmpty(userEmail.get())){
            CommonUtils.showToast(mContext,mContext.getResources().getString(R.string.please_enter_email));
            return false;
        }else if (!CommonUtils.isEmailValid(userEmail.get())){
            CommonUtils.showToast(mContext,mContext.getResources().getString(R.string.please_valid_email));
            return false;
        }else if (TextUtils.isEmpty(userPassword.get())){
            CommonUtils.showToast(mContext,mContext.getResources().getString(R.string.please_enter_password));
            return false;
        }
        return true;
    }



    public UserModel getExistUser(String email,String pass){
        UserRepository userRepository = new UserRepository(mContext);
        return userRepository.getExistUser(email,pass);
    }


    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private Context mContext;

        public Factory(Context context) {
            mContext = context;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new LoginViewModel(mContext);
        }
    }

}
