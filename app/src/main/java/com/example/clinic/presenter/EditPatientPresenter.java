package com.example.clinic.presenter;

import com.example.clinic.contract.EditPatientContract;
import com.example.clinic.domain.Patient;
import com.example.clinic.model.EditPatientModel;

public class EditPatientPresenter implements EditPatientContract.Presenter,
        EditPatientContract.Model.OnLoadPatientListener,
        EditPatientContract.Model.OnUpdatePatientListener,
        EditPatientContract.Model.OnDeletePatientListener {

    private final EditPatientContract.View view;
    private final EditPatientContract.Model model;

    public EditPatientPresenter(EditPatientContract.View view) {
        this.view = view;
        this.model = new EditPatientModel();
    }

    @Override public void loadPatient(long id) { model.getPatient(id, this); }

    @Override public void updatePatient(long id, Patient p) { model.updatePatient(id, p, this); }

    @Override public void deletePatient(long id) { model.deletePatient(id, this); }

    @Override public void onLoadPatientSuccess(Patient p) { view.showPatient(p); }

    @Override public void onLoadPatientError(String message) { view.showErrorMessage(message); }

    @Override public void onUpdatePatientSuccess(Patient p) { view.showSuccessMessage("Paciente actualizado"); }

    @Override public void onUpdatePatientError(String message) { view.showErrorMessage(message); }

    @Override public void onDeletePatientSuccess() { view.showSuccessMessage("Paciente eliminado"); view.close(); }

    @Override public void onDeletePatientError(String message) { view.showErrorMessage(message); }
}
