package ru.spbstu.telematics.java;

import ru.spbstu.telematics.java.matrix.SqMatrix;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        SqMatrix originalMatrix = new SqMatrix(3);
        originalMatrix.setElement(0, 0, 1.0);
        originalMatrix.setElement(0, 1, 2.0);
        originalMatrix.setElement(0, 2, 3.0);
        originalMatrix.setElement(1, 0, 4.0);
        originalMatrix.setElement(1, 1, 5.0);
        originalMatrix.setElement(1, 2, 6.0);
        originalMatrix.setElement(2, 0, 7.0);
        originalMatrix.setElement(2, 1, 8.0);
        originalMatrix.setElement(2, 2, 9.0);
        System.out.println("Original matrix:");
        originalMatrix.printMatrix();
        System.out.println("Triangular matrix:");
        originalMatrix.toTriangular().printMatrix();
    }
}
