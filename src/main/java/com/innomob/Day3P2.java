package com.innomob;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day3P2 {

    static int count = 0;

    public static void main(String[] args) throws IOException {
        final String file = FileUtils.readFileToString(new File("day3.txt"));
        String[] values = file.split("\\s+");
        List<String> numberList = Arrays.asList(values).stream().filter(x -> !x.equals("")).collect(Collectors.toList());
        ArrayList<String> list1 = new ArrayList<>(3);
        ArrayList<String> list2 = new ArrayList<>(3);
        ArrayList<String> list3 = new ArrayList<>(3);
        for (int i = 0; i < numberList.size(); i++) {
            String value = numberList.get(i);
            switch (i % 3) {
                case 0:
                    list1.add(value);
                    break;
                case 1:
                    list2.add(value);
                    break;
                case 2:
                    list3.add(value);
                    break;
            }
            if (list3.size() == 3){
                if(isTriangle(list1)) {
                    count++;
                }
                if(isTriangle(list2)) {
                    count++;
                }
                if(isTriangle(list3)) {
                    count++;
                }
                list1.clear();
                list2.clear();
                list3.clear();
            }
        }
        System.out.println(count);
    }

    public static boolean isTriangle(ArrayList<String> values) {

        int v1 = Integer.parseInt(values.get(0));
        int v2 = Integer.parseInt(values.get(1));
        int v3 = Integer.parseInt(values.get(2));

        if (v1 + v2 <= v3) {
            return false;
        }
        if (v3 + v2 <= v1) {
            return false;
        }
        if (v1 + v3 <= v2) {
            return false;
        }
        return true;
    }

}
