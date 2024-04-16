package org.example;
import java.util.*;
public class Ejercicio3 {
    public void calcQuadraticEq(double a, double b, double c) {
        //No soy muy bueno en mates, pero creo que a esto se le llama discriminante, le voy a cambiar el nombre a algo más descriptivo
        double discriminant = b * b - 4 * a * c;
        if (discriminant > 0) {
            //Lo declaro y le doy valor en la misma línea, para que se entienda mejor y evitar malentendidos
            double x1 = (-b - Math.sqrt(discriminant)) / (2 * a);
            double x2 = (-b + Math.sqrt(discriminant)) / (2 * a);
            System.out.println("x1 = " + x1 + ", x2 = " + x2);
        }else if (discriminant == 0) {
            //Lo mismo que en el anterior comentario en este caso
            double x = -b / (2 * a);
            System.out.println("x = " + x);
        }else {
            System.out.println("Equation has no roots");
        }
    }
}
