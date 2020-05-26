package com.analyser;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


public class SortData {

    public enum Parameter {
        BATTING_AVERAGE , BOWLING_AVERAGE, STRIKE_RATE, BOWLING_STRIKE_RATE, FOURS, SIXES,
        ECONOMY, WICKETS, FIVE_FOUR_WICKETS_STRIKE_RATE, BATTING_RUNS, BEST_ALL_ROUNDER,
        BEST_STRIKE_RATE_MAXIMUM_SIXES_FOURS, GREAT_AVERAGE_BEST_STRIKE_RATE, FIVE_WICKETS, FOUR_WICKETS,
        MAXIMUM_RUN_WITH_BATTING_AVERAGE, BEST_BOWLING_BATTING_AVERAGE, BEST_BOWLING_AVERAGE_STRIKE_RATE;
    }

    static Map<Parameter, Comparator> comparatorMap = new HashMap<>();

    SortData() {    }

    static {

         Comparator<IplSheetDAO> comparatorForBattingAverage = Comparator.comparing(cricket -> cricket.battingAverage);
         comparatorMap.put(Parameter.BATTING_AVERAGE, comparatorForBattingAverage);

         Comparator<IplSheetDAO> comparatorForBowlingAverage = Comparator.comparing(cricket -> cricket.bowlingAverage);
         comparatorMap.put(Parameter.BOWLING_AVERAGE, comparatorForBowlingAverage);

         Comparator<IplSheetDAO> comparatorForStrikeRate = Comparator.comparing(cricket -> cricket.battingStrikeRate);
         comparatorMap.put(Parameter.STRIKE_RATE, comparatorForStrikeRate);

         Comparator<IplSheetDAO> comparatorForBowlingStrikeRate = Comparator.comparing(cricket -> cricket.bowlingStrikeRate);
         comparatorMap.put(Parameter.BOWLING_STRIKE_RATE,comparatorForBowlingStrikeRate);

         Comparator<IplSheetDAO> comparatorForFours = Comparator.comparing(cricket -> cricket.fours);
         comparatorMap.put(Parameter.FOURS, comparatorForFours);

         Comparator<IplSheetDAO> comparatorForSixes = Comparator.comparing(cricket -> cricket.sixes);
         comparatorMap.put(Parameter.SIXES, comparatorForSixes);

         Comparator<IplSheetDAO> comparatorForEconomy = Comparator.comparing(cricket -> cricket.economy);
         comparatorMap.put(Parameter.ECONOMY, comparatorForEconomy);

         Comparator<IplSheetDAO> comparatorForFiveWickets = Comparator.comparing(cricket -> cricket.fiveWickets);
         comparatorMap.put(Parameter.FIVE_WICKETS, comparatorForFiveWickets);

         Comparator<IplSheetDAO> comparatorForFourWickets = Comparator.comparing(cricket -> cricket.fourWickets);
         comparatorMap.put(Parameter.FOUR_WICKETS, comparatorForFourWickets);

         Comparator<IplSheetDAO> comparatorForBattingRun = Comparator.comparing(cricket -> cricket.battingRuns);
         comparatorMap.put(Parameter.BATTING_RUNS,comparatorForBattingRun);

         Comparator<IplSheetDAO> comparatorForWickets = Comparator.comparing(cricket -> cricket.wickets);
         comparatorMap.put(Parameter.WICKETS,comparatorForWickets);

         comparatorMap.put(Parameter.BEST_STRIKE_RATE_MAXIMUM_SIXES_FOURS, comparatorForStrikeRate
                                                                           .thenComparing(comparatorForSixes)
                                                                           .thenComparing(comparatorForFours));
         comparatorMap.put(Parameter.GREAT_AVERAGE_BEST_STRIKE_RATE, comparatorForBattingAverage
                                                                     .thenComparing(comparatorForStrikeRate));
         comparatorMap.put(Parameter.MAXIMUM_RUN_WITH_BATTING_AVERAGE, comparatorForBattingRun
                                                                       .thenComparing(comparatorForBattingAverage));
         comparatorMap.put(Parameter.FIVE_FOUR_WICKETS_STRIKE_RATE, comparatorForFours
                                                                    .thenComparing(comparatorForFiveWickets)
                                                                    .thenComparing(comparatorForStrikeRate));
         comparatorMap.put(Parameter.BEST_BOWLING_AVERAGE_STRIKE_RATE, comparatorForBowlingAverage
                                                                       .thenComparing(comparatorForBowlingStrikeRate));
         comparatorMap.put(Parameter.BEST_BOWLING_BATTING_AVERAGE, comparatorForBowlingAverage
                                                                   .thenComparing(comparatorForBattingAverage));
         comparatorMap.put(Parameter.BEST_ALL_ROUNDER, comparatorForBattingRun
                                                       .thenComparing(comparatorForWickets));
    }

    public static Comparator<IplSheetDAO> getArgument(Parameter parameter) {
        Comparator<IplSheetDAO> getComparator = comparatorMap.get(parameter);
        return getComparator;
    }
}
