package yatzi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;


class DiceHand implements Iterable<Integer> {
   private final int[] dice;

   public DiceHand(int d1, int d2, int d3, int d4, int d5) {
      this.dice = new int[]{d1, d2, d3, d4, d5};
      if (stream().anyMatch(d -> d <= 0 || d >= 7)) {
         throw new IllegalArgumentException();
      }
   }

   public Map<Integer, Long> getCountMap() {
      return stream().collect(groupingBy(d -> d, counting()));
   }

   public int sumValues(int value) {
      return stream()
          .filter(n -> n == value)
          .mapToInt(Integer::intValue)
          .sum();
   }

   public int die(int index) {
      return dice[index];
   }

   @Override
   public Iterator<Integer> iterator() {
      return stream().iterator();
   }

   public Stream<Integer> stream() {
      return IntStream.of(dice).boxed();
   }

   @Override
   public String toString() {
      return "DiceHand{" +
             "dice=" + Arrays.toString(dice) +
             '}';
   }
}

public class Yatzy {
   private static final Logger log = LoggerFactory.getLogger(Yatzy.class);

   public static int chance(DiceHand diceHand) {
      return diceHand.stream().mapToInt(Integer::intValue).sum();
   }

   public static int yatzy(DiceHand dice) {
//      if (dice.stream().allMatch(n -> n.equals(dice.die(6)))) {
//      if (dice.stream().collect(toSet()).size() == 1) {
      if (dice.stream().distinct().count() == 1) {
         return 50;
      }
      return 0;
   }

   public static int ones(DiceHand diceHand) {
      return diceHand.sumValues(1);
   }

   public static int twos(DiceHand diceHand) {
      return diceHand.sumValues(2);
   }

   public static int threes(DiceHand diceHand) {
      return diceHand.sumValues(3);
   }

   public static int fours(DiceHand diceHand) {
      return diceHand.sumValues(4);
   }

   public static int fives(DiceHand diceHand) {
      return diceHand.sumValues(5);
   }

   public static int sixes(DiceHand diceHand) {
      return diceHand.sumValues(6);
   }

   protected int[] dice;

   public Yatzy(int d1, int d2, int d3, int d4, int _5) {
      dice = new int[5];
      dice[0] = d1;
      dice[1] = d2;
      dice[2] = d3;
      dice[3] = d4;
      dice[4] = _5;
   }


   public static int score_pair(DiceHand diceHand) {
      // TODO try this: Collections.frequency()
      Map<Integer, Long> counts = diceHand.getCountMap();
      OptionalInt maxDie = counts.entrySet().stream()
          .filter(e -> e.getValue() >= 2)
          .mapToInt(Entry::getKey)
          .max();

      return maxDie.orElse(0) * 2;
   }

   public static int two_pair(DiceHand diceHand) {
      Map<Integer, Long> counts = diceHand.getCountMap();

      List<Integer> diceTwoOrMore = counts.entrySet().stream()
          .filter(e -> e.getValue() >= 2)
          .map(Entry::getKey) // at most 2 items
          .collect(Collectors.toList());

      if (diceTwoOrMore.size() != 2) {
         return 0;
      }
      return diceTwoOrMore.stream().mapToInt(Integer::intValue).sum() * 2;

   }

   public static int three_of_a_kind(DiceHand diceHand) {
      return nOfAKind(diceHand, 3);
   }

   public static int four_of_a_kind(DiceHand diceHand) {
      return nOfAKind(diceHand, 4);
   }

   private static int nOfAKind(DiceHand diceHand, int n) {
      for (Entry<Integer, Long> entry : diceHand.getCountMap().entrySet()) {
         if (entry.getValue() >= n) {
            return entry.getKey() * n;
         }
      }
      return 0;
   }

   public static int smallStraight(DiceHand diceHand) {
      List<Integer> sorted = diceHand.stream().sorted().collect(toList());
      if (asList(1, 2, 3, 4, 5).equals(sorted)) {
         return 15;
      }
      return 0;
   }

   public static int largeStraight(DiceHand diceHand) {
      Set<Integer> sorted = diceHand.stream().collect(toSet());
      if (Set.of(2, 3, 4, 5, 6).equals(sorted)) {
         return 20;
      }
      return 0;
   }

   public static int fullHouse(int d1, int d2, int d3, int d4, int d5) {

//      score_pair()
//       three_of_a_kind()


      int[] tallies;
      boolean _2 = false;
      int i;
      int _2_at = 0;
      boolean _3 = false;
      int _3_at = 0;


      tallies = new int[6];
      tallies[d1 - 1] += 1;
      tallies[d2 - 1] += 1;
      tallies[d3 - 1] += 1;
      tallies[d4 - 1] += 1;
      tallies[d5 - 1] += 1;

      for (i = 0; i < 6; i++)
         if (tallies[i] == 2) {
            _2 = true;
            _2_at = i + 1;
         }

      for (i = 0; i < 6; i++)
         if (tallies[i] == 3) {
            _3 = true;
            _3_at = i + 1;
         }

      if (_2 && _3)
         return _2_at * 2 + _3_at * 3;
      else
         return 0;
   }
}



