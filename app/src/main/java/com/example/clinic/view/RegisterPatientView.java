package com.example.clinic.view;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clinic.R;
import com.example.clinic.contract.RegisterPatientContract;
import com.example.clinic.domain.Patient;
import com.example.clinic.presenter.RegisterPatientPresenter;
import com.example.clinic.util.DateUtil;
import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.util.Date;

public class RegisterPatientView extends AppCompatActivity implements RegisterPatientContract.View {

    private RegisterPatientPresenter presenter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_patient);
        presenter = new RegisterPatientPresenter(this);
        findViewById(R.id.btn_patient_add).setOnClickListener(v -> register());
    }

    private void register() {
        try {
            String name = ((EditText) findViewById(R.id.et_patient_name)).getText().toString();
            String surname = ((EditText) findViewById(R.id.et_patient_surname)).getText().toString();
            String email = ((EditText) findViewById(R.id.et_patient_email)).getText().toString();
            Date birth = DateUtil.parse(((EditText) findViewById(R.id.et_patient_birth)).getText().toString());
            float weight = Float.parseFloat(((EditText) findViewById(R.id.et_patient_weight)).getText().toString());
            boolean active = ((CheckBox) findViewById(R.id.cb_patient_active)).isChecked();

            Patient p = new Patient(name, surname, email, birth, active, weight, null, null);
            presenter.registerPatient(p);
        } catch (ParseException pe) {
            Snackbar.make(findViewById(R.id.btn_patient_add), getString(R.string.error_date_format), Snackbar.LENGTH_LONG).show();
        } catch (NumberFormatException nfe) {
            Snackbar.make(findViewById(R.id.btn_patient_add), getString(R.string.error_generic), Snackbar.LENGTH_LONG).show();
        }
    }

    @Override public void showErrorMessage(String message) {
        Snackbar.make(findViewById(R.id.btn_patient_add), message, Snackbar.LENGTH_LONG).show();
    }
    @Override public void showSuccessMessage(String message) {
        Snackbar.make(findViewById(R.id.btn_patient_add), message, Snackbar.LENGTH_SHORT).show();
        finish();
    }
}
