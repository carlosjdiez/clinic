package com.example.clinic.contract;

import com.example.clinic.domain.Patient;
import java.util.List;

public interface PatientListContract {
    interface Model {
        interface OnLoadPatientsListener {
            void onLoadPatientsSuccess(List<Patient> patientList);
            void onLoadPatientsError(String message);
        }
        void loadPatients(OnLoadPatientsListener listener);
    }

    interface View {
        void listPatients(List<Patient> patientList);
        void showErrorMessage(String message);
        void showSuccessMessage(String message);
    }

    interface Presenter {
        void loadPatients();
    }
}
