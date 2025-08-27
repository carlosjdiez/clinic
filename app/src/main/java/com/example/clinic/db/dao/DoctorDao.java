package com.example.clinic.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.clinic.db.entity.DoctorEntity;
import java.util.List;

@Dao
public interface DoctorDao {
    @Query("SELECT * FROM doctors ORDER BY id")
    List<DoctorEntity> findAll();

    @Query("SELECT * FROM doctors WHERE id = :id LIMIT 1")
    DoctorEntity findById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<DoctorEntity> items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOne(DoctorEntity item);

    @Query("DELETE FROM doctors WHERE id = :id")
    void deleteById(long id);

    @Query("DELETE FROM doctors")
    void clear();
}
