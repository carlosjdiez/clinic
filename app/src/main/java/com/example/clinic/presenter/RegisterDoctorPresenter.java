package com.example.clinic.presenter;

import com.example.clinic.contract.RegisterDoctorContract;
import com.example.clinic.domain.Doctor;
import com.example.clinic.model.RegisterDoctorModel;

public class RegisterDoctorPresenter implements RegisterDoctorContract.Presenter, RegisterDoctorContract.Model.OnRegisterDoctorListener {
    private final RegisterDoctorContract.View view;
    private final RegisterDoctorContract.Model model;

    public RegisterDoctorPresenter(RegisterDoctorContract.View view) {
        this.view = view;
        this.model = new RegisterDoctorModel();
    }

    @Override
    public void registerDoctor(Doctor d) {
        if (d.getName() == null || d.getName().trim().isEmpty()) {
            view.showErrorMessage("El campo nombre no puede estar vacío");
            return;
        }
        model.registerDoctor(d, this);
    }

    @Override public void onRegisterDoctorSuccess(Doctor d) { view.showSuccessMessage("Doctor registrado con id " + d.getId()); }
    @Override public void onRegisterDoctorError(String message) { view.showErrorMessage(message); }
}
