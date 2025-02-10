package com.example.startupmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectManagementActivity extends AppCompatActivity {
    private EditText projectName, projectStartTime, projectFinishTime, employeesNeeded, projectDescription;
    private EditText updateProjectId, updateProjectName, updateStartDate, updateEndDate, updateProjectDescription, updateEmployees;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_management);

        initializeViews();
        dbHelper = new DBHelper(this);

        findViewById(R.id.addProjectButton).setOnClickListener(v -> addProject());
        findViewById(R.id.viewProjectsButton).setOnClickListener(v -> startActivity(new Intent(this, ProjectListActivity.class)));
        findViewById(R.id.updateProjectButton).setOnClickListener(v -> updateProject());
        findViewById(R.id.removeProjectButton).setOnClickListener(v -> removeProject());
    }

    private void initializeViews() {
        projectName = findViewById(R.id.projectNameEditText);
        projectStartTime = findViewById(R.id.startDateEditText);
        projectFinishTime = findViewById(R.id.endDateEditText);
        employeesNeeded = findViewById(R.id.employeesEditText);
        projectDescription = findViewById(R.id.projectDescriptionEditText);

        updateProjectId = findViewById(R.id.updateProjectIdEditText);
        updateProjectName = findViewById(R.id.updateProjectNameEditText);
        updateStartDate = findViewById(R.id.updateStartDateEditText);
        updateEndDate = findViewById(R.id.updateEndDateEditText);
        updateProjectDescription = findViewById(R.id.updateProjectDescriptionEditText);
        updateEmployees = findViewById(R.id.updateEmployeesEditText);
    }

    private void addProject() {
        if (!validateInputs(projectName, projectStartTime, projectFinishTime, employeesNeeded, projectDescription)) return;

        int employees = Integer.parseInt(employeesNeeded.getText().toString().trim());
        long projectId = dbHelper.addProject(
                projectName.getText().toString().trim(),
                projectStartTime.getText().toString().trim(),
                projectFinishTime.getText().toString().trim(),
                employees,
                projectDescription.getText().toString().trim()
        );

        showToast(projectId != -1 ? "Project Added Successfully" : "Failed to Add Project");
        if (projectId != -1) clearFields();
    }

    private void updateProject() {
        if (!validateInputs(updateProjectId, updateProjectName, updateStartDate, updateEndDate, updateEmployees, updateProjectDescription)) return;

        int id = Integer.parseInt(updateProjectId.getText().toString().trim());
        int employees = Integer.parseInt(updateEmployees.getText().toString().trim());
        boolean success = dbHelper.updateProject(
                id,
                updateProjectName.getText().toString().trim(),
                updateStartDate.getText().toString().trim(),
                updateEndDate.getText().toString().trim(),
                employees,
                updateProjectDescription.getText().toString().trim()
        );

        showToast(success ? "Project Updated Successfully" : "Failed to Update Project");
    }

    private void removeProject() {
        if (!validateInputs(updateProjectId)) return;

        int id = Integer.parseInt(updateProjectId.getText().toString().trim());
        boolean success = dbHelper.deleteProject(id);
        showToast(success ? "Project Removed Successfully" : "Failed to Remove Project");
    }

    private boolean validateInputs(EditText... fields) {
        for (EditText field : fields) {
            if (field.getText().toString().trim().isEmpty()) {
                showToast("Please fill all fields");
                return false;
            }
        }
        if (fields.length > 1 && (!isValidDate(fields[1].getText().toString().trim()) || !isValidDate(fields[2].getText().toString().trim()))) {
            showToast("Invalid date format. Use yyyy-MM-dd");
            return false;
        }
        return true;
    }

    private boolean isValidDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            Date date = sdf.parse(dateStr);
            return date != null;
        } catch (ParseException e) {
            return false;
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void clearFields() {
        projectName.setText("");
        projectStartTime.setText("");
        projectFinishTime.setText("");
        employeesNeeded.setText("");
        projectDescription.setText("");
    }
}
