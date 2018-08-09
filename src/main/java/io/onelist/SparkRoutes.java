package io.onelist;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class SparkRoutes {
    public static void main(String[] args) {
        final Configuration config = new Configuration();
        config.setClassForTemplateLoading(SparkRoutes.class, "/");

        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                try {
                    Map<String, Object> fruitsMap = new HashMap<>();
                    fruitsMap.put("fruits",
                                  Arrays.asList("apple", "orange", "coconut", "pineapple"));

                    Template fruitPickerTemplate = config.getTemplate("fruitPicker.ftl");
                    StringWriter writer = new StringWriter();
                    fruitPickerTemplate.process(fruitsMap, writer);

                    return writer;

                } catch (Exception e) {
                    halt(500);
                    e.printStackTrace();
                }
                return "This is the home.";
            }
        });

        Spark.post(new Route("/favorite_fruit") {
            @Override
            public Object handle(Request request, Response response) {
                final String fruit = request.queryParams("fruit");
                if (fruit == null) {
                    return "Pick a favorite fruit.";
                }
                return "Your favorite fruit is " + fruit;
            }
        });

        Spark.get(new Route("/echo/:thing") {
            @Override
            public Object handle(Request request, Response response) {
                return "This will echo ";
            }
        });
    }
}
