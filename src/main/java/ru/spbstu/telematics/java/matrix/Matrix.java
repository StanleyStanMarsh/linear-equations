package ru.spbstu.telematics.java.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * The Matrix class implements the MatrixInterface and provides a concrete representation of 
 * a mathematical 2-dimensional matrix using ArrayList of Double objects. 
 */
public class Matrix implements MatrixInterface 
{
    private ArrayList<ArrayList<Double>> matrix;

    /**
     * Constructs a matrix with the specified number of rows and columns, 
     * initializing all elements to 0.0.
     * 
     * @param numRows The number of rows in the matrix.
     * @param numCols The number of columns in the matrix.
     */
    public Matrix(Integer numRows, Integer numCols) 
    {
        matrix = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++)
        {
            ArrayList<Double> row = new ArrayList<>(numCols);
            for (int j = 0; j < numCols; j++) 
            {
                row.add(0.0);
            }
            matrix.add(row);
        }
    }

    /**
     * Copy constructor that creates a new matrix by copying all elements from an existing matrix.
     * 
     * @param original The matrix to copy.
     */
    public Matrix(Matrix original) {
        this(original.getRowCount(), original.getColumnCount());
        for (int i = 0; i < original.getRowCount(); i++) {
            for (int j = 0; j < original.getColumnCount(); j++) {
                this.setElement(
                    Integer.valueOf(i), Integer.valueOf(j), 
                    original.getElement(Integer.valueOf(i), Integer.valueOf(j))
                    );
            }
        }
    }

    /**
     * Retrieves the element at the specified row and column in the matrix.
     * 
     * @param row The row index of the element.
     * @param col The column index of the element.
     * @return The element at the specified row and column.
     */
    public Double getElement(Integer row, Integer col) throws IndexOutOfBoundsException
    {
        if (row >= 0 && row < matrix.size() && col >= 0 && col < matrix.get(row).size()) 
        {
            return matrix.get(row).get(col);
        } 
        else 
        {
            throw new IndexOutOfBoundsException("Wrong row or column index");
        }
    }

    /**
     * Sets the element at the specified row and column to the provided value.
     * 
     * @param row The row index of the element.
     * @param col The column index of the element.
     * @param value The value to be assigned to the specified position.
     * @trows IndexOutOfBoundsException if the row or column index is out of bounds.
     */
    public void setElement(Integer row, Integer col, Double value) throws IndexOutOfBoundsException
    {
        if (row >= 0 && row < matrix.size() && col >= 0 && col < matrix.get(row).size())
        {
            matrix.get(row).set(col, value);
        } 
        else
        {
            throw new IndexOutOfBoundsException("Wrong row or column index");
        }
    }

    /**
     * Returns the number of rows in the matrix.
     * 
     * @return Integer representing the total number of rows.
     */
    public Integer getRowCount()
    {
        return matrix.size();
    }

    /**
     * Returns the number of columns in the matrix.
     * 
     * @return Integer representing the total number of columns.
     */
    public Integer getColumnCount()
    {
        if (matrix.isEmpty()) {
            return 0;
        }
        return matrix.get(0).size();
    }

    /**
     * Retrieves all elements from the specified column.
     * 
     * @param index The index of the column.
     * @return ArrayList<Double> containing elements from the specified column.
     * @throws IndexOutOfBoundsException if the column index is out of bounds.
     */
    public ArrayList<Double> getColumn(Integer index) throws IndexOutOfBoundsException
    {
        ArrayList<Double> column = new ArrayList<>();
        for (List<Double> row : matrix) 
        {
            if (index < row.size()) 
            {
                column.add(row.get(index));
            } 
            else
            {
                throw new IndexOutOfBoundsException("Column index out of bounds: " + index);
            }
        }
        return column;
    }

    /**
     * Retrieves all elements from the specified row.
     * 
     * @param index The index of the row.
     * @return ArrayList<Double> containing elements from the specified row.
     * @throws IndexOutOfBoundsException if the row index is out of bounds.
     */
    public ArrayList<Double> getRow(Integer index) throws IndexOutOfBoundsException
    {
        if (index < matrix.size()) 
        {
            return new ArrayList<>(matrix.get(index));
        } 
        else 
        {
            throw new IndexOutOfBoundsException("Row index out of bounds: " + index);
        }
    }

    /**
     * Sets all elements from the specified row.
     * 
     * @param index The index of the row.
     * @param row The row to be assigned to the specified position.
     * @throws IndexOutOfBoundsException if the row index is out of bounds.
     */
    public void setRow(Integer index, ArrayList<Double> row) throws IndexOutOfBoundsException
    {
        if (row.size() != this.getColumnCount())
        {
            throw new IndexOutOfBoundsException("Wrong new row size");
        }
        for (int j = 0; j < this.getColumnCount(); j++)
        {
            this.setElement(index, j, row.get(j));
        }
    }

    /**
     * Produces a new Matrix object which is the transpose of this matrix. Rows and columns are swapped.
     * 
     * @return The transposed matrix.
     */
    public MatrixInterface transpose() 
    {
        MatrixInterface transposedMatrix = new Matrix(this.getColumnCount(), this.getRowCount());

        for (int i = 0; i < this.getRowCount(); i++) 
        {
            for (int j = 0; j < this.getColumnCount(); j++) 
            {
                transposedMatrix.setElement(Integer.valueOf(j), Integer.valueOf(i), this.getElement(i, j));
            }
        }

        return transposedMatrix;
    }

    /**
     * Prints the matrix in a formatted manner to the console.
     */
    public void printMatrix() 
    {
        for (List<Double> row : matrix) 
        {
            for (Double value : row) 
            {
                System.out.printf("%10.2f ", value);
            }
            System.out.println();
        }
    }
}