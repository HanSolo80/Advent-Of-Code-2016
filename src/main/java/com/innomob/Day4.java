package com.innomob;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {

    public static void main(String[] args) throws IOException {
        List<Entry> realRooms = new ArrayList<>();
        int result = 0;
        final List<String> lines = FileUtils.readLines(new File("day4.txt"));
        Pattern pattern = Pattern.compile("([a-z|-]*)-(\\d+)\\[(.*)]");
        for (String line : lines) {
            Matcher m = pattern.matcher(line);
            m.matches();
            Entry e = new Entry(m.group(1), m.group(3), Integer.parseInt(m.group(2)));
            if(e.valid) {
                realRooms.add(e);
                result += e.id;
            }
        }

        System.out.println(result);

        for(Entry e : realRooms) {
            for(char c: e.name.toCharArray()) {
                System.out.print(getDecodedChar(c, e.id));
            }
            System.out.println(" - " + e.id);
        }

    }

    static class Entry {

        String name;
        String checksum;
        int id;
        boolean valid;
        List<CharOrder> charList;

        Entry(String name, String checksum, int id) {
            this.name = name;
            this.checksum = checksum;
            this.id = id;

            this.charList = new ArrayList<>();
            for (char c : name.replace("-", "").toCharArray()) {
                CharOrder co = getCharOrderForCharacter(c, this.charList);
                co.increase();
            }

            Collections.sort(charList);
            Collections.reverse(charList);

            if (charList.size() < 5) {
                this.valid = false;
            } else {
                String charOrderCheck = "";
                for (int i = 0; i < 5; i++) {
                    charOrderCheck += charList.get(i).character;
                }
                valid = charOrderCheck.equals(this.checksum);
            }
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "name='" + name + '\'' +
                    ", checksum='" + checksum + '\'' +
                    ", id=" + id +
                    ", valid=" + valid +
                    '}';
        }

    }

    static class CharOrder implements Comparable {
        char character;
        int count;

        @Override
        public int compareTo(Object o) {
            CharOrder o1 = (CharOrder) o;
            if (o1.count > this.count) {
                return -1;
            }

            if (o1.count < this.count) {
                return 1;
            }

            return new Character(o1.character).compareTo(this.character);
        }

        void increase() {
            this.count++;
        }
    }

    static char getDecodedChar(char enc, int hash) {
        if(enc == '-') {
            return ' ';
        }
        int normalized = (int) enc - 97;
        return (char) ((normalized + hash) % 26 + 97);
    }

    static CharOrder getCharOrderForCharacter(char c, List<CharOrder> charList) {
        for (CharOrder co : charList) {
            if (co.character == c) {
                return co;
            }
        }
        CharOrder newCo = new CharOrder();
        newCo.character = c;
        charList.add(newCo);
        return newCo;
    }

}