
import org.testng.annotations.Test;
import org.database.models.Student;
import java.util.List;

/**
 *
 * @author Ja
 */
public class StudentTest {

    @Test
    public void test1() {
        List<Student> students = Student.getAll();
        for (Student s : students) {
            System.out.println(s.toString());
        }
        /*Student.delete(0);
         Student student = new Student();
         student.setId(3);
         student.setImie("Władysław");
         student.setNazwisko("Łokietek");
         student.setEmail("");
         student.setIndeks(341234);
         Student.edit(student);

         students = Student.getAll();
         for (Student s : students) {
         System.out.println(s.toString());
         }*/
    }
}
