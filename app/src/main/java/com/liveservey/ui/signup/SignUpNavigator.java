package com.liveservey.ui.signup;

import com.liveservey.model.UserModel;

public interface SignUpNavigator {
    void handleError(Throwable throwable);
    void signUp(UserModel userModel);
    void openNewActivity();

}
