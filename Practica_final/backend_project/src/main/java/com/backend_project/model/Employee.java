package com.backend_project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Date;
//Modelo de la clase empleado con sus atributos, constructor y métodos
//Mapear la entidad y todas las columnas
@Entity(name = "em_empleados")
public class Employee {
    //Atributos
    @Id
    @Column(name="ID_EMPLEADO")
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
    @Column(name="F_BAJA")
    private Date endDate;
    @Column(name="CX_EDOCIVIL")
    private char civilStatement;
    @Column(name="B_SERVMILITAR")
    private char militarService;

    //Constructor de la clase
    public Employee(String nif, String name, String lastName, String secondLastName, Date birthDate, String firstNumber, String secondNumber, String email, Date registrationDate, Date endDate, char civilStatement, char militarService) {
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
        this.civilStatement = civilStatement;
        this.militarService = militarService;
    }
    //Constructor por defecto
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

    public char getCivilStatement() {
        return civilStatement;
    }

    public void setCivilStatement(char civilStatement) {
        this.civilStatement = civilStatement;
    }

    public char getMilitarService() {
        return militarService;
    }

    public void setMilitarService(char militarService) {
        this.militarService = militarService;
    }
}
