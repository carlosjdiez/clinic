package com.example.clinic.db.entity;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(tableName = "patients")
public class PatientEntity {
    @PrimaryKey public long id;
    public String name;
    public String surname;
    public String email;
    public Date birthDate;
    public boolean active;
    public float weightKg;
    @Nullable public Double latitude;
    @Nullable public Double longitude;
}
