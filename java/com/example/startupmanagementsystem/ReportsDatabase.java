package com.example.startupmanagementsystem;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ReportsDatabase {

    private DBHelper dbHelper;

    public ReportsDatabase(Context context) {
        dbHelper = new DBHelper(context);
    }

    // Method to fetch all reports
    public List<Report> getAllReports() {
        List<Report> reports = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM reports", null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String content = cursor.getString(cursor.getColumnIndex("content"));

                reports.add(new Report(id, title, date, content));
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return reports;
    }
}
