package org.pojos;

import org.database.models.SEMESTR;
import org.database.models.TYP_OCENIANIA;

public class PrzedmiotDTO {

    private final String nazwa;
    private final int rokAkademicki;
    private final SEMESTR semestr;

    public PrzedmiotDTO(int id, String nazwa, String grupaOcen, TYP_OCENIANIA typOceniania, int rok_akademicki_start, SEMESTR semestr) {
        this.nazwa = nazwa;
        this.rokAkademicki = rok_akademicki_start;
        this.semestr = semestr;
    }

    public String getNazwa() {
        return nazwa;
    }

    public int getRokAkademicki() {
        return rokAkademicki;
    }

    public SEMESTR getSemestr() {
        return semestr;
    }

}
