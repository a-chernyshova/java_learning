package org.learning.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        if (args.length <= 1) {
            System.out.println("File and node names are required");
            //lterm
            //C:\Users\Anastasiia_Chernysho\epam_trainings\final_java_repo\java_learning\jsonparser\src\main\resources\test.json
            RequestArgs requestArgs = new RequestArgs().invoke();
            File source = requestArgs.getSource();
            String nodeName = requestArgs.getNodeName();
            if (!source.exists()) {
                //exit("File isn't found");
                throw new RuntimeException("File not found");
            }
            getNodeValue(source, nodeName);
        } else {
            System.out.println(args[0]);
            File source = new File(args[0]);
            String nodeName = args[1];
            if (!source.exists()) {
                //exit("File isn't found");
                throw new RuntimeException("File not found");
            }
            getNodeValue(source, nodeName);
        }
    }

    private static void getNodeValue(File source, String nodeName) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode root = objectMapper.readTree(source);
            JsonNode node = root.findValue(nodeName);
            if (node == null){
                System.out.println("Node isn't found");
            } else {
                System.out.println(node);
            }
        } catch (IOException e) {
            System.out.println("Incorrect json structure. \n" + e.getMessage());
        }
    }

    private static void exit(String s) {
        System.out.println(s);
        System.exit(100);
    }
}
