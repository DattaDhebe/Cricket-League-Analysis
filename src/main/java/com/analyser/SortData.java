package com.analyser;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortData {

    static Map<Parameter, Comparator> sortParameterComparator = new HashMap<>();

    public enum Parameter {
        BATTING_AVERAGE, BOWLING_AVERAGE, STRIKE_RATE, BOWLING_STRIKE_RATE, FOURS, SIXES,
        ECONOMY, WICKETS, FIVE_FOUR_WICKETS_STRIKE_RATE, BEST_ALL_ROUNDER,
        BEST_STRIKE_RATE_MAC_SIXES_FOURS, GREAT_AVERAGE_BEST_STRIKE_RATE, FIVE_WICKETS, FOUR_WICKETS,
        MAX_RUN_WITH_BATTING_AVERAGE, BEST_BOWLING_BATTING_AVERAGE, BEST_BOWLING_AVERAGE_STRIKE_RATE;
    }

    SortData() {    }

    public static Comparator getArgument(SortData.Parameter parameter) {

        Comparator<IplSheetDAO> comparatorForBattingAverage = Comparator.comparing(cricket -> cricket.battingAverage);
        Comparator<IplSheetDAO> comparatorForBowlingAverage = Comparator.comparing(cricket -> cricket.bowlingAverage);
        Comparator<IplSheetDAO> comparatorForStrikeRate = Comparator.comparing(cricket -> cricket.battingStrikeRate);
        Comparator<IplSheetDAO> comparatorForBowlingStrikeRate = Comparator.comparing(cricket -> cricket.bowlingStrikeRate);
        Comparator<IplSheetDAO> comparatorForFours = Comparator.comparing(cricket -> cricket.fours);
        Comparator<IplSheetDAO> comparatorForSixes = Comparator.comparing(cricket -> cricket.sixes);
        Comparator<IplSheetDAO> comparatorForEconomy = Comparator.comparing(cricket -> cricket.economy);
        Comparator<IplSheetDAO> comparatorForFiveWickets = Comparator.comparing(cricket -> cricket.fiveWickets);
        Comparator<IplSheetDAO> comparatorForFourWickets = Comparator.comparing(cricket -> cricket.fourWickets);
        Comparator<IplSheetDAO> comparatorForBattingRun = Comparator.comparing(cricket -> cricket.battingRuns);
        Comparator<IplSheetDAO> comparatorForWickets = Comparator.comparing(cricket -> cricket.wickets);

        sortParameterComparator.put(Parameter.BATTING_AVERAGE, comparatorForBattingAverage);
        sortParameterComparator.put(Parameter.BOWLING_AVERAGE, comparatorForBowlingAverage);
        sortParameterComparator.put(Parameter.STRIKE_RATE, comparatorForStrikeRate);
        sortParameterComparator.put(Parameter.BOWLING_STRIKE_RATE,comparatorForBowlingStrikeRate);
        sortParameterComparator.put(Parameter.FOURS, comparatorForFours);
        sortParameterComparator.put(Parameter.SIXES, comparatorForSixes);
        sortParameterComparator.put(Parameter.ECONOMY, comparatorForEconomy);
        sortParameterComparator.put(Parameter.WICKETS,comparatorForWickets);
        sortParameterComparator.put(Parameter.FIVE_WICKETS, comparatorForFiveWickets);
        sortParameterComparator.put(Parameter.FOUR_WICKETS, comparatorForFourWickets);

        sortParameterComparator.put(Parameter.BEST_STRIKE_RATE_MAC_SIXES_FOURS, comparatorForStrikeRate
                                                                                .thenComparing(comparatorForSixes)
                                                                                .thenComparing(comparatorForFours));
        sortParameterComparator.put(Parameter.GREAT_AVERAGE_BEST_STRIKE_RATE, comparatorForBattingAverage
                                                                              .thenComparing(comparatorForStrikeRate));
        sortParameterComparator.put(Parameter.MAX_RUN_WITH_BATTING_AVERAGE, comparatorForBattingRun
                                                                            .thenComparing(comparatorForBattingAverage));
        sortParameterComparator.put(Parameter.FIVE_FOUR_WICKETS_STRIKE_RATE, comparatorForFours
                                                                             .thenComparing(comparatorForFiveWickets)
                                                                             .thenComparing(comparatorForStrikeRate));
        sortParameterComparator.put(Parameter.BEST_BOWLING_AVERAGE_STRIKE_RATE, comparatorForBowlingAverage
                                                                                .thenComparing(comparatorForBowlingStrikeRate));
        sortParameterComparator.put(Parameter.BEST_BOWLING_BATTING_AVERAGE, comparatorForBowlingAverage
                                                                            .thenComparing(comparatorForBattingAverage));
        sortParameterComparator.put(Parameter.BEST_ALL_ROUNDER, comparatorForBattingRun
                                                                .thenComparing(comparatorForWickets));

        Comparator<IplSheetDAO> comparator = sortParameterComparator.get(parameter);
        return comparator;
    }
}
