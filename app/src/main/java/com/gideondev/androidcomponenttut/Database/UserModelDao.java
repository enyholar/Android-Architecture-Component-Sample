package com.gideondev.androidcomponenttut.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.gideondev.androidcomponenttut.Model.User;
import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by ${ENNY} on 11/22/2017.
 */
@Dao
public interface UserModelDao {
    @Query("select * from  User ")
    LiveData<List<User>> getUserlist();

    @Query("select * from User where userName =  :userName")
    User getByUsername(String userName);

    @Insert(onConflict = REPLACE)
    void AddUser(User user);

    @Delete
    void DeleteUser(User user);
}
