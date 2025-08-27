package com.example.clinic.model;

import com.example.clinic.api.ClinicApi;
import com.example.clinic.api.ClinicApiInterface;
import com.example.clinic.contract.RegisterDoctorContract;
import com.example.clinic.domain.Doctor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterDoctorModel implements RegisterDoctorContract.Model {
    @Override
    public void registerDoctor(Doctor d, OnRegisterDoctorListener listener) {
        ClinicApiInterface api = ClinicApi.buildInstance();
        api.addDoctor(d).enqueue(new Callback<Doctor>() {
            @Override public void onResponse(Call<Doctor> call, Response<Doctor> response) {
                switch (response.code()) {
                    case 201: listener.onRegisterDoctorSuccess(response.body()); break;
                    case 400: listener.onRegisterDoctorError("Error validando la petición: " + response.message()); break;
                    case 500: listener.onRegisterDoctorError("Error interno en la API: " + response.message()); break;
                    default:   listener.onRegisterDoctorError("Error: " + response.code());
                }
            }
            @Override public void onFailure(Call<Doctor> call, Throwable t) {
                listener.onRegisterDoctorError("No se puede conectar con el origen de datos.");
            }
        });
    }
}
