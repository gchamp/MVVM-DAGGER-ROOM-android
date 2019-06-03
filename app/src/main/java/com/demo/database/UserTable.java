package com.demo.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.demo.model.UserModel;

@Entity(tableName = AppDatabaseConstants.USER_TABLE)
public class UserTable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = AppDatabaseConstants.USER_ID)
    private int userId;
    @NonNull
    @ColumnInfo(name = AppDatabaseConstants.FIRST_NAME)
    private String userFirstName;
    @NonNull
    @ColumnInfo(name = AppDatabaseConstants.LAST_NAME)
    private String userLastName;
    @NonNull
    @ColumnInfo(name = AppDatabaseConstants.USER_NAME)
    private String userName;
    @NonNull
    @ColumnInfo(name = AppDatabaseConstants.USER_EMAIL)
    private String userEmail;
    @NonNull
    @ColumnInfo(name = AppDatabaseConstants.USER_MOBILE)
    private String userMobile;
    @NonNull
    @ColumnInfo(name = AppDatabaseConstants.PASSWORD)
    private String password;
    @ColumnInfo(name = AppDatabaseConstants.IS_ACTIVE,typeAffinity = 3)
    private int isActive;
    @ColumnInfo(name = AppDatabaseConstants.CREATE_AT,typeAffinity = 3)
    private long createAt;
    @ColumnInfo(name = AppDatabaseConstants.LATITUDE,typeAffinity = 3)
    private long latitude;
    @ColumnInfo(name = AppDatabaseConstants.LONGITUDE,typeAffinity = 3)
    private long longitude;

    public UserTable(){}

    public UserTable(UserModel userModel){
        this.userFirstName = userModel.getUserFirstName();
        this.userLastName = userModel.getUserLastName();
        this.userName= userModel.getUserName();
        this.userEmail=userModel.getUserEmail();
        this.userMobile = userModel.getUserMobile();
        this.isActive = userModel.getIsActive();
        this.createAt = userModel.getCreateAt();
        this.latitude = userModel.getLatitude();
        this.longitude = userModel.getLongitude();
        this.password = userModel.getPassword();
    }



    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }


    public void setUserEmail(String userEmail) {
        this.userEmail= userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @NonNull
    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(@NonNull String userFirstName) {
        this.userFirstName = userFirstName;
    }

    @NonNull
    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(@NonNull String userLastName) {
        this.userLastName = userLastName;
    }

    @NonNull
    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(@NonNull String userMobile) {
        this.userMobile = userMobile;
    }


    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt( long createAt) {
        this.createAt = createAt;
    }


    public long getLatitude() {
        return latitude;
    }

    public void setLatitude( long latitude) {
        this.latitude = latitude;
    }


    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }
}
