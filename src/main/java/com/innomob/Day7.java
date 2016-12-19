package com.innomob;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 {

    static Pattern bracket = Pattern.compile("\\[([a-z]*)]");
    static Pattern aba = Pattern.compile("(?=([a-z])(.)\\1)");

    public static void main(String[] args) throws IOException {
        int resultTLS = 0;
        int resultSSL = 0;
        final List<String> lines = FileUtils.readLines(new File("day7.txt"));

        for(String line: lines) {
            if (supportTLS(line)) {
                resultTLS++;
            }
            if (supportSSL(line)) {
                resultSSL++;
            }
        }

        System.out.println(resultTLS);
        System.out.println(resultSSL);

    }

    static boolean supportTLS(String line) {
        Matcher bracketMatcher = bracket.matcher(line);
        while (bracketMatcher.find()) {
            if(containsPalindrome(bracketMatcher.group(1))) {
                return false;
            }
            line = line.replace(bracketMatcher.group(0), ",");
        }
        for(String outside: line.split(",")) {
            if(containsPalindrome(outside)) {
                return true;
            }
        }
        return false;
    }

    static boolean supportSSL(String line) {
        Matcher bracketMatcher = bracket.matcher(line);
        List<String> brackets = new ArrayList<>();
        List<String> outsides = new ArrayList<>();
        while (bracketMatcher.find()) {
            brackets.add(bracketMatcher.group(1));
            line = line.replace(bracketMatcher.group(0), ",");
        }
        for(String outside: line.split(",")) {
            outsides.add(outside);
        }
        for(String outside: outsides) {
            Matcher m = aba.matcher(outside);
            while (m.find()) {
                String abaString = m.group(2) + m.group(1) + m.group(2);
                for(String bracket: brackets) {
                    if(bracket.contains(abaString)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static boolean containsPalindrome(String input) {
        int startIndex = 0;
        int length;
        boolean found = false;
        while (!found && startIndex <= input.length() - 4) {
            length = 2;
            String subString = input.substring(startIndex);
            while(!found && subString.length() >= length * 2) {
                if(checkPalindrome(subString, length)) {
                    found = true;
                }
                length++;
            }
            startIndex++;
        }
        return found;
    }

    static boolean checkPalindrome(String input, int length) {
        if(length * 2 > input.length()) {
            return false;
        }
        char firstChar = input.charAt(0);
        String checkSameCharString = "";
        for(int i = 0; i < length*2; i++) {
            checkSameCharString+=firstChar;
        }
        if(input.substring(0, length*2).equals(checkSameCharString)) {
            return false;
        }
        return input.substring(0, length).equals(reverseString(input.substring(
                length, length * 2)));
    }

    static String reverseString(String in) {
        return new StringBuilder(in).reverse().toString();
    }

}
