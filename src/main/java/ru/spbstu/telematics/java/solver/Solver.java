package ru.spbstu.telematics.java.solver;

import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;
import ru.spbstu.telematics.java.matrix.Matrix;
import ru.spbstu.telematics.java.exceptions.*;

/**
 * The Solver class provides static methods to manipulate matrices, specifically 
 * for transforming a matrix into row echelon form using Gaussian method (elimination) 
 * for solving systems of linear equations. 
 */
public class Solver 
{
    /**
     * This utility method swaps two specified rows within the given matrix.
     * 
     * @param matrix The matrix to be modified.
     * @param row1 The first row to be swapped.
     * @param row2 The second row to be swapped.
     */
    private static void swapRows(Matrix matrix, Integer row1, Integer row2) 
    {
        ArrayList<Double> tempRow = matrix.getRow(row1);
        matrix.setRow(row1, matrix.getRow(row2));
        matrix.setRow(row2, tempRow);
    }

    /**
     * Converts an augmented matrix into its row echelon form using Gaussian elimination. 
     * This form has important applications for solving systems of linear equations.
     * 
     * @param augmented_matrix An augmented matrix where the last column represents the constants from the equations.
     * @return The augmented matrix in row echelon form.
     * @throws WrongCoefficientsMatrixException if the provided matrix does not have a valid form for transformation.
     */
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

    /**
     * Determines if a matrix is singular by checking for zero elements on the diagonal. 
     * A singular matrix does not have a unique solution.
     * 
     * @param augmented_matrix The matrix to be checked for singularity.
     * @return The index of the first row with a zero diagonal element, 
     * indicating singularity. Returns -1 if the matrix is non-singular.
     */
    private static Integer ifSingular(Matrix augmented_matrix)
    {
        for (int i = 0; i < augmented_matrix.getRowCount(); i++)
            if (augmented_matrix.getElement(Integer.valueOf(i), Integer.valueOf(i)) == 0)
                return Integer.valueOf(i);
        return -1;
    }

    /**
     * Solves the system of linear equations represented by the given 
     * augmented matrix using back substitution on its row echelon form. 
     * Handles cases with unique, infinite, or no solutions.
     * 
     * @param augmented_matrix The augmented matrix representing the system of equations.
     * @return  ArrayList<Double> containing the solutions to the system. 
     * If the system has infinitely many solutions, the list is filled with 
     * Double.POSITIVE_INFINITY. If inconsistent, returns an empty list.
     * @throws WrongCoefficientsMatrixException if the provided matrix does not have 
     * a suitable form for solving (checked during row echelon conversion).
     */
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

    /**
     * Prints the solution of the system of equations. Handles messages for unique solutions, 
     * infinitely many solutions, or no solutions.
     * 
     * @param answer The list of solutions obtained from solving the system.
     */
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
