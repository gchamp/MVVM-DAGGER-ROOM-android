package com.demo.ui.login;

import com.demo.model.UserModel;

public interface LoginNavigator {
    void handleError(Throwable throwable);
    void login(UserModel userModel);
    void openMainActivity(UserModel userModel);
}
