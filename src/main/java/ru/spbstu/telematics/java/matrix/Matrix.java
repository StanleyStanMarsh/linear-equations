package ru.spbstu.telematics.java.matrix;

import java.util.ArrayList;

public class Matrix implements MatrixInterface 
{
    private ArrayList<ArrayList<Double>> matrix;

    public Matrix(int numRows, int numCols) 
    {
        data = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++)
        {
            List<Double> row = new ArrayList<>(numCols);
            for (int j = 0; j < numCols; j++) 
            {
                row.add(0.0);
            }
            data.add(row);
        }
    }

    public Double getElement(Integer row, Integer col) 
    {
        if (row >= 0 && row < data.size() && col >= 0 && col < data.get(row).size()) 
        {
            return data.get(row).get(col);
        } 
        else 
        {
            throw new IndexOutOfBoundsException("Неверные индексы строки или столбца");
        }
    }

    public void setElement(Integer row, Integer col, Double value)
    {
        if (row >= 0 && row < data.size() && col >= 0 && col < data.get(row).size())
        {
            data.get(row).set(col, value);
        } 
        else
        {
            throw new IndexOutOfBoundsException("Неверные индексы строки или столбца");
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
        return new ArrayList<Double>();
    }

    public ArrayList<Double> getRow(Integer index)
    {
        return new ArrayList<Double>();
    }

    public MatrixInterface transpose()
    {
        return new SqMatrix(this.size);
    }

    public void printMatrix() 
    {
        for (List<Double> row : data) 
        {
            for (Double value : row) 
            {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}