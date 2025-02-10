package com.example.startupmanagementsystem;

public class Task {
    private int id;
    private String taskName;
    private String deadline;
    private int projectId;

    public Task(int id, String taskName, String deadline, int projectId) {
        this.id = id;
        this.taskName = taskName;
        this.deadline = deadline;
        this.projectId = projectId;
    }

    public int getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDeadline() {
        return deadline;
    }

    public int getProjectId() {
        return projectId;
    }
}
