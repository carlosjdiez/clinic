package com.example.clinic.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.clinic.db.entity.PatientEntity;
import java.util.List;

@Dao
public interface PatientDao {
    @Query("SELECT * FROM patients ORDER BY id") List<PatientEntity> findAll();
    @Insert(onConflict = OnConflictStrategy.REPLACE) void insertAll(List<PatientEntity> items);
    @Query("DELETE FROM patients") void clear();
}
