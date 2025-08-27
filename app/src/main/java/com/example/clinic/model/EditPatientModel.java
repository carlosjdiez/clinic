package com.example.clinic.model;

import com.example.clinic.api.ClinicApi;
import com.example.clinic.api.ClinicApiInterface;
import com.example.clinic.contract.EditPatientContract;
import com.example.clinic.domain.Patient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPatientModel implements EditPatientContract.Model {
    @Override
    public void getPatient(long id, OnLoadPatientListener listener) {
        ClinicApiInterface api = ClinicApi.buildInstance();
        api.getPatient(id).enqueue(new Callback<Patient>() {
            @Override public void onResponse(Call<Patient> call, Response<Patient> response) {
                if (response.code() == 200) listener.onLoadPatientSuccess(response.body());
                else listener.onLoadPatientError("No se pudo cargar el paciente: " + response.code());
            }
            @Override public void onFailure(Call<Patient> call, Throwable t) {
                listener.onLoadPatientError("Fallo de conexión");
            }
        });
    }

    @Override
    public void updatePatient(long id, Patient p, OnUpdatePatientListener listener) {
        ClinicApiInterface api = ClinicApi.buildInstance();
        api.updatePatient(id, p).enqueue(new Callback<Patient>() {
            @Override public void onResponse(Call<Patient> call, Response<Patient> response) {
                if (response.code() == 200) listener.onUpdatePatientSuccess(response.body());
                else listener.onUpdatePatientError("No se pudo actualizar: " + response.code());
            }
            @Override public void onFailure(Call<Patient> call, Throwable t) {
                listener.onUpdatePatientError("Fallo de conexión");
            }
        });
    }

    @Override
    public void deletePatient(long id, OnDeletePatientListener listener) {
        ClinicApiInterface api = ClinicApi.buildInstance();
        api.deletePatient(id).enqueue(new Callback<Void>() {
            @Override public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 204 || response.code() == 200) listener.onDeletePatientSuccess();
                else listener.onDeletePatientError("No se pudo eliminar: " + response.code());
            }
            @Override public void onFailure(Call<Void> call, Throwable t) {
                listener.onDeletePatientError("Fallo de conexión");
            }
        });
    }
}
