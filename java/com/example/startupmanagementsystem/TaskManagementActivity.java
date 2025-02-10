package com.example.startupmanagementsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class TaskManagementActivity extends AppCompatActivity {
    private EditText taskTitleField, taskDescriptionField;
    private Button addTaskButton, updateTaskButton, deleteTaskButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_management);

        // Mapping EditText fields
        taskTitleField = findViewById(R.id.taskTitle);
        taskDescriptionField = findViewById(R.id.taskDescription);

        // Mapping Buttons
        addTaskButton = findViewById(R.id.addTaskButton);
        updateTaskButton = findViewById(R.id.updateTaskButton);
        deleteTaskButton = findViewById(R.id.deleteTaskButton);

        // Adding Task Button Logic
        addTaskButton.setOnClickListener(v -> {
            String taskTitle = taskTitleField.getText().toString();
            String taskDescription = taskDescriptionField.getText().toString();

            // Implement logic to add a task (e.g., add to database, list, etc.)
            // Sample message for task creation
            Toast.makeText(TaskManagementActivity.this, "Task Added", Toast.LENGTH_SHORT).show();
        });

        // Update Task Button Logic
        updateTaskButton.setOnClickListener(v -> {
            String taskTitle = taskTitleField.getText().toString();
            String taskDescription = taskDescriptionField.getText().toString();

            // Implement logic to update the task
            // Sample message for task update
            Toast.makeText(TaskManagementActivity.this, "Task Updated", Toast.LENGTH_SHORT).show();
        });

        // Delete Task Button Logic
        deleteTaskButton.setOnClickListener(v -> {
            // Implement logic to delete the task
            // Sample message for task deletion
            Toast.makeText(TaskManagementActivity.this, "Task Deleted", Toast.LENGTH_SHORT).show();
        });
    }
}
