package com.example.clinic.db;

import androidx.room.TypeConverter;
import java.util.Date;

public class Converters {
    @TypeConverter public static Date fromTimestamp(Long v){ return v==null?null:new Date(v); }
    @TypeConverter public static Long dateToTimestamp(Date d){ return d==null?null:d.getTime(); }
}
