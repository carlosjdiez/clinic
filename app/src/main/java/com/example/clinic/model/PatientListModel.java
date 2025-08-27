package com.example.clinic.model;

import com.example.clinic.App;
import com.example.clinic.api.ClinicApi;
import com.example.clinic.api.ClinicApiInterface;
import com.example.clinic.contract.PatientListContract;
import com.example.clinic.db.ClinicDatabase;
import com.example.clinic.db.entity.PatientEntity;
import com.example.clinic.domain.Patient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientListModel implements PatientListContract.Model {

    @Override
    public void loadPatients(OnLoadPatientsListener listener) {
        // 1) Mostrar al instante lo que haya en el móvil (Room)
        List<PatientEntity> local = ClinicDatabase.getInstance(App.getContext())
                .patientDao().findAll();
        if (local != null && !local.isEmpty()) {
            listener.onLoadPatientsSuccess(mapFromEntity(local));
        }

        // 2) Llamar a la API; si responde, refrescar Room y UI
        ClinicApiInterface api = ClinicApi.buildInstance();
        api.getPatients().enqueue(new Callback<List<Patient>>() {
            @Override public void onResponse(Call<List<Patient>> call, Response<List<Patient>> resp) {
                if (resp.code() == 200 && resp.body() != null) {
                    // guardar todo en Room
                    List<PatientEntity> toSave = new ArrayList<>();
                    for (Patient p : resp.body()) toSave.add(mapToEntity(p));
                    ClinicDatabase db = ClinicDatabase.getInstance(App.getContext());
                    db.patientDao().clear();
                    db.patientDao().insertAll(toSave);

                    listener.onLoadPatientsSuccess(resp.body());
                } else if (local == null || local.isEmpty()) {
                    listener.onLoadPatientsError("API no disponible y sin datos locales.");
                }
            }
            @Override public void onFailure(Call<List<Patient>> call, Throwable t) {
                if (local == null || local.isEmpty())
                    listener.onLoadPatientsError("No hay datos locales y la API no responde.");
            }
        });
    }

    // mapeos
    private static PatientEntity mapToEntity(Patient p){
        PatientEntity e = new PatientEntity();
        e.id = p.getId();
        e.name = p.getName();
        e.surname = p.getSurname();
        e.email = p.getEmail();
        e.birthDate = p.getBirthDate();
        e.active = p.isActive();
        e.weightKg = p.getWeightKg();
        e.latitude = p.getLatitude();
        e.longitude = p.getLongitude();
        return e;
    }
    private static List<Patient> mapFromEntity(List<PatientEntity> list){
        List<Patient> out = new ArrayList<>();
        for (PatientEntity e : list) {
            Patient p = new Patient(e.name, e.surname, e.email, e.birthDate, e.active, e.weightKg, e.latitude, e.longitude);
            out.add(p);
        }
        return out;
    }
}
