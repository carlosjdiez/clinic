package com.example.clinic.presenter;

import com.example.clinic.contract.RegisterPatientContract;
import com.example.clinic.domain.Patient;
import com.example.clinic.model.RegisterPatientModel;

public class RegisterPatientPresenter implements RegisterPatientContract.Presenter, RegisterPatientContract.Model.OnRegisterPatientListener {
    private final RegisterPatientContract.View view;
    private final RegisterPatientContract.Model model;

    public RegisterPatientPresenter(RegisterPatientContract.View view) {
        this.view = view;
        this.model = new RegisterPatientModel();
    }

    @Override
    public void registerPatient(Patient p) {
        if (p.getName() == null || p.getName().trim().isEmpty()) {
            view.showErrorMessage("El campo nombre no puede estar vacío");
            return;
        }
        model.registerPatient(p, this);
    }

    @Override public void onRegisterPatientSuccess(Patient p) {
        view.showSuccessMessage("Paciente registrado con id " + p.getId());
    }

    @Override public void onRegisterPatientError(String message) { view.showErrorMessage(message); }
}
