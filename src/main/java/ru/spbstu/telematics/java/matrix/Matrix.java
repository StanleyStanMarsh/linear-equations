package ru.spbstu.telematics.java.matrix;

import java.util.ArrayList;
import java.util.List;

public class Matrix implements MatrixInterface 
{
    private ArrayList<ArrayList<Double>> matrix;

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

    public Double getElement(Integer row, Integer col) 
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

    public void setElement(Integer row, Integer col, Double value)
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

    public Integer getRowCount()
    {
        return matrix.size();
    }

    public Integer getColumnCount()
    {
        if (matrix.isEmpty()) {
            return 0;
        }
        return matrix.get(0).size();
    }

    public ArrayList<Double> getColumn(Integer index)
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

    public ArrayList<Double> getRow(Integer index)
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

    public void printMatrix() 
    {
        for (List<Double> row : matrix) 
        {
            for (Double value : row) 
            {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}