package ru.spbstu.telematics.java.util;


public class Pair<V1, V2> {
    private V1 first;
    private V2 second;

    public Pair(V1 value1, V2 value2) {
        this.first = value1;
        this.second = value2;
    }

    public V1 getFirst() {
        return first;
    }

    public void setFirst(V1 value) {
        this.first = value;
    }

    public V2 getSecond() {
        return second;
    }

    public void setSecond(V2 value) {
        this.second = value;
    }
}
