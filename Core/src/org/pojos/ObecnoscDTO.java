package org.pojos;

import java.util.ArrayList;
import java.util.List;

public class ObecnoscDTO {

    private Object student;
    private final List<Boolean> obecnosci;

    public ObecnoscDTO(Object student, List<Boolean> obecnosci) {
        this.student = student;
        this.obecnosci = obecnosci;
    }

    public Object getStudent() {
        return student;
    }

    public List<Boolean> getObecnosci() {
        return obecnosci;
    }

    public void setStudent(Object student) {
        this.student = student;
    }

}
