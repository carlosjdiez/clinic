package com.example.clinic.api;

import com.example.clinic.domain.Patient;
import com.example.clinic.domain.Doctor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface ClinicApiInterface {

    // Patients
    @GET("patients")
    Call<List<Patient>> getPatients();

    @GET("patients/{patientId}")
    Call<Patient> getPatient(@Path("patientId") long id);

    @POST("patients")
    Call<Patient> addPatient(@Body Patient patient);

    @PUT("patients/{patientId}")
    Call<Patient> updatePatient(@Path("patientId") long id, @Body Patient patient);

    @DELETE("patients/{patientId}")
    Call<Void> deletePatient(@Path("patientId") long id);

    // Doctors
    @GET("doctors")
    Call<List<Doctor>> getDoctors();

    @GET("doctors/{doctorId}")
    Call<Doctor> getDoctor(@Path("doctorId") long id);

    @POST("doctors")
    Call<Doctor> addDoctor(@Body Doctor doctor);

    @PUT("doctors/{doctorId}")
    Call<Doctor> updateDoctor(@Path("doctorId") long id, @Body Doctor doctor);

    @DELETE("doctors/{doctorId}")
    Call<Void> deleteDoctor(@Path("doctorId") long id);
}
