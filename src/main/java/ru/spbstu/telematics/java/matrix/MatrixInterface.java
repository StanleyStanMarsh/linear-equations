package ru.spbstu.telematics.java.matrix;

import java.util.ArrayList;

public interface MatrixInterface 
{
    Double getElement(Integer row, Integer col);
    void setElement(Integer row, Integer col, Double value);
    Integer getRowCount();
    Integer getColumnCount();
    ArrayList<Double> getColumn(Integer index);
    ArrayList<Double> getRow(Integer index);
    MatrixInterface transpose();
    void printMatrix();
}