package com;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    private static String filePath = System.getProperty("filePath");

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode jsoneNode = objectMapper.readTree(new File(filePath));
            System.out.println("By key " + jsoneNode.get("test_value#2").get(0));
            jsoneNode.fields().forEachRemaining(
                    entry -> {
                        String name = entry.getKey();
                        JsonNode arrayNode = entry.getValue();
                        ArrayList values = new ArrayList();
                        if (arrayNode.isArray()) {
                            arrayNode.forEach(node -> values.add(node.toString()));
                        }
                        System.out.println(name + " : " + values);
                    }
            );
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
