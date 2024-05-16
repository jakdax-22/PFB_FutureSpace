package com.backend_project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class AsignationId implements Serializable {
    @Column(name = "ID_EMPLEADO", insertable = false, updatable = false)
    private Integer employeeId;

    @Column(name = "ID_PROYECTO", insertable = false, updatable = false)
    private Integer projectId;

    //Constructor

    public AsignationId(Integer employeeId, Integer projectId) {
        this.employeeId = employeeId;
        this.projectId = projectId;
    }

    //Constructor vacío
    public AsignationId(){

    }
    //Getters y setters

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

    @Override
    public String toString() {
        return "AsignationId{" +
                "employeeId=" + employeeId +
                ", projectId=" + projectId +
                '}';
    }
}
