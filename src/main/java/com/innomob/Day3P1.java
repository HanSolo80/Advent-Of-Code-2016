package com.innomob;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Day3P1 {

    static int count = 0;

    public static void main(String[] args) throws IOException {
        final List<String> lines = FileUtils.readLines(new File("day3.txt"));
        for (String line : lines) {
            String[] values = line.split("\\s+");
            String[] croppedValues = new String[] {values[1], values[2], values[3]};

            if(isTriangle(croppedValues)) {
                count++;
            }
        }
        System.out.println(count);
    }

    public static boolean isTriangle(String[] values) {

        int v1 = Integer.parseInt(values[0]);
        int v2 = Integer.parseInt(values[1]);
        int v3 = Integer.parseInt(values[2]);

        if(v1 + v2 <= v3) {
            return false;
        }
        if(v3 + v2 <= v1) {
            return false;
        }
        if(v1 + v3 <= v2) {
            return false;
        }
        return true;
    }

}
