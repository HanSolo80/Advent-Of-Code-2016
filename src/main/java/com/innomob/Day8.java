package com.innomob;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day8 {

    static Pattern rect = Pattern.compile("rect\\s(\\d+)x(\\d+)");
    static Pattern rotRow = Pattern.compile("rotate\\srow\\sy=(\\d+)\\sby\\s(\\d+)");
    static Pattern rotCol = Pattern.compile("rotate\\scolumn\\sx=(\\d+)\\sby\\s(\\d+)");

    public static void main(String[] args) throws IOException {

        int[][] coords = new int[6][50];

        final List<String> lines = FileUtils.readLines(new File("day8.txt"));

        for (String line : lines) {
            Matcher rectMatcher = rect.matcher(line);
            Matcher rowMatcher = rotRow.matcher(line);
            Matcher colMatcher = rotCol.matcher(line);
            if(rectMatcher.matches()) {
                rect(coords, Integer.parseInt(rectMatcher.group(1)), Integer.parseInt(rectMatcher.group(2)));
            } else if(rowMatcher.matches()) {
                rotateRow(coords, Integer.parseInt(rowMatcher.group(1)), Integer.parseInt(rowMatcher.group(2)));
            } else if(colMatcher.matches()) {
                rotateColumn(coords, Integer.parseInt(colMatcher.group(1)), Integer.parseInt(colMatcher.group(2)));
            }
        }
        printCoords(coords);
    }

    static void printCoords(int[][] coords) {
        int result = 0;
        for (int i = 0; i < coords.length; i++) {
            for (int j = 0; j < coords[i].length; j++) {
                if (coords[i][j] == 0) {
                    System.out.print('.');
                } else {
                    System.out.print('#');
                    result++;
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println(result);
    }

    static void rect(int[][] coords, int x, int y) {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                coords[i][j] = 1;
            }
        }
    }

    static void rotateRow(int[][] coords, int row, int steps) {
        int[] target = new int[50];
        for (int i = 0; i < 50; i++) {
            target[(i + steps) % 50] = coords[row][i];
        }
        coords[row] = target;
    }

    static void rotateColumn(int[][] coords, int column, int steps) {
        int[] target = new int[6];
        for (int i = 0; i < 6; i++) {
            target[(i + steps) % 6] = coords[i][column];
        }
        for (int i = 0; i < 6; i++) {
            coords[i][column] = target[i];
        }
    }
}
