package com.example.clinic.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clinic.R;
import com.example.clinic.adapter.PatientAdapter;
import com.example.clinic.contract.PatientListContract;
import com.example.clinic.domain.Patient;
import com.example.clinic.presenter.PatientListPresenter;

import java.util.ArrayList;
import java.util.List;

public class PatientListView extends AppCompatActivity implements PatientListContract.View {

    private PatientAdapter adapter;
    private ArrayList<Patient> list;
    private PatientListContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);

        presenter = new PatientListPresenter(this);
        list = new ArrayList<>();

        RecyclerView rv = findViewById(R.id.patient_recycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PatientAdapter(list);
        rv.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        presenter.loadPatients();
    }

    // Inflamos el menú superior (ActionBar)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_patients, menu);
        return true;
    }

    // Clicks del menú

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_hospitals_map) {
            startActivity(new Intent(this, MapHospitalsView.class));
            return true;
        } else if (id == R.id.action_patients) {
            return true;
        } else if (id == R.id.action_doctors) {
            startActivity(new Intent(this, DoctorListView.class));
            return true;
        } else if (id == R.id.action_register_patient) {
            startActivity(new Intent(this, RegisterPatientView.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Botón flotante o onClick del layout para registrar paciente
    public void openRegisterPatient(View v) {
        startActivity(new Intent(this, RegisterPatientView.class));
    }

    // Vista (MVP)
    @Override
    public void listPatients(List<Patient> patientList) {
        list.clear();                // <- clave: vaciar antes
        list.addAll(patientList);    //    y luego reemplazar
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSuccessMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
