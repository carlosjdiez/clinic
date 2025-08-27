package com.example.clinic.contract;

import com.example.clinic.domain.Doctor;

public interface EditDoctorContract {
    interface Model {
        interface OnLoadDoctorListener {
            void onLoadDoctorSuccess(Doctor d);
            void onLoadDoctorError(String message);
        }
        interface OnUpdateDoctorListener {
            void onUpdateDoctorSuccess(Doctor d);
            void onUpdateDoctorError(String message);
        }
        interface OnDeleteDoctorListener {
            void onDeleteDoctorSuccess();
            void onDeleteDoctorError(String message);
        }

        void getDoctor(long id, OnLoadDoctorListener listener);
        void updateDoctor(long id, Doctor d, OnUpdateDoctorListener listener);
        void deleteDoctor(long id, OnDeleteDoctorListener listener);
    }
    interface View {
        void showDoctor(Doctor d);
        void showErrorMessage(String message);
        void showSuccessMessage(String message);
        void close();
    }
    interface Presenter {
        void loadDoctor(long id);
        void updateDoctor(long id, Doctor d);
        void deleteDoctor(long id);
    }
}
