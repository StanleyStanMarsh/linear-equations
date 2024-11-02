
package ru.spbstu.telematics.java.matrix;

import java.util.ArrayList;
import ru.spbstu.telematics.java.util.Pair;

public class SqMatrix extends Matrix 
{

    public SqMatrix(Integer size) 
    {
        super(size, size);
    }

    public SqMatrix(SqMatrix original) {
        super(original.getRowCount(), original.getRowCount());
        for (int i = 0; i < original.getRowCount(); i++) {
            for (int j = 0; j < original.getRowCount(); j++) {
                this.setElement(
                    Integer.valueOf(i), Integer.valueOf(j), 
                    original.getElement(Integer.valueOf(i), Integer.valueOf(j))
                    );
            }
        }
    }
}
