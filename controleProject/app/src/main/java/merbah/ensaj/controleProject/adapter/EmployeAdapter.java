package com.example.projetws.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetws.R;
import com.example.projetws.beans.Employee;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class EmployeAdapter extends RecyclerView.Adapter<EmployeAdapter.EmployeeViewHolder> {
    private List<Employee> employeeList;
    private Context context;

    public EmployeAdapter(List<Employee> employeeList, Context context) {
        this.employeeList = employeeList;
        this.context = context;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_item, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee employee = employeeList.get(position);
        holder.bind(employee);

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirmation de suppression");
                builder.setMessage("Voulez-vous vraiment supprimer cet employé ?");
                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        employeeList.remove(position);
                        notifyItemRemoved(position);
                        // Other code to delete the employee from the database or API
                    }
                });

                builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    static class EmployeeViewHolder extends RecyclerView.ViewHolder {
        private TextView nomTextView;
        private TextView prenomTextView;
        private TextView serviceTextView;
        private TextView dateNaissanceTextView;
        private Button deleteButton;


        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            nomTextView = itemView.findViewById(R.id.nomTextView);
            prenomTextView = itemView.findViewById(R.id.prenomTextView);
            serviceTextView = itemView.findViewById(R.id.serviceTextView);
            dateNaissanceTextView = itemView.findViewById(R.id.dateNaissanceTextView);
            deleteButton = itemView.findViewById(R.id.deleteButton);

        }

        public void bind(Employee employee) {
            nomTextView.setText("Nom: " + employee.getNom());
            prenomTextView.setText("Prenom: " + employee.getPrenom());
            serviceTextView.setText("Service: " + employee.getPhoto());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            dateNaissanceTextView.setText("Date de Naissance: " + sdf.format(employee.getDateNaissance()));
        }
    }
}
