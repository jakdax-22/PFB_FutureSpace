package com.backend_project.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

//Modelo de la clase proyecto con sus atributos, constructor y métodos
//Mapear la entidad y todas las columnas
@Entity(name = "pr_proyectos")
public class Project {
    //Atributos
    @Id
    @Column(name = "ID_PROYECTO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectId;
    @Column(name = "TX_DESCRIPCION")
    private String description;
    @Column(name = "F_INICIO")
    private Date startDate;
    @Column(name = "F_FIN")
    private Date endDate;
    @Column(name = "F_BAJA", nullable = true)
    private Date terminateDate;
    @Column(name = "TX_LUGAR")
    private String place;
    @Column(name = "TX_OBSERVACIONES", nullable = true)
    private String observations;

    @ManyToMany(mappedBy = "projects")
    private Set<Employee> employees = new HashSet<>();
    //Contructor

    public Project(Integer projectId, String description, Date startDate, Date endDate, Date terminateDate, String place, String observations) {
        this.projectId = projectId;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.terminateDate = terminateDate;
        this.place = place;
        this.observations = observations;
    }
    //Constructor vacío
    public Project(){

    }

    //Getters and Setters

    public Integer getProjectId() {
        return projectId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getTerminateDate() {
        return terminateDate;
    }

    public void setTerminateDate(Date terminateDate) {
        this.terminateDate = terminateDate;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", terminateDate=" + terminateDate +
                ", place='" + place + '\'' +
                ", observations='" + observations + '\'' +
                ", employees=" + employees +
                '}';
    }
}