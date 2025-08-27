package com.example.clinic.contract;

import com.example.clinic.domain.Doctor;
import java.util.List;

public interface DoctorListContract {
    interface Model {
        interface OnLoadDoctorsListener {
            void onLoadDoctorsSuccess(List<Doctor> doctorList);
            void onLoadDoctorsError(String message);
        }
        void loadDoctors(OnLoadDoctorsListener listener);
    }
    interface View {
        void listDoctors(List<Doctor> doctorList);
        void showErrorMessage(String message);
        void showSuccessMessage(String message);
    }
    interface Presenter { void loadDoctors(); }
}
