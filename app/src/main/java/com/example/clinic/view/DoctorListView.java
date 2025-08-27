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
import com.example.clinic.adapter.DoctorAdapter;
import com.example.clinic.contract.DoctorListContract;
import com.example.clinic.domain.Doctor;
import com.example.clinic.presenter.DoctorListPresenter;

import java.util.ArrayList;
import java.util.List;

public class DoctorListView extends AppCompatActivity implements DoctorListContract.View {

    private DoctorAdapter adapter;
    private ArrayList<Doctor> list;
    private DoctorListContract.Presenter presenter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        presenter = new DoctorListPresenter(this);
        list = new ArrayList<>();
        RecyclerView rv = findViewById(R.id.doctor_recycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DoctorAdapter(list);
        rv.setAdapter(adapter);
    }

    @Override protected void onResume() {
        super.onResume();
        list.clear();
        presenter.loadDoctors();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_doctors, menu);
        return true;
    }

    public void openRegisterDoctor(View v) { startActivity(new Intent(this, RegisterDoctorView.class)); }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_hospitals_map) {
            startActivity(new Intent(this, MapHospitalsView.class));
            return true;
        } else if (id == R.id.action_patients) {
            startActivity(new Intent(this, PatientListView.class));
            return true;
        } else if (id == R.id.action_doctors) {
            return true;
        } else if (id == R.id.action_register_doctor) {
            startActivity(new Intent(this, RegisterDoctorView.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override public void listDoctors(List<Doctor> doctorList) {
        list.clear();
        list.addAll(doctorList);
        adapter.notifyDataSetChanged();
    }
    @Override public void showErrorMessage(String message) { Toast.makeText(this, message, Toast.LENGTH_LONG).show(); }

    @Override public void showSuccessMessage(String message) { Toast.makeText(this, message, Toast.LENGTH_SHORT).show(); }
}
