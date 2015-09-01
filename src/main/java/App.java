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

    

  }
}
