package com.innomob;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.innomob.Day4.CharOrder;

public class Day6P1 {

    public static void main(String[] args) throws IOException {
        final List<String> lines = FileUtils.readLines(new File("day6.txt"));
        List<CharOrder> charList1 = new ArrayList<>();
        List<CharOrder> charList2 = new ArrayList<>();
        List<CharOrder> charList3 = new ArrayList<>();
        List<CharOrder> charList4 = new ArrayList<>();
        List<CharOrder> charList5 = new ArrayList<>();
        List<CharOrder> charList6 = new ArrayList<>();
        List<CharOrder> charList7 = new ArrayList<>();
        List<CharOrder> charList8 = new ArrayList<>();

        for (String line : lines) {
            Day4.getCharOrderForCharacter(line.charAt(0), charList1).increase();
            Day4.getCharOrderForCharacter(line.charAt(1), charList2).increase();
            Day4.getCharOrderForCharacter(line.charAt(2), charList3).increase();
            Day4.getCharOrderForCharacter(line.charAt(3), charList4).increase();
            Day4.getCharOrderForCharacter(line.charAt(4), charList5).increase();
            Day4.getCharOrderForCharacter(line.charAt(5), charList6).increase();
            Day4.getCharOrderForCharacter(line.charAt(6), charList7).increase();
            Day4.getCharOrderForCharacter(line.charAt(7), charList8).increase();
        }
        Collections.sort(charList1);
        Collections.sort(charList2);
        Collections.sort(charList3);
        Collections.sort(charList4);
        Collections.sort(charList5);
        Collections.sort(charList6);
        Collections.sort(charList7);
        Collections.sort(charList8);

        System.out.print(charList1.get(charList1.size() - 1).character);
        System.out.print(charList2.get(charList2.size() - 1).character);
        System.out.print(charList3.get(charList3.size() - 1).character);
        System.out.print(charList4.get(charList4.size() - 1).character);
        System.out.print(charList5.get(charList5.size() - 1).character);
        System.out.print(charList6.get(charList6.size() - 1).character);
        System.out.print(charList7.get(charList7.size() - 1).character);
        System.out.print(charList8.get(charList8.size() - 1).character);

        System.out.println();

        System.out.print(charList1.get(0).character);
        System.out.print(charList2.get(0).character);
        System.out.print(charList3.get(0).character);
        System.out.print(charList4.get(0).character);
        System.out.print(charList5.get(0).character);
        System.out.print(charList6.get(0).character);
        System.out.print(charList7.get(0).character);
        System.out.print(charList8.get(0).character);


    }

}
