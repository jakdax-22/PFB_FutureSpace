package com.backend_project.model;

public class EmployeeAssigned {
    private String name;
    private Integer employeeId;
    private boolean assigned;

    //Constructor

    public EmployeeAssigned(String name, Integer employeeId, boolean assigned) {
        this.name = name;
        this.employeeId = employeeId;
        this.assigned = assigned;
    }

    //Getters y Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }
}
