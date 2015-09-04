import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    /* Index */
    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    /* Index --> Courses list */
    get("/courses", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("courses", Course.all());
      model.put("template", "templates/courses.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    /* Index --> Students list */
    get("/students", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("students", Student.all());
      model.put("template", "templates/students.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    /* Courses list --> POST a new course (displays on course list view) */
    post("/courses", (request, reponse) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String course_name = request.queryParams("course_name");
      String course_number = request.queryParams("course_number");
      Course newCourse = new Course(course_name, course_number);
      newCourse.save();
      model.put("courses", Course.all());
      model.put("template", "templates/courses.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    /* Courses list --> view individual course */
    get("/courses/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Course course = Course.find(id);
      Student student = Student.find(id);
      model.put("course", course);
      model.put("students", course.getStudents());
      model.put("allStudents", Student.all());
      model.put("template", "templates/course.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    /* Student list --> POST a new student (displays on student list view) */
    post("/students/new", (request, reponse) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String enroll_date = request.queryParams("enroll_date");
      Student newStudent = new Student(name, enroll_date);
      newStudent.save();
      model.put("students", Student.all());
      model.put("template", "templates/students.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    /* Student list && Course page --> view individual student */
    get("/students/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Student student = Student.find(id);
      model.put("allCourses", Course.all());
      model.put("student", student);
      model.put("courses", student.getCourses());
      model.put("template", "templates/student.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    /* Individual course --> POST a student to the course (displays on course view) */
    post("/courses/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Course course = Course.find(Integer.parseInt(request.params("id")));
      model.put("course", course);
      model.put("students", Student.all());

      /* The next block creates a new student from each select. Instead, find the id of the first student.*/
      String name = request.queryParams("name");
      String enrollDate = request.queryParams("enroll_date");
      Student newStudent = new Student(name, enrollDate);
      newStudent.save();
      course.assignStudent(newStudent);

      response.redirect("/courses/" + request.params(":id"));
      return null;
    });








  }
}
