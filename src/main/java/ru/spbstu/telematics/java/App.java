package ru.spbstu.telematics.java;

import java.util.ArrayList;
import ru.spbstu.telematics.java.matrix.Matrix;
import ru.spbstu.telematics.java.solver.Solver;
import ru.spbstu.telematics.java.exceptions.*;
import ru.spbstu.telematics.java.solver.EquationInput;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Matrix originalMatrix;
        ArrayList ans;
        
        try
        {
            originalMatrix = EquationInput.readEquationsToMatrix();

            System.out.println("Original matrix:");
            originalMatrix.printMatrix();
            System.out.println("Triangular matrix:");
            

            Solver.toRowEchelonForm(originalMatrix).printMatrix();
            ans = Solver.solve(originalMatrix);
            Solver.printAnswer(ans);
        }
        catch (WrongCoefficientsMatrixException e)
        {
            System.out.println("Invalid input matrix: " + e.getMessage());
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Invalid input matrix: " + e.getMessage());
        }
    }
}
