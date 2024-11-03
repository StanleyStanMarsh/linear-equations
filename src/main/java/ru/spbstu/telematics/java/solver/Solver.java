package ru.spbstu.telematics.java.solver;

import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;
import ru.spbstu.telematics.java.matrix.Matrix;
import ru.spbstu.telematics.java.exceptions.*;

public class Solver 
{
    private static void swapRows(Matrix matrix, Integer row1, Integer row2) 
    {
        ArrayList<Double> tempRow = matrix.getRow(row1);
        matrix.setRow(row1, matrix.getRow(row2));
        matrix.setRow(row2, tempRow);
    }

    public static Matrix toRowEchelonForm(Matrix augmented_matrix) throws WrongCoefficientsMatrixException
    {
        if (augmented_matrix.getRowCount() + 1 != augmented_matrix.getColumnCount())
            throw new WrongCoefficientsMatrixException("The given matrix is not augmented coefficient matrix");

        Matrix c_matrix = new Matrix(augmented_matrix);
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

    private static Integer ifSingular(Matrix augmented_matrix)
    {
        for (int i = 0; i < augmented_matrix.getRowCount(); i++)
            if (augmented_matrix.getElement(Integer.valueOf(i), Integer.valueOf(i)) == 0)
                return Integer.valueOf(i);
        return -1;
    }

    public static ArrayList<Double> solve(Matrix augmented_matrix) throws WrongCoefficientsMatrixException
    {
         Matrix c_matrix = Solver.toRowEchelonForm(augmented_matrix);
        Integer n_col = c_matrix.getColumnCount();
        Integer n_row = c_matrix.getRowCount();

        Integer ind = Solver.ifSingular(c_matrix);

        if (ind != -1)
        {
            // return infinitely many solutions
            if (c_matrix.getElement(Integer.valueOf(ind), Integer.valueOf(n_col - 1)) == 0.0)
                return new ArrayList<>(Collections.nCopies(n_row.intValue(), Double.valueOf(Double.POSITIVE_INFINITY)));
            // inconsistent system
            return new ArrayList<Double>();
        }

        ArrayList<Double> answer = new ArrayList<Double>(Collections.nCopies(n_row.intValue(), Double.valueOf(0.0)));

        for (int i = n_row - 1; i >= 0; i--)
        {
            answer.set(i, c_matrix.getElement(i, n_row));
        
            for (int j = i + 1; j < n_col - 1; j++) 
            {
                /* subtract all the lhs values
                        * except the coefficient of the variable
                        * whose value is being calculated */
                Double middle_res_i = answer.get(i);
                Double middle_res_j = answer.get(j);
                answer.set(
                    i, 
                    Double.valueOf(middle_res_i.doubleValue() - (c_matrix.getElement(i, j).doubleValue()) * middle_res_j.doubleValue())
                    );
            }
        
            /* divide the RHS by the coefficient of the
                    unknown being calculated */
            Double dividend = answer.get(i);
            answer.set(i, dividend.doubleValue() / c_matrix.getElement(i, i).doubleValue());
        }


        return answer;
    }

    public static void printAnswer(ArrayList<Double> answer)
    {
        if (answer.isEmpty()) 
        {
            System.out.println("System does not have solutions.");
            return;
        }

        for (int i = 0; i < answer.size(); i++) 
        {
            Double value = answer.get(i);
            if (value.equals(Double.POSITIVE_INFINITY)) 
            {
                System.out.println("The system has infintely many solutions.");
                return;
            }
            else 
            {
                System.out.printf("Variable x%d = %.4f\n", i + 1, value);
            }
        }
    }
}
