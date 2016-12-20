package com.innomob;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day9P1 {

    static Pattern markerPattern = Pattern.compile("(\\((\\d+)x(\\d+)\\))");
    static String input;
    static String result = "";

    public static void main(String[] args) throws IOException {

        input = FileUtils.readFileToString(new File("day9.txt"));
        //input = "(6x1)(1x3)A";
        System.out.println(input.length());

        /*System.out.println(test1.indexOf("(2x2)"));
        System.out.println(test1.substring(0,1));
        System.out.println(test1.substring(1+6));*/

        boolean hasNext = true;
        while (hasNext) {
            hasNext = processNext();
        }
        //System.out.println(result);
        System.out.println(result.length());

    }

    static boolean processNext() {
        Matcher m = markerPattern.matcher(input);
        if (m.find()) {
            String marker = m.group(1);
            int chars = Integer.parseInt(m.group(2));
            int times = Integer.parseInt(m.group(3));
            result += input.substring(0, input.indexOf(marker));
            input = input.substring(input.indexOf(marker) + marker.length());
            String repeatString = input.substring(0, chars);
            for(int i = 0; i < times; i++) {
                result += repeatString;
            }
            input = input.substring(chars);
            return true;
        } else {
            result += input;
            input = "";
            return false;
        }
    }
}
