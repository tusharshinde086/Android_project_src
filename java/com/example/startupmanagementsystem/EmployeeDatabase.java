package com.example.startupmanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDatabase {

    private final DBHelper dbHelper;

    public EmployeeDatabase(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (SQLiteDatabase db = dbHelper.getReadableDatabase();
             Cursor cursor = db.rawQuery("SELECT * FROM " + DBHelper.TABLE_EMPLOYEES, null)) {
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_EMPLOYEE_ID));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_EMPLOYEE_NAME));
                    String role = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_EMPLOYEE_ROLE));
                    String email = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_EMPLOYEE_EMAIL));
                    employees.add(new Employee(id, name, role, email));
                } while (cursor.moveToNext());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public void addEmployee(String name, String role, String email) {
        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put(DBHelper.COLUMN_EMPLOYEE_NAME, name);
            values.put(DBHelper.COLUMN_EMPLOYEE_ROLE, role);
            values.put(DBHelper.COLUMN_EMPLOYEE_EMAIL, email);
            db.insert(DBHelper.TABLE_EMPLOYEES, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(int id, String name, String role, String email) {
        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put(DBHelper.COLUMN_EMPLOYEE_NAME, name);
            values.put(DBHelper.COLUMN_EMPLOYEE_ROLE, role);
            values.put(DBHelper.COLUMN_EMPLOYEE_EMAIL, email);
            db.update(DBHelper.TABLE_EMPLOYEES, values, DBHelper.COLUMN_EMPLOYEE_ID + " = ?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int id) {
        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            db.delete(DBHelper.TABLE_EMPLOYEES, DBHelper.COLUMN_EMPLOYEE_ID + " = ?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
