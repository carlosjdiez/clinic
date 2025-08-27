package com.example.clinic.model;

import com.example.clinic.api.ClinicApi;
import com.example.clinic.api.ClinicApiInterface;
import com.example.clinic.contract.EditDoctorContract;
import com.example.clinic.domain.Doctor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditDoctorModel implements EditDoctorContract.Model {
    @Override
    public void getDoctor(long id, OnLoadDoctorListener listener) {
        ClinicApiInterface api = ClinicApi.buildInstance();
        api.getDoctor(id).enqueue(new Callback<Doctor>() {
            @Override public void onResponse(Call<Doctor> call, Response<Doctor> response) {
                if (response.code() == 200) listener.onLoadDoctorSuccess(response.body());
                else listener.onLoadDoctorError("No se pudo cargar el doctor: " + response.code());
            }
            @Override public void onFailure(Call<Doctor> call, Throwable t) {
                listener.onLoadDoctorError("Fallo de conexión");
            }
        });
    }

    @Override
    public void updateDoctor(long id, Doctor d, OnUpdateDoctorListener listener) {
        ClinicApiInterface api = ClinicApi.buildInstance();
        api.updateDoctor(id, d).enqueue(new Callback<Doctor>() {
            @Override public void onResponse(Call<Doctor> call, Response<Doctor> response) {
                if (response.code() == 200) listener.onUpdateDoctorSuccess(response.body());
                else listener.onUpdateDoctorError("No se pudo actualizar: " + response.code());
            }
            @Override public void onFailure(Call<Doctor> call, Throwable t) {
                listener.onUpdateDoctorError("Fallo de conexión");
            }
        });
    }

    @Override
    public void deleteDoctor(long id, OnDeleteDoctorListener listener) {
        ClinicApiInterface api = ClinicApi.buildInstance();
        api.deleteDoctor(id).enqueue(new Callback<Void>() {
            @Override public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 204 || response.code() == 200) listener.onDeleteDoctorSuccess();
                else listener.onDeleteDoctorError("No se pudo eliminar: " + response.code());
            }
            @Override public void onFailure(Call<Void> call, Throwable t) {
                listener.onDeleteDoctorError("Fallo de conexión");
            }
        });
    }
}
