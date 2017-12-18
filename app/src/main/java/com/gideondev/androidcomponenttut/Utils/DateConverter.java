package com.gideondev.androidcomponenttut.Utils;

import android.arch.persistence.room.TypeConverter;
import java.util.Date;

/**
 * Created by ${ENNY} on 11/22/2017.
 */

public class DateConverter {
    //the class just converts Date to Long and vice versa.
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
