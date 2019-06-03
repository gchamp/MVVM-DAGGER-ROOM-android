package com.liveservey.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.liveservey.dao.UserDao;

@Database(entities = {UserTable.class},version = 1,exportSchema = false)
public abstract class UserRoomDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    private static volatile UserRoomDatabase instance;

    public static UserRoomDatabase getInstance(Context mContext){
        if (instance==null){
            synchronized (UserRoomDatabase.class){
                instance = Room.databaseBuilder(mContext,UserRoomDatabase.class,AppDatabaseConstants.DATABASE_NAME).build();
            }

        }
        return instance;
    }



}
