package com.example.clinic.contract;

import com.example.clinic.domain.Patient;

public interface RegisterPatientContract {
    interface Model {
        interface OnRegisterPatientListener {
            void onRegisterPatientSuccess(Patient p);
            void onRegisterPatientError(String message);
        }
        void registerPatient(Patient p, OnRegisterPatientListener listener);
    }

    interface View {
        void showErrorMessage(String message);
        void showSuccessMessage(String message);
    }

    interface Presenter {
        void registerPatient(Patient p);
    }
}
