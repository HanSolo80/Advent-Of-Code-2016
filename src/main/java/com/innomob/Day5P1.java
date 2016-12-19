package com.innomob;


import org.apache.commons.codec.digest.DigestUtils;

import java.security.NoSuchAlgorithmException;

public class Day5P1 {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        String input = "ugkcyxxp";

        int index = 0;
        int found = 0;

        String result = "";

        while (found < 8) {
            String hash = DigestUtils.md5Hex(input + index);
            if(hash.startsWith("00000")) {
                result += hash.charAt(5);
                found++;
            }
            index++;
        }

        System.out.println(result);

    }
}
