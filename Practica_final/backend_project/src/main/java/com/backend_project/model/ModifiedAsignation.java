package com.backend_project.model;

public class ModifiedAsignation {
    Integer employeeId;
    Integer projectId;

    public ModifiedAsignation(Integer employeeId, Integer projectId) {
        this.employeeId = employeeId;
        this.projectId = projectId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
