package org.pojos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ObecnoscDTO {

    private Object tytul;
    private Object student;
    private final List<Date> daty = new ArrayList<Date>();
    private final List<Boolean> obecnosci = new ArrayList();

    public Object getTytul() {
        return tytul;
    }

    public void setTytul(Object tytul) {
        this.tytul = tytul;
    }

    public Object getStudent() {
        return student;
    }

    public List<Date> getDaty() {
        return daty;
    }

    public List<Boolean> getObecnosci() {
        return obecnosci;
    }

    public void setStudent(Object student) {
        this.student = student;
    }

}
