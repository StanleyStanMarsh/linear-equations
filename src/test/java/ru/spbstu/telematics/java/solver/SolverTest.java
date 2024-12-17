package ru.spbstu.telematics.java.solver;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import ru.spbstu.telematics.java.matrix.Matrix;
import ru.spbstu.telematics.java.exceptions.WrongCoefficientsMatrixException;

public class SolverTest {
    
    private static final double DELTA = 0.0001;

    @Test
    public void testSolveWithUniqueSolution() throws WrongCoefficientsMatrixException {
        // Система уравнений:
        // 3x + 2y - 4z = 3
        // 2x + 3y + 3z = 15
        // 5x - 3y + z = 14
        Matrix matrix = new Matrix(3, 4);
        matrix.setElement(0, 0, 3.0);
        matrix.setElement(0, 1, 2.0);
        matrix.setElement(0, 2, -4.0);
        matrix.setElement(0, 3, 3.0);
        matrix.setElement(1, 0, 2.0);
        matrix.setElement(1, 1, 3.0);
        matrix.setElement(1, 2, 3.0);
        matrix.setElement(1, 3, 15.0);
        matrix.setElement(2, 0, 5.0);
        matrix.setElement(2, 1, -3.0);
        matrix.setElement(2, 2, 1.0);
        matrix.setElement(2, 3, 14.0);

        ArrayList<Double> solution = Solver.solve(matrix);
        
        assertEquals(3, solution.size());
        assertEquals(3.0, solution.get(0), DELTA); // x = 3
        assertEquals(1.0, solution.get(1), DELTA); // y = 1
        assertEquals(2.0, solution.get(2), DELTA); // z = 2
    }

    @Test
    public void testSolveWithNoSolution() throws WrongCoefficientsMatrixException {
        // Система уравнений:
        // x + y = 1
        // x + y = 2
        Matrix matrix = new Matrix(2, 3);
        matrix.setElement(0, 0, 1.0);
        matrix.setElement(0, 1, 1.0);
        matrix.setElement(0, 2, 1.0);
        matrix.setElement(1, 0, 1.0);
        matrix.setElement(1, 1, 1.0);
        matrix.setElement(1, 2, 2.0);

        ArrayList<Double> solution = Solver.solve(matrix);
        
        assertTrue(solution.isEmpty());
    }

    @Test
    public void testSolveWithInfiniteSolutions() throws WrongCoefficientsMatrixException {
        // Система уравнений:
        // x + y = 2
        // x + y = 2
        Matrix matrix = new Matrix(2, 3);
        matrix.setElement(0, 0, 1.0);
        matrix.setElement(0, 1, 1.0);
        matrix.setElement(0, 2, 2.0);
        matrix.setElement(1, 0, 1.0);
        matrix.setElement(1, 1, 1.0);
        matrix.setElement(1, 2, 2.0);

        ArrayList<Double> solution = Solver.solve(matrix);
        
        assertEquals(2, solution.size());
        assertEquals(Double.POSITIVE_INFINITY, solution.get(0), DELTA);
        assertEquals(Double.POSITIVE_INFINITY, solution.get(1), DELTA);
    }

    @Test(expected = WrongCoefficientsMatrixException.class)
    public void testSolveWithInvalidMatrix() throws WrongCoefficientsMatrixException {
        Matrix matrix = new Matrix(2, 2); // Неправильная матрица (должна быть расширенная)
        Solver.solve(matrix);
    }
}