package com.demo.repository;

import android.content.Context;
import android.os.AsyncTask;

import com.demo.dao.UserDao;
import com.demo.database.UserRoomDatabase;
import com.demo.database.UserTable;
import com.demo.model.UserModel;
import com.demo.presenter.UserRepositoryListener;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private UserDao userDao;
    private List<UserTable> userList;
    private List<UserModel> mAllUser;
    private UserModel userModel;
    private Context mContext;
    private UserRepositoryListener listener;


    public UserRepository(Context mContext){
        this.mContext = mContext;
        UserRoomDatabase userRoomDatabase= UserRoomDatabase.getInstance(mContext);
        userDao = userRoomDatabase.userDao();
    }

    public UserRepository(Context mContext,UserRepositoryListener listener){
        this.mContext = mContext;
        this.listener = listener;
        UserRoomDatabase userRoomDatabase= UserRoomDatabase.getInstance(mContext);
        userDao = userRoomDatabase.userDao();
    }


    public List<UserTable> getUserList() {
        if (userDao != null)
            userList = userDao.getAllUsers();
        return userList;
    }

    public List<UserModel> getAllUser() {
        if (mAllUser == null)
            mAllUser = new ArrayList<>();
        if (userDao != null)
            mAllUser.addAll(userDao.getUsers());
        return mAllUser;
    }


    public UserModel getExistUser(String email, String pass){
        if (userDao != null){
            userModel = new UserModel();
            userModel = userDao.getExistUserByEmail(email,pass);
        }
        return userModel;
    }

    public void insert(UserModel userTable){
        new InsertNewUser(listener,userDao).execute(userTable);
    }

    public void getExistUser(String email) {
        if (userDao != null){
            new GetExistUser(listener,userDao).execute(email);
        }
    }

    private static class InsertNewUser extends AsyncTask<UserModel,Void,String> {

        private UserDao userDao;
        private UserRepositoryListener listener;

        public InsertNewUser(UserRepositoryListener listener,UserDao userDao) {
            this.listener = listener;
            this.userDao = userDao;
        }

        @Override
        protected String doInBackground(UserModel... userTables) {

            try {
                UserTable userTable = new UserTable(userTables[0]);
                userDao.insert(userTable);
                return "SuccessFully inserted.";
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            listener.onGetUser(null);
        }
    }


    private static class GetExistUser extends AsyncTask<String,Void,UserModel>{

        private UserDao userDao;
        private UserRepositoryListener listener;

        public GetExistUser(UserRepositoryListener listener, UserDao userDao) {
            this.listener = listener;
            this.userDao = userDao;
        }

        @Override
        protected UserModel doInBackground(String... voids) {
            try{
                UserModel userModel = userDao.getExistUserByEmail(voids[0]);
                return userModel;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }

        }


        @Override
        protected void onPostExecute(UserModel userModel) {
            super.onPostExecute(userModel);
            listener.onGetUser(userModel);
        }
    }
}