package com.example.clinic.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.clinic.R;
import com.example.clinic.contract.EditDoctorContract;
import com.example.clinic.domain.Doctor;
import com.example.clinic.presenter.EditDoctorPresenter;
import com.example.clinic.util.DateUtil;

public class DoctorDetailView extends AppCompatActivity implements EditDoctorContract.View {

    private EditDoctorPresenter presenter;
    private long doctorId;
    private Doctor current;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);
        presenter = new EditDoctorPresenter(this);
        doctorId = getIntent().getLongExtra("doctorId", 0);
    }

    @Override protected void onResume() {
        super.onResume();
        presenter.loadDoctor(doctorId);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_edit) {
            Intent i = new Intent(this, EditDoctorView.class);
            i.putExtra("doctorId", doctorId);
            startActivity(i);
            return true;
        } else if (item.getItemId() == R.id.action_delete) {
            // Diálogo Sí/No para eliminar
            new AlertDialog.Builder(this)
                    .setMessage(R.string.lb_esta_seguro_eliminar)
                    .setPositiveButton(R.string.lb_si, (d, w) -> presenter.deleteDoctor(doctorId))
                    .setNegativeButton(R.string.lb_no, (d, w) -> d.dismiss())
                    .create()
                    .show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override public void showDoctor(Doctor d) {
        current = d;
        ((TextView) findViewById(R.id.tv_doctor_name)).setText(d.getName() + " " + d.getSurname());
        ((TextView) findViewById(R.id.tv_doctor_license)).setText(d.getLicenseNumber());
        ((TextView) findViewById(R.id.tv_doctor_specialty)).setText(d.getSpecialty());
        ((TextView) findViewById(R.id.tv_doctor_hiring)).setText(DateUtil.format(d.getHiringDate()));
        ((CheckBox) findViewById(R.id.cb_doctor_active_detail)).setChecked(d.isActive());
    }

    @Override public void showErrorMessage(String message) { Toast.makeText(this, message, Toast.LENGTH_LONG).show(); }
    @Override public void showSuccessMessage(String message) { Toast.makeText(this, message, Toast.LENGTH_SHORT).show(); }
    @Override public void close() { finish(); }
}
