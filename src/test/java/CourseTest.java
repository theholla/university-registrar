import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class CourseTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
      assertEquals(Course.all().size(), 0);
  }

  @Test
  public void course_instantiatesCorrectly_true() {
    Course testCourse = new Course("Margarita", "09/25/15");
    assertTrue(testCourse instanceof Course);
  }

  @Test
  public void equals_returnsTrueIfNamesAndEnrollDateAretheSame() {
    Course testCourse = new Course("Jane", "09/01/15");
    Course nextCourse = new Course("Jane", "09/01/15");
    assertTrue(testCourse.equals(nextCourse));
  }

  @Test
  public void save_savesNewCourseToDatabase_true() {
    Course newCourse = new Course("Jane", "09/01/15");
    newCourse.save();
    Course savedCourse = Course.all().get(0);
    assertTrue(savedCourse.equals(newCourse));
  }

  @Test
  public void save_savesIdToObject() {
    Course testCourse = new Course("Joaquin", "08/25/15");
    testCourse.save();
    Course savedCourse = Course.all().get(0);
    assertEquals(testCourse.getId(), savedCourse.getId());
  }

  @Test
  public void find_findsCourseInDatabase() {
    Course testCourse = new Course("Madison", "09/11/15");
    testCourse.save();
    Course savedCourse = Course.find(testCourse.getId());
    assertTrue(testCourse.equals(savedCourse));
  }



}
