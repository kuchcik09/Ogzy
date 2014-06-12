package org.gui;

import java.util.ArrayList;

/**
 *
 * @author Ja
 */
public class Row {

    private static final int DEFAULT_ROW_NUMBER = 4;

    private final ArrayList<Object> data;

    public Row() {
        data = new ArrayList<Object>(DEFAULT_ROW_NUMBER);
    }

    public Row(int initialCapacity) {
        data = new ArrayList<Object>(initialCapacity);
    }

    public Object getRow(int nr) {
        return data.get(nr);
    }

    public void setRow(int indeks, Object value) {
        data.add(indeks, value);
    }

}
