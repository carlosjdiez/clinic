package com.example.clinic.db.entity;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(tableName = "doctors")
public class DoctorEntity {
    @PrimaryKey public long id;
    public String name;
    public String surname;
    public String licenseNumber;
    public String specialty;
    public Date hiringDate;
    public boolean active;
    @Nullable public Double latitude;
    @Nullable public Double longitude;
}
