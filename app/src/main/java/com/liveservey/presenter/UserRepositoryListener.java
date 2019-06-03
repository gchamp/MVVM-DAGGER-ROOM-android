package com.liveservey.presenter;

import com.liveservey.model.UserModel;

public interface UserRepositoryListener {
    void onGetUser(UserModel userModel);
}
