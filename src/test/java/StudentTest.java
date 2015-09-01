import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class StudentTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Student.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAndEnrollDateAretheSame() {
    Student testStudent = new Student("Jane", "09/01/15");
    Student nextStudent = new Student("Jane", "09/01/15");
    assertTrue(testStudent.equals(nextStudent));
  }

  @Test
  public void save_savesNewStudentToDatabase_true() {
    Student newStudent = new Student("Jane", "09/01/15");
    newStudent.save();
    Student savedStudent = Student.all().get(0);
    assertTrue(savedStudent.equals(newStudent));
  }

}
