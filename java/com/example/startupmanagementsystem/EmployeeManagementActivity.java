package com.example.startupmanagementsystem;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class EmployeeManagementActivity extends AppCompatActivity {

    private EditText idEditText, nameEditText, roleEditText, emailEditText, updateNameEditText, updateRoleEditText, updateEmailEditText;
    private Button addButton, updateButton, removeButton, viewButton;
    private TableLayout employeeTableLayout;
    private EmployeeDatabase employeeDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_management);

        // Initialize views
        idEditText = findViewById(R.id.idEditText);
        nameEditText = findViewById(R.id.nameEditText);
        roleEditText = findViewById(R.id.roleEditText);
        emailEditText = findViewById(R.id.emailEditText);
        updateNameEditText = findViewById(R.id.updateNameEditText);
        updateRoleEditText = findViewById(R.id.updateRoleEditText);
        updateEmailEditText = findViewById(R.id.updateEmailEditText);
        addButton = findViewById(R.id.addButton);
        updateButton = findViewById(R.id.updateButton);
        removeButton = findViewById(R.id.removeButton);
        viewButton = findViewById(R.id.viewButton);
        employeeTableLayout = findViewById(R.id.employeeTableLayout);

        // Initialize database
        employeeDatabase = new EmployeeDatabase(this);

        // Set button listeners
        addButton.setOnClickListener(v -> addEmployee());
        updateButton.setOnClickListener(v -> updateEmployee());
        removeButton.setOnClickListener(v -> removeEmployee());
        viewButton.setOnClickListener(v -> refreshTable());

        // Initial table refresh
        refreshTable();
    }

    private void addEmployee() {
        String name = nameEditText.getText().toString().trim();
        String role = roleEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();

        if (name.isEmpty() || role.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isValidEmail(email)) {
            Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show();
            return;
        }

        employeeDatabase.addEmployee(name, role, email);
        clearFields();
        Toast.makeText(this, "Employee added successfully", Toast.LENGTH_SHORT).show();
        refreshTable();
    }

    private void updateEmployee() {
        String idStr = idEditText.getText().toString().trim();
        String name = updateNameEditText.getText().toString().trim();
        String role = updateRoleEditText.getText().toString().trim();
        String email = updateEmailEditText.getText().toString().trim();

        if (idStr.isEmpty() || name.isEmpty() || role.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            if (!isValidEmail(email)) {
                Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show();
                return;
            }

            employeeDatabase.updateEmployee(id, name, role, email);
            clearFields();
            Toast.makeText(this, "Employee updated successfully", Toast.LENGTH_SHORT).show();
            refreshTable();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid ID format", Toast.LENGTH_SHORT).show();
        }
    }

    private void removeEmployee() {
        String idStr = idEditText.getText().toString().trim();
        if (idStr.isEmpty()) {
            Toast.makeText(this, "Please enter the Employee ID to remove", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            employeeDatabase.deleteEmployee(id);
            clearFields();
            Toast.makeText(this, "Employee removed successfully", Toast.LENGTH_SHORT).show();
            refreshTable();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid ID format", Toast.LENGTH_SHORT).show();
        }
    }

    private void refreshTable() {
        employeeTableLayout.removeAllViews();

        // Add header row
        TableRow headerRow = new TableRow(this);
        addTextViewToRow(headerRow, "ID");
        addTextViewToRow(headerRow, "Name");
        addTextViewToRow(headerRow, "Role");
        addTextViewToRow(headerRow, "Email");
        employeeTableLayout.addView(headerRow);

        // Populate table rows with employees
        List<Employee> employees = employeeDatabase.getAllEmployees();
        for (Employee employee : employees) {
            TableRow row = new TableRow(this);
            addTextViewToRow(row, String.valueOf(employee.getId()));
            addTextViewToRow(row, employee.getName());
            addTextViewToRow(row, employee.getRole());
            addTextViewToRow(row, employee.getEmail());
            employeeTableLayout.addView(row);
        }

        Log.d("EmployeeManagement", "Table refreshed with " + employees.size() + " employees.");
    }

    private void addTextViewToRow(TableRow row, String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(8, 8, 8, 8);
        row.addView(textView);
    }

    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void clearFields() {
        idEditText.setText("");
        nameEditText.setText("");
        roleEditText.setText("");
        emailEditText.setText("");
        updateNameEditText.setText("");
        updateRoleEditText.setText("");
        updateEmailEditText.setText("");
    }
}
