package com.example.clinic.view;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.clinic.R;
import com.example.clinic.contract.EditDoctorContract;
import com.example.clinic.domain.Doctor;
import com.example.clinic.presenter.EditDoctorPresenter;
import com.example.clinic.util.DateUtil;
import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.util.Date;

public class EditDoctorView extends AppCompatActivity implements EditDoctorContract.View {

    private EditDoctorPresenter presenter;
    private long doctorId;
    private Doctor original;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_doctor);
        presenter = new EditDoctorPresenter(this);
        doctorId = getIntent().getLongExtra("doctorId", 0);

        findViewById(R.id.btn_doctor_update).setOnClickListener(v -> update());
    }

    @Override protected void onResume() {
        super.onResume();
        presenter.loadDoctor(doctorId);
    }

    private void update() {
        try {
            String name = ((EditText) findViewById(R.id.et_doctor_name)).getText().toString();
            String surname = ((EditText) findViewById(R.id.et_doctor_surname)).getText().toString();
            String lic = ((EditText) findViewById(R.id.et_doctor_license)).getText().toString();
            String spec = ((EditText) findViewById(R.id.et_doctor_specialty)).getText().toString();
            Date hiring = DateUtil.parse(((EditText) findViewById(R.id.et_doctor_hiring)).getText().toString());
            boolean active = ((CheckBox) findViewById(R.id.cb_doctor_active)).isChecked();

            Doctor d = new Doctor(name, surname, lic, spec, hiring, active,
                    original.getLatitude(), original.getLongitude());

            // Diálogo Sí/No para confirmar guardado
            new AlertDialog.Builder(this)
                    .setMessage(R.string.lb_esta_seguro_guardar)
                    .setPositiveButton(R.string.lb_si, (dlg, w) -> presenter.updateDoctor(doctorId, d))
                    .setNegativeButton(R.string.lb_no, (dlg, w) -> dlg.dismiss())
                    .create()
                    .show();

        } catch (ParseException pe) {
            Snackbar.make(findViewById(R.id.btn_doctor_update),
                    getString(R.string.error_date_format), Snackbar.LENGTH_LONG).show();
        }
    }

    @Override public void showDoctor(Doctor d) {
        original = d;
        ((EditText) findViewById(R.id.et_doctor_name)).setText(d.getName());
        ((EditText) findViewById(R.id.et_doctor_surname)).setText(d.getSurname());
        ((EditText) findViewById(R.id.et_doctor_license)).setText(d.getLicenseNumber());
        ((EditText) findViewById(R.id.et_doctor_specialty)).setText(d.getSpecialty());
        ((EditText) findViewById(R.id.et_doctor_hiring)).setText(DateUtil.format(d.getHiringDate()));
        ((CheckBox) findViewById(R.id.cb_doctor_active)).setChecked(d.isActive());
    }

    @Override public void showErrorMessage(String message) { Snackbar.make(findViewById(R.id.btn_doctor_update), message, Snackbar.LENGTH_LONG).show(); }
    @Override public void showSuccessMessage(String message) { Snackbar.make(findViewById(R.id.btn_doctor_update), message, Snackbar.LENGTH_SHORT).show(); }
    @Override public void close() { finish(); }
}
