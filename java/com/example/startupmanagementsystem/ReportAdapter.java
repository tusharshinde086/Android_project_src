package com.example.startupmanagementsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ReportAdapter extends ArrayAdapter<Report> {

    public ReportAdapter(Context context, List<Report> reports) {
        super(context, 0, reports);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.report_item, parent, false);
        }

        Report report = getItem(position);

        TextView titleTextView = convertView.findViewById(R.id.reportTitle);
        TextView dateTextView = convertView.findViewById(R.id.reportDate);
        TextView contentTextView = convertView.findViewById(R.id.reportContent);

        titleTextView.setText(report.getTitle());
        dateTextView.setText(report.getDate());
        contentTextView.setText(report.getContent());

        return convertView;
    }
}
