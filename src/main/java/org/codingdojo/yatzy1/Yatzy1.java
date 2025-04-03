package org.codingdojo.yatzy1;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Yatzy1 {

    private final int[] dice;

    public Yatzy1(int... dice) {
        this.dice = Arrays.copyOf(dice, dice.length);
    }

    public static int chance(int... dice) {
        return Arrays.stream(dice).sum();
    }

    public static int yatzy(int... dice) {
        return Arrays.stream(dice).distinct().count() == 1 ? 50 : 0;
    }


    public static int ones(int... dice) {
        return countSimpleDice(dice, 1);
    }

    public static int twos(int... dice) {
        return countSimpleDice(dice, 2);
    }

    public static int threes(int... dice) {
        return countSimpleDice(dice, 3);
    }

    public int fours() {
        return countSimpleDice(dice, 4);
    }

    public int fives() {
        return countSimpleDice(dice, 5);
    }

    public int sixes() {
        return countSimpleDice(dice, 6);
    }

    public int scorePair() {
        return getCounts(dice).entrySet().stream()
            .filter(entry -> entry.getValue() >= 2)
            .mapToInt(entry -> entry.getKey() * 2)
            .max()
            .orElse(0);
    }

    public int twoPair() {
        int[] pairs = getCounts(dice).entrySet().stream()
            .filter(entry -> entry.getValue() >= 2)
            .mapToInt(Map.Entry::getKey)
            .sorted()
            .toArray();

        return pairs.length >= 2 ? (pairs[pairs.length - 1] + pairs[pairs.length - 2]) * 2 : 0;
    }

    public int threeOfAKind() {
        return getCounts(dice).entrySet().stream()
            .filter(entry -> entry.getValue() >= 3)
            .mapToInt(entry -> entry.getKey() * 3)
            .findFirst()
            .orElse(0);
    }

    public int fourOfAKind() {
        return getCounts(dice).entrySet().stream()
            .filter(entry -> entry.getValue() >= 4)
            .mapToInt(entry -> entry.getKey() * 4)
            .findFirst()
            .orElse(0);
    }

    public int smallStraight() {
        return Arrays.equals(Arrays.stream(dice).sorted().toArray(), new int[]{1, 2, 3, 4, 5}) ? 15 : 0;
    }

    public int largeStraight() {
        return Arrays.equals(Arrays.stream(dice).sorted().toArray(), new int[]{2, 3, 4, 5, 6}) ? 20 : 0;
    }

    public int fullHouse() {
        Map<Integer, Long> counts = getCounts(dice);
        boolean hasThree = counts.containsValue(3L);
        boolean hasTwo = counts.containsValue(2L);

        return hasThree && hasTwo ? Arrays.stream(dice).sum() : 0;
    }

    private static int countSimpleDice(int[] dice, int number) {
        return Arrays.stream(dice).filter(d -> d == number).sum();
    }

    private static Map<Integer, Long> getCounts(int... dice) {
        return Arrays.stream(dice)
            .boxed()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

}



