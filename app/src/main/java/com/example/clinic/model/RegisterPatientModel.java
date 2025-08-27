package com.example.clinic.model;

import com.example.clinic.api.ClinicApi;
import com.example.clinic.api.ClinicApiInterface;
import com.example.clinic.contract.RegisterPatientContract;
import com.example.clinic.domain.Patient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPatientModel implements RegisterPatientContract.Model {
    @Override
    public void registerPatient(Patient p, OnRegisterPatientListener listener) {
        ClinicApiInterface api = ClinicApi.buildInstance();
        api.addPatient(p).enqueue(new Callback<Patient>() {
            @Override public void onResponse(Call<Patient> call, Response<Patient> response) {
                switch (response.code()) {
                    case 201: listener.onRegisterPatientSuccess(response.body()); break;
                    case 400: listener.onRegisterPatientError("Error validando la petición: " + response.message()); break;
                    case 500: listener.onRegisterPatientError("Error interno en la API: " + response.message()); break;
                    default:   listener.onRegisterPatientError("Error: " + response.code());
                }
            }
            @Override public void onFailure(Call<Patient> call, Throwable t) {
                listener.onRegisterPatientError("No se puede conectar con el origen de datos. Inténtalo de nuevo.");
            }
        });
    }
}
