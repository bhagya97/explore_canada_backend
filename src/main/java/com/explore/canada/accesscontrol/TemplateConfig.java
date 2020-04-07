package com.explore.canada.accesscontrol;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;

import java.io.*;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

public class TemplateConfig {
    private String htmlTemplate =
            "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <title>Explore Canada</title>\n" +
            "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
            "</head>\n" +
            "<body>\n" +
            "<p>\n" +
            "    Hello {name}\n" +
            "</p>\n" +
            "\n" +
            "    {message}\n" +
            "\n" +
            "<p>\n" +
            "    Regards, <br />\n" +
            "    <em>{signature}</em>\n" +
            "</p>\n" +
            "</body>\n" +
            "</html>";

    public String process(String templateName, Map<String,String> parameters){
        StringBuilder htmlBuilder = new StringBuilder();
        try {

            Scanner reader = new Scanner(htmlTemplate);
            String line = null;
            while((line = reader.nextLine()) != null){
                for(String key : parameters.keySet()){
                    String content = "{" + key + "}";
                    if(line.contains(content)){
                        line = line.replace(content, parameters.get(key));
                    }
                }
                htmlBuilder.append(line);
            }
        }
        catch (Exception ex){
            System.out.println(ex.toString());
        }
        return htmlBuilder.toString();
    }

    /*
    public String process(String templateName, Map<String,String> parameters){
        StringBuilder htmlBuilder = new StringBuilder();
        try {
            Resource resource = new ClassPathResource(templateName);
            InputStream input = resource.getInputStream();
            File file = resource.getFile();
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = null;
            while((line = bufferedReader.readLine()) != null){
                for(String key : parameters.keySet()){
                    String content = "{" + key + "}";
                    if(line.contains(content)){
                        line = line.replace(content, parameters.get(key));
                    }
                }
                htmlBuilder.append(line);
            }
        }
        catch (Exception ex){
            System.out.println(ex.toString());
        }
        return htmlBuilder.toString();
    }
     */
}
