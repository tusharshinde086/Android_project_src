package com.example.startupmanagementsystem;

public class Project {
    private int id;
    private String name;
    private String description;
    private int employeesNeeded;
    private String startTime;
    private String finishTime;

    // Default Constructor (Needed for some frameworks like Firebase)
    public Project() {}

    // Constructor with all fields
    public Project(int id, String name, String description, int employeesNeeded, String startTime, String finishTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.employeesNeeded = employeesNeeded;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getEmployeesNeeded() {
        return employeesNeeded;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    // Setters (Optional, useful if you need to modify an existing project)
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmployeesNeeded(int employeesNeeded) {
        this.employeesNeeded = employeesNeeded;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", employeesNeeded=" + employeesNeeded +
                ", startTime='" + startTime + '\'' +
                ", finishTime='" + finishTime + '\'' +
                '}';
    }
}
