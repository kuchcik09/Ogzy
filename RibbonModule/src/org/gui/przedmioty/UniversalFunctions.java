package org.gui.przedmioty;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Mariushrek
 */
public class UniversalFunctions {

    /**
     * StartRokuAkademickiego() Zwraca aktualny start roku akademickiego
     * Przyjmujemy ze do 9 miesiacego (wrzesnia), wystepuje rok akademicki W tym
     * miesiacu pojawia się nowy rok akademicki.
     *
     * @return year
     */
    public static int startRokuAkademickiego() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        if (month < 9) {
            year--;
        }
        return year;
    }

    public static List<String> rokAkademicki() {
        List<String> lata = new ArrayList<String>();
        int year = startRokuAkademickiego();
        int year_end = year - 3;
        while (year != year_end) {
            lata.add(year + "/" + (year + 1));
            year--;
        }
        return lata;
    }
}
