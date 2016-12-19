package com.innomob;


import org.apache.commons.codec.digest.DigestUtils;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Day5P2 {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        String input = "ugkcyxxp";

        int index = 0;
        int found = 0;

        char[] result = new char[8];

        System.out.println();

        while (found < 8) {
            String hash = DigestUtils.md5Hex(input + index);
            if(hash.startsWith("00000")) {
                if(isValid(hash, result)) {
                    found++;
                }
            }
            index++;
        }

        System.out.println(index);
        System.out.println(String.valueOf(result));

    }

    static boolean isValid(String hash, char[] result) {
        int position;
        try {
            position = Integer.parseInt(String.valueOf(hash.charAt(5)));
        } catch (NumberFormatException e) {
            return false;
        }
        if(position >= 8) {
            return false;
        }
        if(result[position] != '\u0000') {
            return false;
        }
        result[position] = hash.charAt(6);
        return true;
    }
}
