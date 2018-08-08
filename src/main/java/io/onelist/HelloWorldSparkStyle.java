package io.onelist;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class HelloWorldSparkStyle {
    public static void main(String[] args) {
        final Configuration config = new Configuration();
        config.setClassForTemplateLoading(
                HelloWorldFreemarkerStyle.class, "/"
        );
        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                StringWriter writer = new StringWriter();
                try {
                    Template helloTemplate = config.getTemplate("hello.ftl");
                    Map<String, Object> helloMap = new HashMap<>();
                    helloMap.put("name", "Freemarker");

                    helloTemplate.process(helloMap, writer);
                } catch (Exception e) {
                    halt(500);
                    e.printStackTrace();
                }
                return writer;
            }
        });
    }
}
