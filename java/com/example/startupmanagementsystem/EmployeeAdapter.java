package com.example.startupmanagementsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class EmployeeAdapter extends ArrayAdapter<Employee> {

    private Context context;

    public EmployeeAdapter(Context context, List<Employee> employeeList) {
        super(context, 0, employeeList);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_employee, parent, false);
        }

        Employee employee = getItem(position);

        if (employee != null) {
            TextView idTextView = convertView.findViewById(R.id.idTextView);
            TextView nameTextView = convertView.findViewById(R.id.nameTextView);
            TextView roleTextView = convertView.findViewById(R.id.roleTextView);
            TextView emailTextView = convertView.findViewById(R.id.emailTextView);

            idTextView.setText(String.valueOf(employee.getId()));
            nameTextView.setText(employee.getName());
            roleTextView.setText(employee.getRole());
            emailTextView.setText(employee.getEmail());
        }

        return convertView;
    }
}
