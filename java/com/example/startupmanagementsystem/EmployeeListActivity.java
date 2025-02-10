package com.example.startupmanagementsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class EmployeeListActivity extends AppCompatActivity {

    private EditText idEditText, nameEditText, roleEditText, emailEditText;
    private Button addButton, updateButton, removeButton, viewButton;
    private TableLayout employeeTableLayout;
    private EmployeeDatabase employeeDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        // Initialize views
        idEditText = findViewById(R.id.idEditText);
        nameEditText = findViewById(R.id.nameEditText);
        roleEditText = findViewById(R.id.roleEditText);
        emailEditText = findViewById(R.id.emailEditText);
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
        removeButton.setOnClickListener(v -> {
            String idStr = idEditText.getText().toString().trim();
            if (!idStr.isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr);
                    removeEmployee(id);
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Invalid ID format", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Please enter the Employee ID to remove", Toast.LENGTH_SHORT).show();
            }
        });
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
        String name = nameEditText.getText().toString().trim();
        String role = roleEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();

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

    private void removeEmployee(int id) {
        employeeDatabase.deleteEmployee(id);
        clearFields();
        Toast.makeText(this, "Employee removed successfully", Toast.LENGTH_SHORT).show();
        refreshTable();
    }

    private void refreshTable() {
        employeeTableLayout.removeAllViews();

        // Add header row
        TableRow headerRow = new TableRow(this);

        TextView idHeader = new TextView(this);
        idHeader.setText("ID");
        headerRow.addView(idHeader);

        TextView nameHeader = new TextView(this);
        nameHeader.setText("Name");
        headerRow.addView(nameHeader);

        TextView roleHeader = new TextView(this);
        roleHeader.setText("Role");
        headerRow.addView(roleHeader);

        TextView emailHeader = new TextView(this);
        emailHeader.setText("Email");
        headerRow.addView(emailHeader);

        TextView updateHeader = new TextView(this);
        updateHeader.setText("Update");
        headerRow.addView(updateHeader);

        TextView deleteHeader = new TextView(this);
        deleteHeader.setText("Delete");
        headerRow.addView(deleteHeader);

        employeeTableLayout.addView(headerRow);

        // Populate table rows with employees
        List<Employee> employees = employeeDatabase.getAllEmployees();
        for (Employee employee : employees) {
            TableRow row = new TableRow(this);

            TextView idTextView = new TextView(this);
            idTextView.setText(String.valueOf(employee.getId()));
            row.addView(idTextView);

            TextView nameTextView = new TextView(this);
            nameTextView.setText(employee.getName());
            row.addView(nameTextView);

            TextView roleTextView = new TextView(this);
            roleTextView.setText(employee.getRole());
            row.addView(roleTextView);

            TextView emailTextView = new TextView(this);
            emailTextView.setText(employee.getEmail());
            row.addView(emailTextView);

            Button updateButton = new Button(this);
            updateButton.setText("Update");
            updateButton.setOnClickListener(v -> {
                idEditText.setText(String.valueOf(employee.getId()));
                nameEditText.setText(employee.getName());
                roleEditText.setText(employee.getRole());
                emailEditText.setText(employee.getEmail());
            });
            row.addView(updateButton);

            Button deleteButton = new Button(this);
            deleteButton.setText("Delete");
            deleteButton.setOnClickListener(v -> removeEmployee(employee.getId()));
            row.addView(deleteButton);

            employeeTableLayout.addView(row);
        }
    }

    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void clearFields() {
        idEditText.setText("");
        nameEditText.setText("");
        roleEditText.setText("");
        emailEditText.setText("");
    }
}
