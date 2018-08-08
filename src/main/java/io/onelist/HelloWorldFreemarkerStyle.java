package io.onelist;

import freemarker.template.Configuration;

public class HelloWorldFreemarkerStyle {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setClassForTemplateLoading(
                HelloWorldFreemarkerStyle.class, "/"
        );
    }
}
