package org.learning.parser;

import java.io.File;
import java.util.Scanner;

public class RequestArgs {
    private File source;
    private String nodeName;

    public File getSource() {
        return source;
    }

    public String getNodeName() {
        return nodeName;
    }

    public RequestArgs invoke() {
        Scanner input = new Scanner(System.in);
        System.out.println("Input path to file:");
        this.source = new File(input.nextLine());
        System.out.println("Input node name:");
        this.nodeName = input.nextLine();
        return this;
    }
}
