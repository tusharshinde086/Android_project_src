package com.example.startupmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {

    private Button loginButton;
    private TextView projectTitle, introText, featuresHeader, featuresList,
            objectiveHeader, objectiveText, methodologyHeader, methodologyText,
            referencesHeader, referencesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);  // Connect to the updated XML layout

        // Initialize views
        loginButton = findViewById(R.id.loginButton);
        projectTitle = findViewById(R.id.projectTitle);
        introText = findViewById(R.id.introText);
        featuresHeader = findViewById(R.id.featuresHeader);
        featuresList = findViewById(R.id.featuresList);
        objectiveHeader = findViewById(R.id.objectiveHeader);
        objectiveText = findViewById(R.id.objectiveText);
        methodologyHeader = findViewById(R.id.methodologyHeader);
        methodologyText = findViewById(R.id.methodologyText);


        // Set onClickListener for the Login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to LoginActivity when Login button is clicked
                Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();  // Optional: finish this activity to prevent back navigation
            }
        });

        }
}
