package com.example.startupmanagementsystem;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class TaskListActivity extends AppCompatActivity {

    private ListView taskListView;
    private TaskDatabase taskDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        taskListView = findViewById(R.id.taskListView);
        taskDatabase = new TaskDatabase(this);

        // Fetch all tasks from the database
        List<Task> taskList = taskDatabase.getAllTasks();

        if (taskList.isEmpty()) {
            Toast.makeText(this, "No Tasks Found", Toast.LENGTH_SHORT).show();
        } else {
            // Create an adapter to display tasks
            TaskAdapter adapter = new TaskAdapter(this, taskList);
            taskListView.setAdapter(adapter);
        }
    }
}
