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
import com.example.clinic.contract.EditPatientContract;
import com.example.clinic.domain.Patient;
import com.example.clinic.presenter.EditPatientPresenter;
import com.example.clinic.util.DateUtil;

public class PatientDetailView extends AppCompatActivity implements EditPatientContract.View {

    private EditPatientPresenter presenter;
    private long patientId;
    private Patient current;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_detail);
        presenter = new EditPatientPresenter(this);
        patientId = getIntent().getLongExtra("patientId", 0);
    }

    @Override protected void onResume() {
        super.onResume();
        presenter.loadPatient(patientId);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_edit) {
            Intent i = new Intent(this, EditPatientView.class);
            i.putExtra("patientId", patientId);
            startActivity(i);
            return true;
        } else if (item.getItemId() == R.id.action_delete) {
            // Diálogo Sí/No para eliminar
            new AlertDialog.Builder(this)
                    .setMessage(R.string.lb_esta_seguro_eliminar)
                    .setPositiveButton(R.string.lb_si, (d, w) -> presenter.deletePatient(patientId))
                    .setNegativeButton(R.string.lb_no, (d, w) -> d.dismiss())
                    .create()
                    .show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override public void showPatient(Patient p) {
        current = p;
        ((TextView) findViewById(R.id.tv_detail_name)).setText(p.getName() + " " + p.getSurname());
        ((TextView) findViewById(R.id.tv_detail_email)).setText(p.getEmail());
        ((TextView) findViewById(R.id.tv_detail_birth)).setText(DateUtil.format(p.getBirthDate()));
        ((TextView) findViewById(R.id.tv_detail_weight)).setText(String.valueOf(p.getWeightKg()));
        ((CheckBox) findViewById(R.id.cb_detail_active)).setChecked(p.isActive());
    }

    @Override public void showErrorMessage(String message) { Toast.makeText(this, message, Toast.LENGTH_LONG).show(); }
    @Override public void showSuccessMessage(String message) { Toast.makeText(this, message, Toast.LENGTH_SHORT).show(); }
    @Override public void close() { finish(); }
}
