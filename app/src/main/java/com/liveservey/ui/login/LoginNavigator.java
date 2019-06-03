package com.liveservey.ui.login;

import com.liveservey.model.UserModel;

public interface LoginNavigator {
    void handleError(Throwable throwable);
    void login(UserModel userModel);
    void openMainActivity(UserModel userModel);
}
