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
import com.example.clinic.domain.Patient;
import com.example.clinic.view.PatientDetailView;

import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientHolder> {

    private final List<Patient> patientList;

    public PatientAdapter(List<Patient> patientList) { this.patientList = patientList; }

    @NonNull @Override
    public PatientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_item, parent, false);
        return new PatientHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientHolder h, int position) {
        Patient p = patientList.get(position);
        h.name.setText(p.getName() + " " + p.getSurname());
        h.email.setText(p.getEmail());
        h.weight.setText(parenthesizeWeight(p.getWeightKg()));
    }

    private String parenthesizeWeight(float w) { return "(" + w + " kg)"; }

    @Override public int getItemCount() { return patientList.size(); }

    class PatientHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView name;
        TextView email;
        TextView weight;

        public PatientHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.item_patient_image);
            name = itemView.findViewById(R.id.item_patient_name);
            email = itemView.findViewById(R.id.item_patient_email);
            weight = itemView.findViewById(R.id.item_patient_weight);

            itemView.setOnClickListener(v -> {
                long id = patientList.get(getAdapterPosition()).getId();
                Intent i = new Intent(itemView.getContext(), PatientDetailView.class);
                i.putExtra("patientId", id);
                startActivity(itemView.getContext(), i, null);
            });
        }
    }
}
