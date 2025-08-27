package com.example.clinic.view;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.clinic.R;
import com.example.clinic.contract.EditPatientContract;
import com.example.clinic.domain.Patient;
import com.example.clinic.presenter.EditPatientPresenter;
import com.example.clinic.util.DateUtil;
import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.util.Date;

public class EditPatientView extends AppCompatActivity implements EditPatientContract.View {

    private EditPatientPresenter presenter;
    private long patientId;
    private Patient original;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_patient);
        presenter = new EditPatientPresenter(this);
        patientId = getIntent().getLongExtra("patientId", 0);

        findViewById(R.id.btn_patient_update).setOnClickListener(v -> update());
    }

    @Override protected void onResume() {
        super.onResume();
        presenter.loadPatient(patientId);
    }

    private void update() {
        try {
            String name = ((EditText) findViewById(R.id.et_patient_name)).getText().toString();
            String surname = ((EditText) findViewById(R.id.et_patient_surname)).getText().toString();
            String email = ((EditText) findViewById(R.id.et_patient_email)).getText().toString();
            Date birth = DateUtil.parse(((EditText) findViewById(R.id.et_patient_birth)).getText().toString());
            float weight = Float.parseFloat(((EditText) findViewById(R.id.et_patient_weight)).getText().toString());
            boolean active = ((CheckBox) findViewById(R.id.cb_patient_active)).isChecked();

            Patient updated = new Patient(name, surname, email, birth, active, weight,
                    original.getLatitude(), original.getLongitude());

            // Diálogo Sí/No para confirmar guardado
            new AlertDialog.Builder(this)
                    .setMessage(R.string.lb_esta_seguro_guardar)
                    .setPositiveButton(R.string.lb_si, (d, w) -> presenter.updatePatient(patientId, updated))
                    .setNegativeButton(R.string.lb_no, (d, w) -> d.dismiss())
                    .create()
                    .show();

        } catch (ParseException pe) {
            Snackbar.make(findViewById(R.id.btn_patient_update),
                    getString(R.string.error_date_format), Snackbar.LENGTH_LONG).show();
        } catch (NumberFormatException nfe) {
            Snackbar.make(findViewById(R.id.btn_patient_update),
                    getString(R.string.error_generic), Snackbar.LENGTH_LONG).show();
        }
    }

    @Override public void showPatient(Patient p) {
        original = p;
        ((EditText) findViewById(R.id.et_patient_name)).setText(p.getName());
        ((EditText) findViewById(R.id.et_patient_surname)).setText(p.getSurname());
        ((EditText) findViewById(R.id.et_patient_email)).setText(p.getEmail());
        ((EditText) findViewById(R.id.et_patient_birth)).setText(DateUtil.format(p.getBirthDate()));
        ((EditText) findViewById(R.id.et_patient_weight)).setText(String.valueOf(p.getWeightKg()));
        ((CheckBox) findViewById(R.id.cb_patient_active)).setChecked(p.isActive());
    }

    @Override public void showErrorMessage(String message) { Snackbar.make(findViewById(R.id.btn_patient_update), message, Snackbar.LENGTH_LONG).show(); }
    @Override public void showSuccessMessage(String message) { Snackbar.make(findViewById(R.id.btn_patient_update), message, Snackbar.LENGTH_SHORT).show(); }
    @Override public void close() { finish(); }
}
