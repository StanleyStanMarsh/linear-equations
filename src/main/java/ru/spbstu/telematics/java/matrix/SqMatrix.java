
package ru.spbstu.telematics.java.matrix;

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

    public SqMatrix toTriangular()
    {
        SqMatrix copy = new SqMatrix(this);
        for (int k = 1; k < copy.getRowCount(); k++)
        {
            for (int j = k; j < copy.getColumnCount(); j++)
            {
                if (copy.getElement(Integer.valueOf(k - 1), Integer.valueOf(k - 1)) == 0)
                {
                    break;
                }
                Double m = Double.valueOf(
                    copy.getElement(Integer.valueOf(j), Integer.valueOf(k - 1)) /
                    copy.getElement(Integer.valueOf(k - 1), Integer.valueOf(k - 1))
                    );
                for (int i = 0; i < copy.getRowCount(); i++)
                {
                    copy.setElement(
                        Integer.valueOf(j), Integer.valueOf(i),
                        copy.getElement(Integer.valueOf(j), Integer.valueOf(i)) - 
                        m * copy.getElement(Integer.valueOf(k - 1), Integer.valueOf(i))
                        );
                }
            }
        }
        return copy;
    }
}
