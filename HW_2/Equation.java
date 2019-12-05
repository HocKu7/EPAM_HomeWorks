package com.epam;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Equation {
    private float a;
    private float b;
    private float c;
    private double Discriminant;
    Roots roots;

    public static class Roots {
        Double x1, x2;
    }

    Equation() {

    }

    Equation(float a, float b, float c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }

    public float getC() {
        return c;
    }

    public void setC(float c) {
        this.c = c;
    }

    public double getDiscriminant() {
        return (pow(b, 2) - 4 * a * c);
    }

    public static double getDiscriminant(float a, float b, float c) {
        return (pow(b, 2) - 4 * a * c);
    }

    private Roots getRoots() {
        roots = new Roots();
        if (Discriminant == 0) {
            roots.x1 = (double) -b / (2 * a);
            roots.x2 = null;
        } else {
            roots.x1 = (-b + sqrt(Discriminant)) / (2 * a);
            roots.x2 = (-b - sqrt(Discriminant)) / (2 * a);
        }
        return roots;
    }

    public static Roots getRoots(float a, float b, double Discriminant) {
        Roots roots = new Roots();
        if (Discriminant == 0) {
            roots.x1 = (double) -b / (2 * a);
        } else {
            roots.x1 = (-b + sqrt(Discriminant)) / (2 * a);
            roots.x2 = (-b - sqrt(Discriminant)) / (2 * a);
        }
        return roots;
    }

    public void solveEquation() {
        this.Discriminant = getDiscriminant();
        boolean hasSolution = Discriminant >= 0;
        if (hasSolution) {
            roots = getRoots();
            System.out.println("First root: " + roots.x1);
            if (roots.x2 != null) {
                System.out.println("Second root: " + roots.x2);
            }
        } else {
            System.out.println("No roots");
        }
    }

    public static void solveEquation(float a, float b, float c) {
        Roots roots;
        double Discriminant = getDiscriminant(a, b, c);
        boolean hasSolution = Discriminant >= 0;
        if (hasSolution) {
            roots = getRoots(a, b, Discriminant);
            System.out.println("First root: " + roots.x1);
            System.out.println("Second root: " + roots.x2);
        } else {
            System.out.println("No roots");
        }
    }

    public static void main(String[] args) {
        Equation pointer = new Equation(5, 8, 3);
        pointer.solveEquation();
        pointer = new Equation();
        pointer.setA(5);
        pointer.setB(8);
        pointer.setC(3);
        double D = pointer.getDiscriminant();
        System.out.println("Discriminant is: " + D);

        Equation.solveEquation(3, 7, 2);


    }
}
