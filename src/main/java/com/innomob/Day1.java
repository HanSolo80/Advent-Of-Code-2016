package com.innomob;

import java.util.ArrayList;
import java.util.List;

public class Day1 {

    static int[] coord;
    static int direction = 0;
    static List<String> coords = new ArrayList<String>();
    static boolean stop = false;


    public static void main(String[] args) {
        coord = new int[2];
        coord[0] = 0;
        coord[1] = 0;

        String in = "L5, R1, R3, L4, R3, R1, L3, L2, R3, L5, L1, L2, R5, L1, R5, R1, L4, R1, R3, L4, L1, R2, R5, R3, " +
                "R1, R1, L1, R1, L1, L2, L1, R2, L5, L188, L4, R1, R4, L3, R47, R1, L1, R77, R5, L2, R1, L2, R4, L5, L1, R3, R187, L4, L3, L3, R2, L3, L5, L4, L4, R1, R5, L4, L3, L3, L3, L2, L5, R1, L2, R5, L3, L4, R4, L5, R3, R4, L2, L1, L4, R1, L3, R1, R3, L2, R1, R4, R5, L3, R5, R3, L3, R4, L2, L5, L1, L1, R3, R1, L4, R3, R3, L2, R5, R4, R1, R3, L4, R3, R3, L2, L4, L5, R1, L4, L5, R4, L2, L1, L3, L3, L5, R3, L4, L3, R5, R4, R2, L4, R2, R3, L3, R4, L1, L3, R2, R1, R5, L4, L5, L5, R4, L5, L2, L4, R4, R4, R1, L3, L2, L4, R3";

//        String in = "R8, R4, R4, R8";

        for(String value : in.split(", ")) {
            if (stop) {
                break;
            }
            move(value);
        }

    }

    public static void move(String input) {
        direction = input.charAt(0) == 'R' ? (direction + 1) % 4 : (direction - 1) % 4;
        if (direction < 0) {
            direction = 3;
        }
        int value = Integer.parseInt(input.substring(1));

        switch (direction) {
            case 0:
                for(int i = 0; i < value; i++) {
                    coord[1] ++;
                    stop = isInList(coord[0] + "," + coord[1]);
                }
                break;
            case 1:
                for(int i = 0; i < value; i++) {
                    coord[0] ++;
                    stop = isInList(coord[0] + "," + coord[1]);
                }
                break;
            case 2:
                for(int i = 0; i < value; i++) {
                    coord[1] --;
                    stop = isInList(coord[0] + "," + coord[1]);
                }
                break;
            case 3:
                for(int i = 0; i < value; i++) {
                    coord[0] --;
                    stop = isInList(coord[0] + "," + coord[1]);
                }
                break;
        }
    }

    public static boolean isInList(String in) {
        if(coords.contains(in)) {
            System.out.println(in);
            return true;
        }
        coords.add(in);
        return false;
    }


}
