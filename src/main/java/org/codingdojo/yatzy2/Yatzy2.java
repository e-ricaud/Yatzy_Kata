package org.codingdojo.yatzy2;

import org.codingdojo.YatzyCalculator;
import org.codingdojo.YatzyCategory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Yatzy2 implements YatzyCalculator {
    private static final List<Integer> SMALL_STRAIGHT = Arrays.asList(1, 2, 3, 4, 5);
    private static final List<Integer> LARGE_STRAIGHT = Arrays.asList(2, 3, 4, 5, 6);

    private static final Map<YatzyCategory, Function<Map<Integer, Long>, Integer>> YATZY_RULES = Map.ofEntries(
        Map.entry(YatzyCategory.CHANCE, freq -> freq.keySet().stream().mapToInt(i -> i * freq.get(i).intValue()).sum()),
        Map.entry(YatzyCategory.YATZY, freq -> freq.containsValue(5L) ? 50 : 0),
        Map.entry(YatzyCategory.ONES, freq -> freq.getOrDefault(1, 0L).intValue()),
        Map.entry(YatzyCategory.TWOS, freq -> freq.getOrDefault(2, 0L).intValue() * 2),
        Map.entry(YatzyCategory.THREES, freq -> freq.getOrDefault(3, 0L).intValue() * 3),
        Map.entry(YatzyCategory.FOURS, freq -> freq.getOrDefault(4, 0L).intValue() * 4),
        Map.entry(YatzyCategory.FIVES, freq -> freq.getOrDefault(5, 0L).intValue() * 5),
        Map.entry(YatzyCategory.SIXES, freq -> freq.getOrDefault(6, 0L).intValue() * 6),
        Map.entry(YatzyCategory.PAIR, freq -> scoreOfFrequencyDice(freq, 2)),
        Map.entry(YatzyCategory.THREE_OF_A_KIND, freq -> scoreOfFrequencyDice(freq, 3)),
        Map.entry(YatzyCategory.FOUR_OF_A_KIND, freq -> scoreOfFrequencyDice(freq, 4)),
        Map.entry(YatzyCategory.SMALL_STRAIGHT, freq -> isStraight(freq, SMALL_STRAIGHT) ? 15 : 0),
        Map.entry(YatzyCategory.LARGE_STRAIGHT, freq -> isStraight(freq, LARGE_STRAIGHT) ? 20 : 0),
        Map.entry(YatzyCategory.TWO_PAIRS, freq -> scoreTwoPairs(freq)),
        Map.entry(YatzyCategory.FULL_HOUSE, freq -> scoreFullHouse(freq))
    );

    @Override
    public List<String> validCategories() {
        return Arrays.stream(YatzyCategory.values()).map(Enum::toString).collect(Collectors.toList());
    }

    @Override
    public int score(List<Integer> dice, String categoryName) {
        YatzyCategory category = YatzyCategory.valueOf(categoryName);
        Map<Integer, Long> diceFrequencies = dice.stream()
            .collect(Collectors.groupingBy(die -> die, Collectors.counting()));

        return YATZY_RULES.getOrDefault(category, d -> 0).apply(diceFrequencies);
    }

    private static int scoreOfFrequencyDice(Map<Integer, Long> frequencies, int count) {
        return frequencies.entrySet().stream()
            .filter(entry -> entry.getValue() >= count)
            .mapToInt(entry -> entry.getKey() * count)
            .max()
            .orElse(0);
    }

    private static boolean isStraight(Map<Integer, Long> frequencies, List<Integer> expected) {
        return expected.stream().allMatch(frequencies::containsKey);
    }

    private static int scoreTwoPairs(Map<Integer, Long> frequencies) {
        List<Integer> pairs = frequencies.entrySet().stream()
            .filter(entry -> entry.getValue() >= 2)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
        return pairs.size() == 2 ? pairs.stream().mapToInt(i -> i * 2).sum() : 0;
    }

    private static int scoreFullHouse(Map<Integer, Long> frequencies) {
        boolean hasThree = frequencies.containsValue(3L);
        boolean hasTwo = frequencies.containsValue(2L);
        return (hasThree && hasTwo) ? frequencies.keySet().stream().mapToInt(i -> i * frequencies.get(i).intValue()).sum() : 0;
    }

}

