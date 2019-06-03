package com.demo.ui.signup;

import com.demo.model.UserModel;

public interface SignUpNavigator {
    void handleError(Throwable throwable);
    void signUp(UserModel userModel);
    void openNewActivity();

}
