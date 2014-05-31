
import org.database.models.GrupaCwiczeniowa;
import java.util.List;
import org.testng.annotations.Test;

/**
 *
 * @author Ja
 */
public class GrupaCwiczeniowaTest {

    @Test
    public void test1() {
        //GrupaCwiczeniowa.add("PSI2");
        List<GrupaCwiczeniowa> grupyCw = GrupaCwiczeniowa.getAll();
        for (GrupaCwiczeniowa grupaCw : grupyCw) {
            System.out.println("GrupaCwiczeniowa{" + "id=" + grupaCw.getId() + ", nazwa=" + grupaCw.getNazwa() + ", przedmiot=" + grupaCw.getPrzedmiot().getNazwa() + '}');
        }
        /*GrupaCwiczeniowa.delete(3);
         GrupaCwiczeniowa grupaCw = new GrupaCwiczeniowa();
         grupaCw.setId(3);
         grupaCw.setNazwa("PSI3");
         GrupaCwiczeniowa.edit(grupaCw);*/
    }
}
