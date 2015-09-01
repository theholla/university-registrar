import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Course {
  private int id;
  private String course_name;
  private String course_number;

  public Course(String courseName, String enrollDate) {
    this.course_name = courseName;
    this.course_number = enrollDate;
  }

  public String getCourseName() {
    return course_name;
  }

  public String getCourseNumber() {
    return course_number;
  }

  public int getId() {
    return id;
  }

  public static List<Course> all() {
    String sql = "SELECT id, course_name, course_number FROM courses";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Course.class);
    }
  }

  @Override
  public boolean equals(Object otherCourse) {
    if (!(otherCourse instanceof Course)) {
      return false;
    } else {
      Course newCourse = (Course) otherCourse;
      return this.getCourseName().equals(newCourse.getCourseName()) &&
             this.getCourseNumber().equals(newCourse.getCourseNumber()) &&
             this.getId() == newCourse.getId();
    }
  }

  public void save() {
    String sql = "INSERT INTO courses (course_name, course_number) VALUES (:course_name, :course_number)";
    try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
        .addParameter("course_name", course_name)
        .addParameter("course_number", course_number)
        .executeUpdate()
        .getKey();
    }
  }

  public static Course find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM courses WHERE id = :id";
      Course course = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Course.class); //why FetchFirst?
      return course;
    }
  }

  public void assignStudent(Student myStudent) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO courses_students (course_id, student_id)" +
                    " VALUES (:course_id, :student_id)";
      con.createQuery(sql)
        .addParameter("course_id", this.getId())
        .addParameter("student_id", myStudent.getId())
        .executeUpdate();

    }
  }

  public ArrayList<Student> getStudents() {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT student_id FROM courses_students WHERE course_id = :course_id";
      List<Integer> studentIds = con.createQuery(sql)
        .addParameter("course_id", this.getId())
        .executeAndFetch(Integer.class);

      ArrayList<Student> students = new ArrayList<Student>();

      for (Integer studentId : studentIds) {
          String courseQuery = "SELECT * FROM students WHERE id = :student_id";
          Student student = con.createQuery(courseQuery)
            .addParameter("student_id", studentId)
            .executeAndFetchFirst(Student.class);
          students.add(student);
      }
      return students;
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String deleteQuery = "DELETE FROM courses WHERE id = :id";
      con.createQuery(deleteQuery)
        .addParameter("id", id)
        .executeUpdate();

      String joinDeleteQuery = "DELETE FROM courses_students WHERE course_id = :course_id";
      con.createQuery(joinDeleteQuery)
        .addParameter("course_id", this.getId())
        .executeUpdate();
    }
  }

}
