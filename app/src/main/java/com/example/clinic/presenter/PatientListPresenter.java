package com.example.clinic.presenter;

import com.example.clinic.contract.PatientListContract;
import com.example.clinic.domain.Patient;
import com.example.clinic.model.PatientListModel;

import java.util.List;

public class PatientListPresenter implements PatientListContract.Presenter, PatientListContract.Model.OnLoadPatientsListener {
    private final PatientListContract.View view;
    private final PatientListContract.Model model;

    public PatientListPresenter(PatientListContract.View view) {
        this.view = view;
        this.model = new PatientListModel();
    }

    @Override public void loadPatients() { model.loadPatients(this); }

    @Override public void onLoadPatientsSuccess(List<Patient> patientList) { view.listPatients(patientList); }

    @Override public void onLoadPatientsError(String message) { view.showErrorMessage(message); }
}
