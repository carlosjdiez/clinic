package com.example.clinic.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;

public class Doctor implements Parcelable {
    private long id;
    private String name;
    private String surname;
    private String licenseNumber;
    private String specialty;
    private Date hiringDate;     // mapeado a LocalDate (yyyy-MM-dd)
    private boolean active;

    // opcional para mapa
    private Double latitude;
    private Double longitude;

    public Doctor(String name, String surname, String licenseNumber, String specialty, Date hiringDate, boolean active, Double latitude, Double longitude) {
        this.name = name;
        this.surname = surname;
        this.licenseNumber = licenseNumber;
        this.specialty = specialty;
        this.hiringDate = hiringDate;
        this.active = active;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    protected Doctor(Parcel in) {
        id = in.readLong();
        name = in.readString();
        surname = in.readString();
        licenseNumber = in.readString();
        specialty = in.readString();
        long hd = in.readLong();
        hiringDate = hd == -1 ? null : new Date(hd);
        active = in.readByte() != 0;
        if (in.readByte() == 0) latitude = null; else latitude = in.readDouble();
        if (in.readByte() == 0) longitude = null; else longitude = in.readDouble();
    }

    public static final Creator<Doctor> CREATOR = new Creator<Doctor>() {
        @Override public Doctor createFromParcel(Parcel in) { return new Doctor(in); }
        @Override public Doctor[] newArray(int size) { return new Doctor[size]; }
    };

    public long getId() { return id; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getLicenseNumber() { return licenseNumber; }
    public String getSpecialty() { return specialty; }
    public Date getHiringDate() { return hiringDate; }
    public boolean isActive() { return active; }
    public Double getLatitude() { return latitude; }
    public Double getLongitude() { return longitude; }

    public void setName(String name) { this.name = name; }
    public void setSurname(String surname) { this.surname = surname; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
    public void setHiringDate(Date hiringDate) { this.hiringDate = hiringDate; }
    public void setActive(boolean active) { this.active = active; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    @Override public int describeContents() { return 0; }
    @Override public void writeToParcel(@NonNull Parcel parcel, int flags) {
        parcel.writeLong(id);
        parcel.writeString(name);
        parcel.writeString(surname);
        parcel.writeString(licenseNumber);
        parcel.writeString(specialty);
        parcel.writeLong(hiringDate == null ? -1 : hiringDate.getTime());
        parcel.writeByte((byte) (active ? 1 : 0));
        if (latitude == null) { parcel.writeByte((byte)0); } else { parcel.writeByte((byte)1); parcel.writeDouble(latitude); }
        if (longitude == null) { parcel.writeByte((byte)0); } else { parcel.writeByte((byte)1); parcel.writeDouble(longitude); }
    }
}
