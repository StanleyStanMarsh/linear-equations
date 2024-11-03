package ru.spbstu.telematics.java.exceptions;

/**
 * The WrongCoefficientsMatrixException class is a custom exception 
 * that extends Java's Exception class. It is used to signal errors 
 * related to an improperly formatted coefficients matrix, 
 * particularly in the context of augmented matrices for linear equations.
 */
public class WrongCoefficientsMatrixException extends Exception
{
    public WrongCoefficientsMatrixException(String message)
    {
        super(message);
    }
}