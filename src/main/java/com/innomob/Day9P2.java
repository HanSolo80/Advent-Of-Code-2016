package com.innomob;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day9P2 {

    static Pattern markerPattern = Pattern.compile("(\\((\\d+)x(\\d+)\\))");
    static String input;
    static String result = "";

    static class ProcessResult {
        String remainingInput;
        String result;
    }

    public static void main(String[] args) throws IOException {

        input = FileUtils.readFileToString(new File("day9.txt"));
        //input = "(27x12)(20x12)(13x14)(7x10)(1x12)A";
        System.out.println(processNext(input));

    }


    static long processNext(String input) {
        Matcher m = markerPattern.matcher(input);
        if (m.find()) {
            String marker = m.group(1);
            int chars = Integer.parseInt(m.group(2));
            int times = Integer.parseInt(m.group(3));
            String prefix = input.substring(0, input.indexOf(marker));
            String suffix = input.substring(input.indexOf(marker) + marker.length() + chars);
            long replaced = 0;
            String repeatString = input.substring(input.indexOf(marker) + marker.length(), input.indexOf(marker) + marker.length() + chars);
            for(int i = 0; i < times; i++) {
                replaced += processNext(repeatString);
            }
            return (long) prefix.length() + replaced + processNext(suffix);
        } else {
            return (long) input.length();
        }
    }
}
