package com.example.clinic.presenter;

import com.example.clinic.contract.DoctorListContract;
import com.example.clinic.domain.Doctor;
import com.example.clinic.model.DoctorListModel;

import java.util.List;

public class DoctorListPresenter implements DoctorListContract.Presenter, DoctorListContract.Model.OnLoadDoctorsListener {
    private final DoctorListContract.View view;
    private final DoctorListContract.Model model;

    public DoctorListPresenter(DoctorListContract.View view) {
        this.view = view;
        this.model = new DoctorListModel();
    }
    @Override public void loadDoctors() { model.loadDoctors(this); }
    @Override public void onLoadDoctorsSuccess(List<Doctor> doctorList) { view.listDoctors(doctorList); }
    @Override public void onLoadDoctorsError(String message) { view.showErrorMessage(message); }
}
