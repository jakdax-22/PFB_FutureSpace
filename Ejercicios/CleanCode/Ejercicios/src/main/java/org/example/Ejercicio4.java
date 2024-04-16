package org.example;

public class Ejercicio4 {
    //Lo he comentado porque no tengo ninguna clase Employee y para las pruebas no podía probar el 5 y el 6 si no lo comentaba
    /*public void employeeMethod(Employee employee) {
    // En este caso yo no definiría las variables, debido a que creo que los métodos que las calculan ya resultan bastante autodescriptivos
        double monthlySalary = getMonthlySalary(employee.getYearSalary, employee.getAwards());
    // Continue processing
    }
    /*Este método lo haría privado para que fuera solo accesible desde la propia clase, mejorando encapsulación y modularidad, también cambiaría el get
    a calculate, debido a que el get se suele asociar al método para coger atributos encapsulados de una clase
    private double calculateMonthlySalary(double yearlySalary, double awards) {
        //Aquí no veo nada que pueda refactorizarse
        return (yearlySalary + awards)/12;
    }*/
}
