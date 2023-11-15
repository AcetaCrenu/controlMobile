package com.example.projetws;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Button addEtudiantButton = findViewById(R.id.addEtudiantButton);
        Button studentListButton = findViewById(R.id.studentListButton);

        addEtudiantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancer l'activity AddEtudiantActivity
                Intent intent = new Intent(SplashActivity.this, addEtudiant.class);
                startActivity(intent);
            }
        });

        studentListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancer l'activity StudentListActivity
                Intent intent = new Intent(SplashActivity.this, StudentListActivity.class);
                startActivity(intent);
            }
        });
    }
}
