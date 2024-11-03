package ru.spbstu.telematics.java.solver;

import java.util.ArrayList;
import java.util.Scanner;
import ru.spbstu.telematics.java.matrix.Matrix;

public class EquationInput
{
    public static Matrix readEquationsToMatrix() throws IllegalArgumentException
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of equations (number of rows): ");
        int numRows = scanner.nextInt();

        System.out.print("Enter the number of variables: ");
        int numCols = scanner.nextInt();

        Matrix matrix = new Matrix(numRows, numCols + 1);

        scanner.nextLine(); // Очистка буфера

        for (int i = 0; i < numRows; i++)
        {
            System.out.print(
                "Enter " + (numCols + 1) + " coefficients for " +
                 (i + 1) + "th equation separated by spaces (with constant terms): "
                 );
            String line = scanner.nextLine();
            String[] coefficients = line.split(" ");

            if (coefficients.length != numCols + 1)
            {
                throw new IllegalArgumentException("Wrong matrix coefficients.");
            }

            for (int j = 0; j < numCols + 1; j++) 
            {
                try 
                {
                    double coefficient = Double.parseDouble(coefficients[j]);
                    matrix.setElement(Integer.valueOf(i), Integer.valueOf(j), Double.valueOf(coefficient));
                } 
                catch (NumberFormatException e) 
                {
                    System.out.println("Wrong: enter real numbers.");
                    j--; // Повторите ввод для этой переменной
                }
            }
        }

        scanner.close();
        return matrix;
    }
}
