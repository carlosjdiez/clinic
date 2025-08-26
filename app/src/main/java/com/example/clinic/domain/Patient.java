package com.example.clinic.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;

public class Patient implements Parcelable {
    private long id;
    private String name;
    private String surname;
    private String email;
    private Date birthDate;      // mapeado a LocalDate (yyyy-MM-dd)
    private boolean active;
    private float weightKg;

    // opcional
    private Double latitude;
    private Double longitude;

    public Patient(String name, String surname, String email, Date birthDate, boolean active, float weightKg, Double latitude, Double longitude) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthDate = birthDate;
        this.active = active;
        this.weightKg = weightKg;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    protected Patient(Parcel in) {
        id = in.readLong();
        name = in.readString();
        surname = in.readString();
        email = in.readString();
        long bd = in.readLong();
        birthDate = bd == -1 ? null : new Date(bd);
        active = in.readByte() != 0;
        weightKg = in.readFloat();
        if (in.readByte() == 0) latitude = null; else latitude = in.readDouble();
        if (in.readByte() == 0) longitude = null; else longitude = in.readDouble();
    }

    public static final Creator<Patient> CREATOR = new Creator<Patient>() {
        @Override public Patient createFromParcel(Parcel in) { return new Patient(in); }
        @Override public Patient[] newArray(int size) { return new Patient[size]; }
    };

    public long getId() { return id; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getEmail() { return email; }
    public Date getBirthDate() { return birthDate; }
    public boolean isActive() { return active; }
    public float getWeightKg() { return weightKg; }
    public Double getLatitude() { return latitude; }
    public Double getLongitude() { return longitude; }

    public void setName(String name) { this.name = name; }
    public void setSurname(String surname) { this.surname = surname; }
    public void setEmail(String email) { this.email = email; }
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }
    public void setActive(boolean active) { this.active = active; }
    public void setWeightKg(float weightKg) { this.weightKg = weightKg; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    @Override public int describeContents() { return 0; }
    @Override public void writeToParcel(@NonNull Parcel parcel, int flags) {
        parcel.writeLong(id);
        parcel.writeString(name);
        parcel.writeString(surname);
        parcel.writeString(email);
        parcel.writeLong(birthDate == null ? -1 : birthDate.getTime());
        parcel.writeByte((byte) (active ? 1 : 0));
        parcel.writeFloat(weightKg);
        if (latitude == null) { parcel.writeByte((byte)0); } else { parcel.writeByte((byte)1); parcel.writeDouble(latitude); }
        if (longitude == null) { parcel.writeByte((byte)0); } else { parcel.writeByte((byte)1); parcel.writeDouble(longitude); }
    }
}
