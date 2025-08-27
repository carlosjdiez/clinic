package com.example.clinic.view;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clinic.R;
import com.example.clinic.contract.RegisterDoctorContract;
import com.example.clinic.domain.Doctor;
import com.example.clinic.presenter.RegisterDoctorPresenter;
import com.example.clinic.util.DateUtil;
import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.util.Date;

public class RegisterDoctorView extends AppCompatActivity implements RegisterDoctorContract.View {

    private RegisterDoctorPresenter presenter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_doctor);
        presenter = new RegisterDoctorPresenter(this);
        findViewById(R.id.btn_doctor_add).setOnClickListener(v -> register());
    }

    private void register() {
        try {
            String name = ((EditText) findViewById(R.id.et_doctor_name)).getText().toString();
            String surname = ((EditText) findViewById(R.id.et_doctor_surname)).getText().toString();
            String lic = ((EditText) findViewById(R.id.et_doctor_license)).getText().toString();
            String spec = ((EditText) findViewById(R.id.et_doctor_specialty)).getText().toString();
            Date hiring = DateUtil.parse(((EditText) findViewById(R.id.et_doctor_hiring)).getText().toString());
            boolean active = ((CheckBox) findViewById(R.id.cb_doctor_active)).isChecked();

            Doctor d = new Doctor(name, surname, lic, spec, hiring, active, null, null);
            presenter.registerDoctor(d);
        } catch (ParseException pe) {
            Snackbar.make(findViewById(R.id.btn_doctor_add), getString(R.string.error_date_format), Snackbar.LENGTH_LONG).show();
        }
    }

    @Override public void showErrorMessage(String message) {
        Snackbar.make(findViewById(R.id.btn_doctor_add), message, Snackbar.LENGTH_LONG).show();
    }
    @Override public void showSuccessMessage(String message) {
        Snackbar.make(findViewById(R.id.btn_doctor_add), message, Snackbar.LENGTH_SHORT).show();
        finish();
    }
}
