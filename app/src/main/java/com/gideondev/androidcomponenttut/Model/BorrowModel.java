package com.gideondev.androidcomponenttut.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.gideondev.androidcomponenttut.BR;
import com.gideondev.androidcomponenttut.Utils.DateConverter;
import java.util.Date;
import java.util.Observable;

/**
 * Created by ${ENNY} on 11/22/2017.
 */
@Entity //this is to tell the database to use this class table
public class BorrowModel extends BaseObservable {
    @PrimaryKey(autoGenerate = true) // will serve as a primary key for the table
    public int id;
    public String itemName;
    public String personName;
    @TypeConverters(DateConverter.class) //SQL cannot store data types like Date by default,
    // That’s why we need a way to convert it into a compatible data type to store it in the database
    //@TypeConverters to specify the converter for the borrowDate attribute
    private Date borrowDate;

    public BorrowModel(String itemName, String personName, Date borrowDate) {
        this.itemName = itemName;
        this.personName = personName;
        this.borrowDate = borrowDate;
    }

    @Bindable
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
        notifyPropertyChanged(BR.itemName);
    }
    @Bindable
    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
        notifyPropertyChanged(BR.personName);
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BorrowModel that = (BorrowModel) o;

        if (id != that.id) return false;
        if (itemName != null ? !itemName.equals(that.itemName) : that.itemName != null)
            return false;
        if (personName != null ? !personName.equals(that.personName) : that.personName != null) {
            return false;
        }
        return borrowDate != null ? borrowDate.equals(that.borrowDate) : that.borrowDate == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        result = 31 * result + (personName != null ? personName.hashCode() : 0);
        result = 31 * result + (borrowDate != null ? borrowDate.hashCode() : 0);
        return result;
    }
}
