package com.backend_project.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;

@Entity(name = "pr_empleados_proyecto")
public class Asignation implements Serializable {
    @EmbeddedId
    private AsignationId id;
    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "ID_EMPLEADO")
    private Employee employee;

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "ID_PROYECTO")
    private Project project;

    @Column(name = "F_ALTA")
    private Date startDate;

    //Constructor


    public Asignation(Employee employee, Project project, Date startDate) {
        this.id = new AsignationId(employee.getEmployeeId(),project.getProjectId());
        this.employee = employee;
        this.project = project;
        this.startDate = startDate;
    }

    //Constructor vacío
    public Asignation(){

    }
    //Getters y setters


    public AsignationId getId() {
        return id;
    }

    public void setId(AsignationId id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Asignation{" +
                "id=" + id +
                ", employee=" + employee +
                ", project=" + project +
                ", startDate=" + startDate +
                '}';
    }
}
