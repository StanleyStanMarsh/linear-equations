package ru.spbstu.telematics.java;

import ru.spbstu.telematics.java.matrix.Matrix;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Matrix m = new Matrix(3, 5);
        m.transpose().printMatrix();
    }
}
