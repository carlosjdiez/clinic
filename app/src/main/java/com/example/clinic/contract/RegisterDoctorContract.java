package com.example.clinic.contract;

import com.example.clinic.domain.Doctor;

public interface RegisterDoctorContract {
    interface Model {
        interface OnRegisterDoctorListener {
            void onRegisterDoctorSuccess(Doctor d);
            void onRegisterDoctorError(String message);
        }
        void registerDoctor(Doctor d, OnRegisterDoctorListener listener);
    }
    interface View {
        void showErrorMessage(String message);
        void showSuccessMessage(String message);
    }
    interface Presenter { void registerDoctor(Doctor d); }
}
