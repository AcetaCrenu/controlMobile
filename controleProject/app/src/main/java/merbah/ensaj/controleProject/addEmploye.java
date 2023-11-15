package com.example.projetws;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetws.beans.Employee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class addEmployee extends AppCompatActivity {

    private EditText nomEditText;
    private EditText prenomEditText;
    private EditText photoEditText;
    private EditText dateNaissanceEditText;
    private Button addButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        nomEditText = findViewById(R.id.nomEditText);
        prenomEditText = findViewById(R.id.prenomEditText);
        photoEditText = findViewById(R.id.photoEditText);
        dateNaissanceEditText = findViewById(R.id.dateNaissanceEditText);
        addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call a method to handle the creation of the employee
                createEmployee();
            }
        });
    }

    private void createEmployee() {
        String nom = nomEditText.getText().toString();
        String prenom = prenomEditText.getText().toString();
        String photo = photoEditText.getText().toString();
        String dateNaissanceString = dateNaissanceEditText.getText().toString();

        // Convert the date string to a Date object
        Date dateNaissance = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            dateNaissance = sdf.parse(dateNaissanceString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Check if the required fields are not empty
        if (!nom.isEmpty() && !prenom.isEmpty() && dateNaissance != null) {
            // Create an Employee object
            Employee newEmployee = new Employee(null, nom, prenom, photo, dateNaissance);

            finish();
        } else {

    }
}

