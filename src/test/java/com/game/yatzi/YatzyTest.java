package com.game.yatzi;

import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

public class YatzyTest {

    @Test
    public void should_return_sumOfAllDices() {
        assertEquals(15, Yatzy.sumOfAllDices(List.of(2,3,4,5,1)));
        assertEquals(16, Yatzy.sumOfAllDices(List.of(3,3,4,5,1)));
    }

    @Test public void should_return_yatzy_scores_50() {
        assertEquals(50, Yatzy.yatzy(List.of(6,6,6,6,6)));
        assertEquals(0, Yatzy.yatzy(List.of(6,6,6,6,3)));
    }

    @Test public void should_return_sumOfOnes() {
        assertEquals(2, Yatzy.ones(List.of(1,2,1,4,5)));
        assertEquals(0, Yatzy.ones(List.of(6,2,2,4,5)));
    }

    @Test
    public void should_return_sumOfTwos() {
        assertEquals(4, Yatzy.twos (List.of(1,2,3,2,6)));
        assertEquals(10, Yatzy.twos(List.of(2,2,2,2,2)));
    }

    @Test
    public void should_return_sumOfThrees() {
        assertEquals(6, Yatzy.threes(List.of(1,2,3,2,3)));
        assertEquals(12, Yatzy.threes(List.of(2,3,3,3,3)));
    }

    @Test
    public void should_return_sumOfFours() {
        assertEquals(12, Yatzy.fours(List.of(4,4,4,5,5)));
        assertEquals(8, Yatzy.fours(List.of(4,4,5,5,5)));
    }

    @Test
    public void should_return_sumOfFives() {
        assertEquals(10,Yatzy.fives(List.of(4,4,4,5,5)));
        assertEquals(15,Yatzy.fives(List.of(4,4,5,5,5)));
    }

    @Test
    public void should_return_sumOfSixes() {
        assertEquals(0, Yatzy.sixes(List.of(4,4,4,5,5)));
        assertEquals(6,Yatzy.sixes(List.of(4,4,6,5,5)));
    }

    @Test
    public void should_return_scoreOfOnePair() {
        assertEquals(6, Yatzy.scoreOfOnePair(List.of(3,4,3,5,6)));
        assertEquals(10, Yatzy.scoreOfOnePair(List.of(5,3,3,3,5)));
        assertEquals(12, Yatzy.scoreOfOnePair(List.of(5,3,6,6,5)));
    }

    @Test
    public void should_return_scoreOfTwoPair() {
        assertEquals(16, Yatzy.scoreOfTwoPair(List.of(3,3,5,4,5)));
        assertEquals(16, Yatzy.scoreOfTwoPair(List.of(3,3,5,5,5)));
    }

    @Test
    public void should_return_scoreOfThreeOfAKind() {
        assertEquals(9, Yatzy.scoreOfThreeOfAKind(List.of(3,3,3,4,5)));
        assertEquals(15, Yatzy.scoreOfThreeOfAKind(List.of(5,3,5,4,5)));
        assertEquals(9, Yatzy.scoreOfThreeOfAKind(List.of(3,3,3,3,5)));
    }

    @Test
    public void should_return_scoreOfFourOfAKind() {
        assertEquals(12, Yatzy.scoreOfFourOfAKind(List.of(3,3,3,3,5)));
        assertEquals(20, Yatzy.scoreOfFourOfAKind(List.of(5,5,5,4,5)));
    }

    @Test
    public void smallStraight() {
        assertEquals(15, Yatzy.smallStraight(List.of(1,2,3,4,5)));
        assertEquals(15, Yatzy.smallStraight(List.of(2,3,4,5,1)));
        assertEquals(0, Yatzy.smallStraight(List.of(1,2,2,4,5)));
    }

    @Test
    public void largeStraight() {
        assertEquals(20, Yatzy.largeStraight(List.of(6,2,3,4,5)));
        assertEquals(20, Yatzy.largeStraight(List.of(2,3,4,5,6)));
        assertEquals(0, Yatzy.largeStraight(List.of(1,2,2,4,5)));
    }

    @Test
    public void fullHouse() {
        assertEquals(18, Yatzy.fullHouse(List.of(6,2,2,2,6)));
        assertEquals(0, Yatzy.fullHouse(List.of(2,3,4,5,6)));
    }
}
