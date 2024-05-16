package com.backend_project.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

//Modelo de la clase empleado con sus atributos, constructor y métodos
//Mapear la entidad y todas las columnas
@Entity(name = "em_empleados")
public class Employee {
    //Atributos
    @Id
    @Column(name="ID_EMPLEADO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;
    @Column(name="TX_NIF")
    private String nif;
    @Column(name="TX_NOMBRE")
    private String name;
    @Column(name="TX_APELLIDO1")
    private String lastName;
    @Column(name="TX_APELLIDO2")
    private String secondLastName;
    @Column(name="F_NACIMIENTO")
    private Date birthDate;
    @Column(name="N_TELEFONO1")
    private String firstNumber;
    @Column(name="N_TELEFONO2")
    private String secondNumber;
    @Column(name="TX_EMAIL")
    private String email;
    @Column(name="F_ALTA")
    private Date registrationDate;
    @Column(name="F_BAJA", nullable = true)
    private Date endDate;
    @Column(name="CX_EDOCIVIL")
    private char civilState;
    @Column(name="B_SERVMILITAR")
    private char militarService;

    @ManyToMany()
    @JoinTable(name="pr_empleados_proyecto",
            joinColumns = @JoinColumn(name="ID_EMPLEADO"),
            inverseJoinColumns = @JoinColumn(name = "ID_PROYECTO"))
    private Set<Project> projects = new HashSet<>();

    //Constructor de la clase
    public Employee(String nif, String name, String lastName, String secondLastName, Date birthDate, String firstNumber, String secondNumber, String email, Date registrationDate, Date endDate, char civilState, char militarService) {
        this.nif = nif;
        this.name = name;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.birthDate = birthDate;
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.email = email;
        this.registrationDate = registrationDate;
        this.endDate = endDate;
        this.civilState = civilState;
        this.militarService = militarService;
    }
    //Constructor por defecto

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Employee(){

    }
    // Getters y Setters de todo menos del id
    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(String firstNumber) {
        this.firstNumber = firstNumber;
    }

    public String getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(String secondNumber) {
        this.secondNumber = secondNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public char getCivilState() {
        return civilState;
    }

    public void setCivilState(char civilState) {
        this.civilState = civilState;
    }

    public char getMilitarService() {
        return militarService;
    }

    public void setMilitarService(char militarService) {
        this.militarService = militarService;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", nif='" + nif + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", secondLastName='" + secondLastName + '\'' +
                ", birthDate=" + birthDate +
                ", firstNumber='" + firstNumber + '\'' +
                ", secondNumber='" + secondNumber + '\'' +
                ", email='" + email + '\'' +
                ", registrationDate=" + registrationDate +
                ", endDate=" + endDate +
                ", civilState=" + civilState +
                ", militarService=" + militarService +
                '}';
    }
}
