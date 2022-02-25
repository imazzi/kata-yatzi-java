package com.game.yatzi;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class Yatzy {

    protected int[] dices;

    public Yatzy(int dice1, int dice2, int dice3, int dice4, int dice5)
    {
        dices = new int[5];
        dices[0] = dice1;
        dices[1] = dice2;
        dices[2] = dice3;
        dices[3] = dice4;
        dices[4] = dice5;
    }

    public static int sumOfAllDices(List<Integer> dices) {
        return dices.stream().collect(Collectors.summingInt(Integer::intValue));
    }

    public static int yatzy(List<Integer> dices) {
        if(dices.stream().distinct().count()== 1){
            return 50;
        }
        return 0;
    }

    public static int ones(List<Integer> dices) {
        return sumValues(dices,1);
    }

    public static int twos(List<Integer> dices) {
        return sumValues(dices,2);
    }
    public static int threes(List<Integer> dices) {
        return sumValues(dices,3);
    }
    public static int fours(List<Integer> dices) {
        return sumValues(dices,4);
    }
    public static int fives(List<Integer> dices) {
        return sumValues(dices,5);
    }
    public static int sixes(List<Integer> dices) {
        return sumValues(dices,6);
    }

    public static int sumValues(List<Integer> dices, int diceValue){
        return Collections.frequency(dices,diceValue)*diceValue;
    }

    public static int scoreOfOnePair(List<Integer> dices) {
        return Collections.max(dices.stream().filter(i -> Collections.frequency(dices, i) >1).distinct().collect(Collectors.toList()))*2;
    }

    public static int scoreOfTwoPair(List<Integer> dices) {
        return nOfAKind(dices,2);
    }

    public static int scoreOfThreeOfAKind(List<Integer> dices) {
        return nOfAKind(dices,3);
    }

    public static int scoreOfFourOfAKind(List<Integer> dices) {
        return nOfAKind(dices,4);
    }

   public static int nOfAKind(List<Integer> dices,int n){
       return dices.stream().filter(i -> Collections.frequency(dices, i) >=n).distinct().mapToInt(Integer::intValue).sum()*n;

   }

    public static int smallStraight(List<Integer> dices) {
        int result = 0;
        if(asList(1,2,3,4,5).equals(dices.stream().sorted().collect(Collectors.toList())))
            result = 15;
        return result;
    }

    public static int largeStraight(List<Integer> dices) {
        int result = 0;
        if(asList(2,3,4,5,6).equals(dices.stream().sorted().collect(Collectors.toList())))
            result = 20;
        return result;
    }

    public static int fullHouse(List<Integer> dices) {
        int result = 0;
        if(dices.stream().filter(i -> Collections.frequency(dices, i) >1).distinct().collect(Collectors.toList()).size() ==2){
            result = dices.stream().mapToInt(Integer::intValue).sum();
        }
       return result;
    }
}



