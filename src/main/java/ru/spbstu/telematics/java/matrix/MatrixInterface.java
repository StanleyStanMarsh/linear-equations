package ru.spbstu.telematics.java.matrix;

import java.util.ArrayList;

/**
 * The MatrixInterface provides a blueprint for creating matrix data structures in Java. 
 * It offers essential operations for manipulating and retrieving data from matrices.
 */
public interface MatrixInterface 
{
    /**
     * Retrieves the element at the specified row and column.
     * 
     * @param row: The row index of the element.
     * @param col: The column index of the element.
     * @return Double value of the element at the specified position.
     */
    Double getElement(Integer row, Integer col);

    /**
     * Sets the element at the specified row and column to the given value.
     * 
     * @param row: The row index where the element should be set.
     * @param col: The column index where the element should be set.
     */
    void setElement(Integer row, Integer col, Double value);

    /**
     * Provides the number of rows in the matrix.
     * 
     * @return Integer representing the total number of rows.
     */
    Integer getRowCount();

    /**
     * Provides the number of columns in the matrix.
     * 
     * @return Integer representing the total number of columns.
     */
    Integer getColumnCount();

    /**
     * Retrieves all elements from the specified column.
     * 
     * @param index: The index of the column.
     * @return ArrayList<Double> containing elements from the specified column.
     */
    ArrayList<Double> getColumn(Integer index);

    /**
     * Retrieves all elements from the specified row.
     * 
     * @param index: The index of the row.
     * @return ArrayList<Double> containing elements from the specified row.
     */
    ArrayList<Double> getRow(Integer index);

    /**
     * Creates a new matrix that is the transpose of the current matrix.
     * 
     * @return MatrixInterface representing the transpose of the current matrix.
     */
    MatrixInterface transpose();

    /**
     * Prints the matrix to the standard output in a readable format.
     */
    void printMatrix();
}