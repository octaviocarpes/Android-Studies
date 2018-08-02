package com.example.comics.android_room_poc.database;

import com.example.comics.android_room_poc.model.User;

import java.util.List;

import io.reactivex.Flowable;

public class UserRepository implements IUserDataSource {

    private IUserDataSource mLocaDataSource;
    private static UserRepository mInstance;

    public UserRepository(IUserDataSource mLocaDataSource) {
        this.mLocaDataSource = mLocaDataSource;
    }

    public static UserRepository getInstance(IUserDataSource mLocaDataSource) {
        if (mInstance == null) {
            mInstance = new UserRepository(mLocaDataSource);
        }
        return mInstance;
    }

    @Override
    public Flowable<User> getUserById(int userId) {
        return mLocaDataSource.getUserById(userId);
    }

    @Override
    public Flowable<List<User>> getAllUsers() {
        return mLocaDataSource.getAllUsers();
    }

    @Override
    public void insertUser(User... users) {
        mLocaDataSource.insertUser(users);
    }

    @Override
    public void updateUser(User... users) {
        mLocaDataSource.updateUser(users);
    }

    @Override
    public void deleteUser(User user) {
        mLocaDataSource.deleteUser(user);
    }

    @Override
    public void deleteAllUsers() {
        mLocaDataSource.deleteAllUsers();
    }
}
