package com.liveservey.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.liveservey.database.AppDatabaseConstants;
import com.liveservey.database.UserTable;
import com.liveservey.model.UserModel;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(UserTable userTable);

    @Query("DELETE FROM "+AppDatabaseConstants.USER_TABLE)
    void deleteAll();

    @Query("SELECT * FROM "+AppDatabaseConstants.USER_TABLE+" ORDER BY userId ASC")
    List<UserTable> getAllUsers();

    @Query("SELECT * FROM "+AppDatabaseConstants.USER_TABLE+" ORDER BY userId ASC")
    List<UserModel> getUsers();
    @Query("SELECT * FROM "+AppDatabaseConstants.USER_TABLE+" WHERE "+AppDatabaseConstants.USER_EMAIL+"= :email AND "+AppDatabaseConstants.PASSWORD+"= :pass")
    UserModel getExistUserByEmail(String email, String pass);
    @Query("SELECT * FROM "+AppDatabaseConstants.USER_TABLE+" WHERE "+AppDatabaseConstants.USER_EMAIL+"= :email")
    UserModel getExistUserByEmail(String email);
}
