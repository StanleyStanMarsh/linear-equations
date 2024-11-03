package ru.spbstu.telematics.java.solver;

import java.util.ArrayList;
import java.lang.Math;
import ru.spbstu.telematics.java.matrix.Matrix;
import ru.spbstu.telematics.java.exeptions.*;

public class Solver 
{
    private static void swapRows(Matrix matrix, Integer row1, Integer row2) 
    {
        ArrayList<Double> tempRow = matrix.getRow(row1);
        matrix.setRow(row1, matrix.getRow(row2));
        matrix.setRow(row2, tempRow);
    }

    public static Matrix toRowEchelonForm(Matrix matrix) throws WrongCoefficientsMatrixException
    {
        if (matrix.getRowCount() + 1 != matrix.getColumnCount())
            throw new WrongCoefficientsMatrixException("The given matrix is not augmented coefficient matrix");

        Matrix c_matrix = new Matrix(matrix);
        Integer n = c_matrix.getRowCount();
        for (int k = 0; k < n; k++)
        {
            // find row with greatest value
            int i_max = k;
            Double max = c_matrix.getElement(Integer.valueOf(i_max), Integer.valueOf(k));

            for (int i = k + 1; i < n; i++)
            {
                if (Math.abs(c_matrix.getElement(Integer.valueOf(i), Integer.valueOf(k))) > max)
                {
                    i_max = i;
                    max = c_matrix.getElement(Integer.valueOf(i), Integer.valueOf(k));
                }
            }
            // singular matrix
            if (c_matrix.getElement(Integer.valueOf(k), Integer.valueOf(i_max)) == 0)
                return c_matrix;
            
            // swap current row with greatest
            if (i_max != k)
                Solver.swapRows(c_matrix, Integer.valueOf(k), Integer.valueOf(i_max));
            
            for (int i = k + 1; i < n; i++)
            {
                // claculate coefficient for substraction
                Double c = c_matrix.getElement(Integer.valueOf(i), Integer.valueOf(k)) / c_matrix.getElement(Integer.valueOf(k), Integer.valueOf(k));

                // substract row with k row
                for (int j = k + 1; j < n + 1; j++)
                    c_matrix.setElement(
                        Integer.valueOf(i), Integer.valueOf(j),
                        c_matrix.getElement(i, j) - c * c_matrix.getElement(k, j)
                        );
                
                c_matrix.setElement(Integer.valueOf(i), Integer.valueOf(k), 0.0);
            }
        }

        return c_matrix;
    }

    
}
