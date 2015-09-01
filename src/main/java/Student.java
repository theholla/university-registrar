import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Student {
  private int id;
  private String name;
  private String enroll_date;

  public Student(String name, String enrollDate) {
    this.name = name;
    this.enroll_date = enrollDate;
  }

  public String getName() {
    return name;
  }

  public String getEnrollDate() {
    return enroll_date;
  }

  public int getId() {
    return id;
  }

  public static List<Student> all() {
    String sql = "SELECT id, name, enroll_date FROM students";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Student.class);
    }
  }

  @Override
  public boolean equals(Object otherStudent) {
    if (!(otherStudent instanceof Student)) {
      return false;
    } else {
      Student newStudent = (Student) otherStudent;
      return this.getName().equals(newStudent.getName()) &&
             this.getEnrollDate().equals(newStudent.getEnrollDate()) &&
             this.getId() == newStudent.getId();
    }
  }
}
