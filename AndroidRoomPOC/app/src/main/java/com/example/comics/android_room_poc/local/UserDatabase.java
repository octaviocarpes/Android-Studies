package com.example.comics.android_room_poc.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.comics.android_room_poc.model.User;

import static com.example.comics.android_room_poc.local.UserDatabase.DATABASE_VERISON;

@Database(entities = User.class, version = DATABASE_VERISON)
public abstract class UserDatabase extends RoomDatabase{

    public static final int DATABASE_VERISON = 1;
    public static final String DATABASE_NAME = "Database-Room";


    public abstract UserDAO userDAO();

    public static UserDatabase mInstance;

    public static UserDatabase getmInstance(Context context) {
        if (mInstance == null) {
            mInstance = Room.databaseBuilder(context, UserDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return mInstance;
    }

}
