package com.example.clinic.presenter;

import com.example.clinic.contract.EditDoctorContract;
import com.example.clinic.domain.Doctor;
import com.example.clinic.model.EditDoctorModel;

public class EditDoctorPresenter implements EditDoctorContract.Presenter,
        EditDoctorContract.Model.OnLoadDoctorListener,
        EditDoctorContract.Model.OnUpdateDoctorListener,
        EditDoctorContract.Model.OnDeleteDoctorListener {

    private final EditDoctorContract.View view;
    private final EditDoctorContract.Model model;

    public EditDoctorPresenter(EditDoctorContract.View view) {
        this.view = view;
        this.model = new EditDoctorModel();
    }

    @Override public void loadDoctor(long id) { model.getDoctor(id, this); }
    @Override public void updateDoctor(long id, Doctor d) { model.updateDoctor(id, d, this); }
    @Override public void deleteDoctor(long id) { model.deleteDoctor(id, this); }

    @Override public void onLoadDoctorSuccess(Doctor d) { view.showDoctor(d); }
    @Override public void onLoadDoctorError(String message) { view.showErrorMessage(message); }
    @Override public void onUpdateDoctorSuccess(Doctor d) { view.showSuccessMessage("Doctor actualizado"); }
    @Override public void onUpdateDoctorError(String message) { view.showErrorMessage(message); }
    @Override public void onDeleteDoctorSuccess() { view.showSuccessMessage("Doctor eliminado"); view.close(); }
    @Override public void onDeleteDoctorError(String message) { view.showErrorMessage(message); }
}
