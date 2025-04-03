package org.codingdojo;

import org.codingdojo.yatzy1.Yatzy1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Yatzy1Test {

    @Test
    public void testChance() {
        assertEquals(15, Yatzy1.chance(2, 3, 4, 5, 1));
        assertEquals(16, Yatzy1.chance(3, 3, 4, 5, 1));
    }

    @Test
    public void testYatzy() {
        assertEquals(50, Yatzy1.yatzy(4, 4, 4, 4, 4));
        assertEquals(50, Yatzy1.yatzy(6, 6, 6, 6, 6));
        assertEquals(0, Yatzy1.yatzy(6, 6, 6, 6, 3));
    }

    @Test
    public void testOnes() {
        assertEquals(1, Yatzy1.ones(1, 2, 3, 4, 5));
        assertEquals(2, Yatzy1.ones(1, 2, 1, 4, 5));
        assertEquals(0, Yatzy1.ones(6, 2, 2, 4, 5));
        assertEquals(4, Yatzy1.ones(1, 2, 1, 1, 1));
    }

    @Test
    public void testTwos() {
        assertEquals(4, Yatzy1.twos(1, 2, 3, 2, 6));
        assertEquals(10, Yatzy1.twos(2, 2, 2, 2, 2));
    }

    @Test
    public void testThrees() {
        assertEquals(6, Yatzy1.threes(1, 2, 3, 2, 3));
        assertEquals(12, Yatzy1.threes(2, 3, 3, 3, 3));
    }

    @Test
    public void testFours() {
        assertEquals(12, new Yatzy1(4, 4, 4, 5, 5).fours());
        assertEquals(8, new Yatzy1(4, 4, 5, 5, 5).fours());
        assertEquals(4, new Yatzy1(4, 5, 5, 5, 5).fours());
    }

    @Test
    public void testFives() {
        assertEquals(10, new Yatzy1(4, 4, 4, 5, 5).fives());
        assertEquals(15, new Yatzy1(4, 4, 5, 5, 5).fives());
        assertEquals(20, new Yatzy1(4, 5, 5, 5, 5).fives());
    }

    @Test
    public void testSixes() {
        assertEquals(0, new Yatzy1(4, 4, 4, 5, 5).sixes());
        assertEquals(6, new Yatzy1(4, 4, 6, 5, 5).sixes());
        assertEquals(18, new Yatzy1(6, 5, 6, 6, 5).sixes());
    }

    @Test
    public void testOnePair() {
        assertEquals(6, new Yatzy1(3, 4, 3, 5, 6).scorePair());
        assertEquals(10, new Yatzy1(5, 3, 3, 3, 5).scorePair());
        assertEquals(12, new Yatzy1(5, 3, 6, 6, 5).scorePair());
    }

    @Test
    public void testTwoPair() {
        assertEquals(16, new Yatzy1(3, 3, 5, 4, 5).twoPair());
        assertEquals(16, new Yatzy1(3, 3, 5, 5, 5).twoPair());
    }

    @Test
    public void testThreeOfAKind() {
        assertEquals(9, new Yatzy1(3, 3, 3, 4, 5).threeOfAKind());
        assertEquals(15, new Yatzy1(5, 3, 5, 4, 5).threeOfAKind());
        assertEquals(9, new Yatzy1(3, 3, 3, 3, 5).threeOfAKind());
    }

    @Test
    public void testFourOfAKind() {
        assertEquals(12, new Yatzy1(3, 3, 3, 3, 5).fourOfAKind());
        assertEquals(20, new Yatzy1(5, 5, 5, 4, 5).fourOfAKind());
    }

    @Test
    public void testSmallStraight() {
        assertEquals(15, new Yatzy1(1, 2, 3, 4, 5).smallStraight());
        assertEquals(15, new Yatzy1(2, 3, 4, 5, 1).smallStraight());
        assertEquals(0, new Yatzy1(1, 2, 2, 4, 5).smallStraight());
    }

    @Test
    public void testLargeStraight() {
        assertEquals(20, new Yatzy1(6, 2, 3, 4, 5).largeStraight());
        assertEquals(20, new Yatzy1(2, 3, 4, 5, 6).largeStraight());
        assertEquals(0, new Yatzy1(1, 2, 2, 4, 5).largeStraight());
    }

    @Test
    public void testFullHouse() {
        assertEquals(18, new Yatzy1(6, 2, 2, 2, 6).fullHouse());
        assertEquals(0, new Yatzy1(2, 3, 4, 5, 6).fullHouse());
    }
}
