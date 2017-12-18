package com.gideondev.androidcomponenttut.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import com.gideondev.androidcomponenttut.Model.BorrowModel;
import com.gideondev.androidcomponenttut.Utils.DateConverter;
import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by ${ENNY} on 11/22/2017.
 */
//This class will be used to define all the queries we will perform on our database.
@Dao // to tell Room that this is a DAO class.
@TypeConverters(DateConverter.class)
public interface BorrowModelDao {
    @Query("select * from BorrowModel")
    LiveData<List<BorrowModel>> getAllBorrowedItems(); // to notify UI if there is any changes that is why LiveData is used

    @Query("select * from BorrowModel where id = :id")//get item  by Id/query by ID
    BorrowModel getItembyId(String id);

    @Insert(onConflict = REPLACE)
    void addBorrow(BorrowModel borrowModel);

    //@Insert (onConflict = REPLACE)
    //LiveData<BorrowModel> insert(BorrowModel borrowModel);

    @Delete
    void deleteBorrow(BorrowModel borrowModel);
}
