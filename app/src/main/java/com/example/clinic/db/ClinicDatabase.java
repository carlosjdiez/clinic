package com.example.clinic.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.clinic.db.dao.DoctorDao;
import com.example.clinic.db.dao.PatientDao;
import com.example.clinic.db.entity.DoctorEntity;
import com.example.clinic.db.entity.PatientEntity;

@Database(
        entities = { PatientEntity.class, DoctorEntity.class },
        version = 2,
        exportSchema = false
)
@TypeConverters({Converters.class})
public abstract class ClinicDatabase extends RoomDatabase {
    private static volatile ClinicDatabase INSTANCE;

    public abstract PatientDao patientDao();
    public abstract DoctorDao doctorDao();

    public static ClinicDatabase getInstance(Context ctx){
        if (INSTANCE == null) {
            synchronized (ClinicDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    ctx.getApplicationContext(),
                                    ClinicDatabase.class,
                                    "clinic.db")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
