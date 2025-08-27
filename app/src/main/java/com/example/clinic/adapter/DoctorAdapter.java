package com.example.clinic.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clinic.R;
import com.example.clinic.domain.Doctor;
import com.example.clinic.view.DoctorDetailView;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorHolder> {

    private final List<Doctor> doctorList;

    public DoctorAdapter(List<Doctor> doctorList) { this.doctorList = doctorList; }

    @NonNull @Override
    public DoctorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_item, parent, false);
        return new DoctorHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorHolder h, int position) {
        Doctor d = doctorList.get(position);
        h.name.setText(d.getName() + " " + d.getSurname());
        h.specialty.setText(d.getSpecialty());
        h.license.setText(d.getLicenseNumber());
    }

    @Override public int getItemCount() { return doctorList.size(); }

    class DoctorHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView name;
        TextView specialty;
        TextView license;

        public DoctorHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.item_doctor_image);
            name = itemView.findViewById(R.id.item_doctor_name);
            specialty = itemView.findViewById(R.id.item_doctor_specialty);
            license = itemView.findViewById(R.id.item_doctor_license);

            itemView.setOnClickListener(v -> {
                long id = doctorList.get(getAdapterPosition()).getId();
                Intent i = new Intent(itemView.getContext(), DoctorDetailView.class);
                i.putExtra("doctorId", id);
                startActivity(itemView.getContext(), i, null);
            });
        }
    }
}
