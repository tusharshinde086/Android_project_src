package com.example.startupmanagementsystem;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TaskDatabase {

    private DBHelper dbHelper;

    public TaskDatabase(Context context) {
        dbHelper = new DBHelper(context);
    }

    // Method to fetch all tasks from the database
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tasks", null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String taskName = cursor.getString(cursor.getColumnIndex("task_name"));
                String deadline = cursor.getString(cursor.getColumnIndex("deadline"));
                int projectId = cursor.getInt(cursor.getColumnIndex("project_id"));

                Task task = new Task(id, taskName, deadline, projectId);
                tasks.add(task);
            } while (cursor.moveToNext());
            cursor.close();
        }

        db.close();
        return tasks;
    }
}
