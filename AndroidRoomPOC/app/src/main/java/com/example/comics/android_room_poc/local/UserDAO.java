package com.example.comics.android_room_poc.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.comics.android_room_poc.model.User;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface UserDAO {

    @Query("Select * from users where id = :userId")
    Flowable<User> getUserById(int userId);

    @Query("Select * from users")
    Flowable<List<User>> getAllUsers();

    @Insert
    void insertUser(User... users);

    @Update
    void updateUser(User... users);

    @Delete
    void deleteUser(User user);

    @Query("Delete from users")
    void deleteAllUsers();
}

