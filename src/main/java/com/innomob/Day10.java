package com.innomob;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day10 {

    static HashMap<Integer,Bot> bots;
    static HashMap<Integer,Bot> outputs;

    static class Bot implements Comparable {
        int id;

        boolean carriedHigh;
        boolean carriedLow;

        int highValue = -1;
        int lowValue = -1;

        Bot highBot;
        Bot lowBot;

        public Bot(int id) {
            this.id = id;
        }

        public void giveChip(int value) {
            if(!carriedHigh) {
                highValue = value;
                carriedHigh = true;
            } else {
                if(value > highValue) {
                    lowValue = highValue;
                    highValue = value;
                } else {
                    lowValue = value;
                }
                carriedLow = true;
            }
            this.processChips();
        }

        public void assignBots(Bot highBot, Bot lowBot) {
            this.highBot = highBot;
            this.lowBot = lowBot;
        }

        public void processChips() {
            if(this.isFull()) {
                if(highBot instanceof Output) {
                    outputs.get(highBot.id).giveChip(highValue);
//                    System.out.println("Bot: " + this.id + " gives high value chip: " + highValue + " to Output: " + highBot.id);
                } else {
                    bots.get(highBot.id).giveChip(highValue);
//                    System.out.println("Bot: " + this.id + " gives high value chip: " + highValue + " to Bot: " +
//                            highBot.id);
                }
                this.carriedHigh = false;
                if(lowBot instanceof Output) {
                    outputs.get(lowBot.id).giveChip(lowValue);
//                    System.out.println("Bot: " + this.id + " gives low value chip: " + lowValue + " to Output: " +
//                            lowBot.id);
                } else {
                    bots.get(lowBot.id).giveChip(lowValue);
//                    System.out.println("Bot: " + this.id + " gives low value chip: " + lowValue + " to Bot: " +
//                            lowBot.id);
                }
                this.carriedLow = false;
            }
        }

        private boolean isFull() {
            return highBot!= null && lowBot != null && highValue != -1  && lowValue != -1 && carriedHigh && carriedLow;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Bot bot = (Bot) o;

            if (id != bot.id) return false;
            if (carriedHigh != bot.carriedHigh) return false;
            if (carriedLow != bot.carriedLow) return false;
            if (highValue != bot.highValue) return false;
            if (lowValue != bot.lowValue) return false;
            if (highBot != null ? !highBot.equals(bot.highBot) : bot.highBot != null) return false;
            return lowBot != null ? lowBot.equals(bot.lowBot) : bot.lowBot == null;
        }

        @Override
        public int hashCode() {
            int result = id;
            result = 31 * result + (carriedHigh ? 1 : 0);
            result = 31 * result + (carriedLow ? 1 : 0);
            result = 31 * result + highValue;
            result = 31 * result + lowValue;
            result = 31 * result + (highBot != null ? highBot.hashCode() : 0);
            result = 31 * result + (lowBot != null ? lowBot.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Bot{" +
                    "id=" + id +
                    ", carriedHigh=" + carriedHigh +
                    ", carriedLow=" + carriedLow +
                    ", highValue=" + highValue +
                    ", lowValue=" + lowValue +
                    ", highBot=" + highBot +
                    ", lowBot=" + lowBot +
                    '}';
        }


        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }

    static class Output extends Bot {

        public List<Integer> chips;

        public Output(int id) {
            super(id);
            this.chips = new ArrayList<>();
        }

        @Override
        public void giveChip(int value) {
//            System.out.println("Output: " + this.id + " stores chip: " + value);
            this.chips.add(value);
        }
    }

    public static void main(String[] args) throws IOException {

        bots = new HashMap<>();
        outputs = new HashMap<>();
        List<String> lines = FileUtils.readLines(new File("day10.txt"));

        Pattern inputPattern = Pattern.compile("value\\s(\\d+)\\sgoes\\sto\\sbot\\s(\\d+)");
        Pattern givePattern = Pattern.compile("bot\\s(\\d+)\\sgives\\slow\\sto\\s(\\w+)\\s(\\d+)\\sand\\shigh\\sto\\s" +
                "(\\w+)\\s(\\d+)");

        for (String line: lines) {
            Matcher inputMatcher = inputPattern.matcher(line);
            Matcher giveMatcher = givePattern.matcher(line);
            if(inputMatcher.find()) {
                int botId = Integer.parseInt(inputMatcher.group(2));
                int value = Integer.parseInt(inputMatcher.group(1));
                Bot bot = getBotById(botId);
                bot.giveChip(value);
            }
            if (giveMatcher.find()) {
                int botId = Integer.parseInt(giveMatcher.group(1));
                int lowBotId = Integer.parseInt(giveMatcher.group(3));
                int highBotId = Integer.parseInt(giveMatcher.group(5));
                Bot bot = getBotById(botId);
                Bot highBot;
                Bot lowBot;
                if(giveMatcher.group(2).equals("output")) {
                    lowBot = getOutputById(lowBotId);
                } else {
                    lowBot = getBotById(lowBotId);
                }
                if(giveMatcher.group(4).equals("output")) {
                    highBot = getOutputById(highBotId);
                } else {
                    highBot = getBotById(highBotId);
                }
                bot.assignBots(highBot, lowBot);
            }
        }

        while(!testEnd()) {
            for(Bot bot: bots.values()) {
                bot.processChips();
            }
        }

        System.out.println(bots.values().stream().filter(x -> x.highValue == 61 && x.lowValue == 17).findFirst().get
                ().id);
        System.out.println(((Output)outputs.get(0)).chips.get(0) * ((Output)outputs.get(1)).chips.get(0) *((Output)
                outputs.get(2)).chips.get(0));
    }

    static void printBots() {
        for(Bot bot: bots.values()) {
            System.out.println("Bot: " + bot.id + "carried high: " + bot.carriedHigh + "carried low: " + bot
                    .carriedLow);
        }
    }

    static boolean testEnd() {
        boolean result = true;

        for(Bot bot: bots.values()) {
            if(bot.carriedHigh || bot.carriedLow) {
                return false;
            }
        }

        return result;
    }

    static Bot getBotById(int botId) {
        Bot result;
        if(bots.containsKey(botId)) {
            result = bots.get(botId);
        } else {
            result = new Bot(botId);
            bots.put(botId, result);
        }
        return result;
    }

    static Bot getOutputById(int botId) {
        Bot result;
        if(outputs.containsKey(botId)) {
            result = outputs.get(botId);
        } else {
            result = new Output(botId);
            outputs.put(botId, result);
        }
        return result;
    }
}
