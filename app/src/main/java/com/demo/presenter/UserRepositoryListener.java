package com.demo.presenter;

import com.demo.model.UserModel;

public interface UserRepositoryListener {
    void onGetUser(UserModel userModel);
}
