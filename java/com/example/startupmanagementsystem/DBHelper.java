package com.example.startupmanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "startup_management.db";
    private static final int DATABASE_VERSION = 5;

    // Employee table constants
    public static final String TABLE_EMPLOYEES = "employees";
    public static final String COLUMN_EMPLOYEE_ID = "id";
    public static final String COLUMN_EMPLOYEE_NAME = "name";
    public static final String COLUMN_EMPLOYEE_ROLE = "role";
    public static final String COLUMN_EMPLOYEE_EMAIL = "email";

    // Project table constants
    public static final String TABLE_PROJECTS = "projects";
    public static final String COLUMN_PROJECT_ID = "id";
    public static final String COLUMN_PROJECT_NAME = "name";
    public static final String COLUMN_PROJECT_START_TIME = "start_time";
    public static final String COLUMN_PROJECT_FINISH_TIME = "finish_time";
    public static final String COLUMN_PROJECT_EMPLOYEES_NEEDED = "employees_needed";
    public static final String COLUMN_PROJECT_DESCRIPTION = "description";

    // Users table constants
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_PASSWORD = "password";

    // Create table queries
    private static final String CREATE_TABLE_EMPLOYEES =
            "CREATE TABLE " + TABLE_EMPLOYEES + " (" +
                    COLUMN_EMPLOYEE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_EMPLOYEE_NAME + " TEXT NOT NULL, " +
                    COLUMN_EMPLOYEE_ROLE + " TEXT NOT NULL, " +
                    COLUMN_EMPLOYEE_EMAIL + " TEXT NOT NULL UNIQUE)";

    private static final String CREATE_TABLE_PROJECTS =
            "CREATE TABLE " + TABLE_PROJECTS + " (" +
                    COLUMN_PROJECT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_PROJECT_NAME + " TEXT NOT NULL, " +
                    COLUMN_PROJECT_START_TIME + " TEXT NOT NULL, " +
                    COLUMN_PROJECT_FINISH_TIME + " TEXT NOT NULL, " +
                    COLUMN_PROJECT_EMPLOYEES_NEEDED + " INTEGER NOT NULL, " +
                    COLUMN_PROJECT_DESCRIPTION + " TEXT NOT NULL)";

    private static final String CREATE_TABLE_USERS =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USERNAME + " TEXT NOT NULL, " +
                    COLUMN_USER_EMAIL + " TEXT NOT NULL, " +
                    COLUMN_USER_PASSWORD + " TEXT NOT NULL)";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EMPLOYEES);
        db.execSQL(CREATE_TABLE_PROJECTS);
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEES);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECTS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            onCreate(db);
        }
    }

    // Add a new project
    public long addProject(String name, String startTime, String finishTime, int employeesNeeded, String description) {
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_PROJECT_NAME, name);
            values.put(COLUMN_PROJECT_START_TIME, startTime);
            values.put(COLUMN_PROJECT_FINISH_TIME, finishTime);
            values.put(COLUMN_PROJECT_EMPLOYEES_NEEDED, employeesNeeded);
            values.put(COLUMN_PROJECT_DESCRIPTION, description);

            return db.insert(TABLE_PROJECTS, null, values);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // Add a new user
    public long addUser(String username, String email, String password) {
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_USERNAME, username);
            values.put(COLUMN_USER_EMAIL, email);
            values.put(COLUMN_USER_PASSWORD, password);

            return db.insert(TABLE_USERS, null, values);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // Validate user login
    public boolean isValidUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + "=? AND " + COLUMN_USER_PASSWORD + "=?",
                new String[]{username, password});
        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        return isValid;
    }
}
