package com.example.startupmanagementsystem;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class ReportsActivity extends AppCompatActivity {

    private ListView reportsListView;
    private ReportsDatabase reportsDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        reportsListView = findViewById(R.id.reportsListView);
        reportsDatabase = new ReportsDatabase(this);

        // Fetch all reports from the database
        List<Report> reportList = reportsDatabase.getAllReports();

        if (reportList.isEmpty()) {
            Toast.makeText(this, "No Reports Found", Toast.LENGTH_SHORT).show();
        } else {
            // Create an adapter to display reports
            ReportAdapter adapter = new ReportAdapter(this, reportList);
            reportsListView.setAdapter(adapter);
        }
    }
}
