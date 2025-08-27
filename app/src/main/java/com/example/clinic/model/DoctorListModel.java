package com.example.clinic.model;

import com.example.clinic.App;
import com.example.clinic.api.ClinicApi;
import com.example.clinic.api.ClinicApiInterface;
import com.example.clinic.contract.DoctorListContract;
import com.example.clinic.db.ClinicDatabase;
import com.example.clinic.db.entity.DoctorEntity;
import com.example.clinic.domain.Doctor;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorListModel implements DoctorListContract.Model {

    @Override
    public void loadDoctors(OnLoadDoctorsListener listener) {
        // 1) Mostrar datos locales de Room si existen
        List<DoctorEntity> local = ClinicDatabase.getInstance(App.getContext()).doctorDao().findAll();
        if (local != null && !local.isEmpty()) {
            listener.onLoadDoctorsSuccess(mapFromEntity(local));
        }

        // 2) API: refrescar Room + UI cuando responda
        ClinicApiInterface api = ClinicApi.buildInstance();
        api.getDoctors().enqueue(new Callback<List<Doctor>>() {
            @Override public void onResponse(Call<List<Doctor>> call, Response<List<Doctor>> resp) {
                if (resp.code() == 200 && resp.body() != null) {
                    List<DoctorEntity> toSave = new ArrayList<>();
                    for (Doctor d : resp.body()) toSave.add(mapToEntity(d));
                    ClinicDatabase db = ClinicDatabase.getInstance(App.getContext());
                    db.doctorDao().clear();
                    db.doctorDao().insertAll(toSave);
                    listener.onLoadDoctorsSuccess(resp.body());
                } else if (local == null || local.isEmpty()) {
                    listener.onLoadDoctorsError("API no disponible y sin datos locales.");
                }
            }
            @Override public void onFailure(Call<List<Doctor>> call, Throwable t) {
                if (local == null || local.isEmpty()) {
                    listener.onLoadDoctorsError("No hay datos locales y la API no responde.");
                }
            }
        });
    }

    // mapeo
    private static DoctorEntity mapToEntity(Doctor d){
        DoctorEntity e = new DoctorEntity();
        e.id = d.getId();
        e.name = d.getName();
        e.surname = d.getSurname();
        e.licenseNumber = d.getLicenseNumber();
        e.specialty = d.getSpecialty();
        e.hiringDate = d.getHiringDate();
        e.active = d.isActive();
        e.latitude = d.getLatitude();
        e.longitude = d.getLongitude();
        return e;
    }
    private static List<Doctor> mapFromEntity(List<DoctorEntity> list){
        List<Doctor> out = new ArrayList<>();
        for (DoctorEntity e : list) {
            Doctor d = new Doctor(e.name, e.surname, e.licenseNumber, e.specialty,
                    e.hiringDate, e.active, e.latitude, e.longitude);
            out.add(d);
        }
        return out;
    }
}
