package com.gideondev.androidcomponenttut.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.gideondev.androidcomponenttut.Model.BorrowModel;
import com.gideondev.androidcomponenttut.Model.User;

/**
 * Created by ${ENNY} on 11/24/2017.
 */
//  RoomDatabase class
//  An array of the Entity classes(the tables)
// The database version which is just an integer.
@Database(entities = {BorrowModel.class,User.class}, version = 2,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

//create database
    public static AppDatabase getDatabase(Context context) {
        //create the database using
        if (INSTANCE == null) {
            INSTANCE =
                Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "borrow_db")
                    .allowMainThreadQueries().build();
                    //.build();
        }
        return INSTANCE;
    }

    public abstract BorrowModelDao itemAndPersonModel();
    public abstract UserModelDao getUserDao();
}
