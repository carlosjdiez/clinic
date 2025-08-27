package com.example.clinic.contract;

import com.example.clinic.domain.Patient;

public interface EditPatientContract {
    interface Model {
        interface OnLoadPatientListener {
            void onLoadPatientSuccess(Patient p);
            void onLoadPatientError(String message);
        }
        interface OnUpdatePatientListener {
            void onUpdatePatientSuccess(Patient p);
            void onUpdatePatientError(String message);
        }
        interface OnDeletePatientListener {
            void onDeletePatientSuccess();
            void onDeletePatientError(String message);
        }

        void getPatient(long id, OnLoadPatientListener listener);
        void updatePatient(long id, Patient p, OnUpdatePatientListener listener);
        void deletePatient(long id, OnDeletePatientListener listener);
    }

    interface View {
        void showPatient(Patient p);
        void showErrorMessage(String message);
        void showSuccessMessage(String message);
        void close();
    }

    interface Presenter {
        void loadPatient(long id);
        void updatePatient(long id, Patient p);
        void deletePatient(long id);
    }
}
