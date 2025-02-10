package com.example.startupmanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class ProjectDatabase {
    private final DBHelper dbHelper;

    public ProjectDatabase(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    // Fetch all projects
    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        try (SQLiteDatabase db = dbHelper.getReadableDatabase();
             Cursor cursor = db.rawQuery("SELECT * FROM projects", null)) {

            if (cursor.moveToFirst()) {
                do {
                    Project project = new Project(
                            cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                            cursor.getString(cursor.getColumnIndexOrThrow("name")),
                            cursor.getString(cursor.getColumnIndexOrThrow("start_time")),
                            cursor.getString(cursor.getColumnIndexOrThrow("finish_time")),
                            cursor.getInt(cursor.getColumnIndexOrThrow("employees_needed")),
                            cursor.getString(cursor.getColumnIndexOrThrow("description"))
                    );
                    projects.add(project);
                } while (cursor.moveToNext());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    // Fetch a single project by ID
    public Project getProjectById(int id) {
        try (SQLiteDatabase db = dbHelper.getReadableDatabase();
             Cursor cursor = db.rawQuery("SELECT * FROM projects WHERE id = ?", new String[]{String.valueOf(id)})) {

            if (cursor.moveToFirst()) {
                return new Project(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("start_time")),
                        cursor.getString(cursor.getColumnIndexOrThrow("finish_time")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("employees_needed")),
                        cursor.getString(cursor.getColumnIndexOrThrow("description"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Add a new project
    public boolean addProject(String name, String startTime, String finishTime, int employeesNeeded, String description) {
        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("start_time", startTime);
            values.put("finish_time", finishTime);
            values.put("employees_needed", employeesNeeded);
            values.put("description", description);

            return db.insert("projects", null, values) != -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update an existing project
    public boolean updateProject(int id, String name, String startTime, String finishTime, int employeesNeeded, String description) {
        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("start_time", startTime);
            values.put("finish_time", finishTime);
            values.put("employees_needed", employeesNeeded);
            values.put("description", description);

            return db.update("projects", values, "id = ?", new String[]{String.valueOf(id)}) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a project by ID
    public boolean deleteProject(int id) {
        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            return db.delete("projects", "id = ?", new String[]{String.valueOf(id)}) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
