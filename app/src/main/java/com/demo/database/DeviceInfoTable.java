package com.demo.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.demo.model.DeviceInfoModel;

@Entity(tableName = AppDatabaseConstants.DEVICE_INFO_TABLE)
public class DeviceInfoTable {
    @PrimaryKey
    @ColumnInfo(name = AppDatabaseConstants.DEVICE_ID)
    private String deviceId;

    @NonNull
    @ColumnInfo(name = AppDatabaseConstants.DEVICE_NAME)
    private String deviceName;

    @NonNull
    @ColumnInfo(name = AppDatabaseConstants.DEVICE_TOKEN)
    private String deviceToken;

    @NonNull
    @ColumnInfo(name=AppDatabaseConstants.USER_ID)
    private int userId;

    public DeviceInfoTable(){}

    public DeviceInfoTable(DeviceInfoModel deviceInfoModel){
        this.deviceId= deviceInfoModel.getDeviceId();
        this.deviceName = deviceInfoModel.getDeviceName();
        this.deviceToken = deviceInfoModel.getDeviceToken();
        this.userId= deviceInfoModel.getUserId();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @NonNull
    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(@NonNull String deviceName) {
        this.deviceName = deviceName;
    }

    @NonNull
    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(@NonNull String deviceToken) {
        this.deviceToken = deviceToken;
    }

    @NonNull
    public int getUserId() {
        return userId;
    }

    public void setUserId(@NonNull int userId) {
        this.userId = userId;
    }
}
