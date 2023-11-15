package com.example.projetws;

import android.os.Bundle;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.projetws.adapter.EmployeAdapter;
import com.example.projetws.beans.Employee;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EmployeListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EmployeAdapter employeAdapter;
    private List<Employee> employeeList;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employe_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        employeeList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);

        // Fetch employees from the API using Volley
        fetchEmployees();
    }

    private void fetchEmployees() {
        String apiUrl = "http://localhost:8089/api/v1/employees";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, apiUrl, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        parseJsonResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("EmployeListActivity", "Error: " + error.getMessage());
                        // Handle error or display a message
                    }
                });

        requestQueue.add(request);
    }

    private void parseJsonResponse(JSONArray jsonArray) {
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject employeeJson = jsonArray.getJSONObject(i);

                Long id = employeeJson.getLong("id");
                String nom = employeeJson.getString("nom");
                String prenom = employeeJson.getString("prenom");
                String photo = employeeJson.getString("photo");
                String dateNaissanceString = employeeJson.getString("dateNaissance");

                // Convert date string to Date object
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Date dateNaissance = sdf.parse(dateNaissanceString);

                Employee employee = new Employee(id, nom, prenom, photo, dateNaissance);
                employeeList.add(employee);
            }

            // Set up RecyclerView with the employee list
            employeAdapter = new EmployeAdapter(employeeList, EmployeListActivity.this);
            recyclerView.setAdapter(employeAdapter);

        } catch (JSONException | ParseException e) {
            Log.e("EmployeListActivity", "Error parsing JSON: " + e.getMessage());
            // Handle error or display a message
        }
    }
}
