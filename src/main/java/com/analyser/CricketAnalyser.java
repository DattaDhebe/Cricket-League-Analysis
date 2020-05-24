package com.analyser;

import com.google.gson.Gson;
import java.util.*;
import java.util.stream.Collectors;


public class CricketAnalyser {

    public enum Cricket { BATTING, BOWLING };

    Map<String, IplSheetDAO> iplRunSheetMap;

    public CricketAnalyser() {    }

    public int loadIplData(Cricket cricket, String... csvFilePath) throws CricketAnalyserException {
        iplRunSheetMap = new IplFileLoader().loadCricketData(cricket, csvFilePath);
        return this.iplRunSheetMap.size();
    }

    public String loadSortingOnBattingAverage()  {
        Comparator<IplSheetDAO> compareByAverage = Comparator.comparing(cricket -> cricket.battingAverage);
        return sort(compareByAverage);
    }

    public String loadSortingOnMaxStrikeRate()  {
        Comparator<IplSheetDAO> compareByStrikeRate = Comparator.comparing(cricket -> cricket.strikeRate);
        return sort(compareByStrikeRate);
    }

    public String getSixesWiseSortedData()  {
        Comparator<IplSheetDAO> compareBySixes = Comparator.comparing(cricket -> cricket.sixes);
        return sort(compareBySixes);
    }

    public String getFoursWiseSortedData()  {
        Comparator<IplSheetDAO> compareByFours = Comparator.comparing(cricket -> cricket.fours);
        return sort(compareByFours);
    }

    public String getStrikeRateWithSixesAndFoursWiseSortedData() {
        Comparator<IplSheetDAO> compareByStrikeRate = Comparator.comparing(cricket -> cricket.strikeRate);
        Comparator<IplSheetDAO> compareBySixes = compareByStrikeRate.thenComparing(cricket -> cricket.sixes);
        Comparator<IplSheetDAO> compareByFours = compareBySixes.thenComparing(cricket -> cricket.fours);
        return sort(compareByFours);
    }

    public String getGreatAverageWithBestStrikingRateWiseSortedData() {
        Comparator<IplSheetDAO> compareByAverage = Comparator.comparing(cricket -> cricket.battingAverage);
        Comparator<IplSheetDAO> compareByStrikeRate = compareByAverage
                                                         .thenComparing(cricket -> cricket.strikeRate);
        return sort(compareByStrikeRate);
    }

    public String getMaximumRunWithBestAverageWiseSortedData() {
        Comparator<IplSheetDAO> compareByMaximumRuns = Comparator.comparing(cricket -> cricket.battingRuns);
        Comparator<IplSheetDAO> compareByBestAverage = compareByMaximumRuns
                                                          .thenComparing(cricket -> cricket.battingAverage);
        return sort(compareByBestAverage);
    }

    public String loadSortingDataOnBowlingAverage()  {
        Comparator<IplSheetDAO> compareByAverage = Comparator.comparing(cricket -> cricket.bowlingAverage);
        return sort(compareByAverage);
    }

    public String loadSortingDataOnBowlingStrikingRate()  {
        Comparator<IplSheetDAO> compareByStrikingRate = Comparator.comparing(cricket -> cricket.strikeRate);
        return sort(compareByStrikingRate);
    }

    public String loadSortingDataOnBestEconomyRate()  {
        Comparator<IplSheetDAO> compareByEconomyRate = Comparator.comparing(cricket -> cricket.economy);
        return sort(compareByEconomyRate);
    }

    public String getStrikeRateWithFiveWicketsAndFourWicketsDataWiseSorted() {
        Comparator<IplSheetDAO> compareByFiveWickets = Comparator.comparing(cricket -> cricket.fiveWickets);
        Comparator<IplSheetDAO> compareByFourWickets = compareByFiveWickets
                                                          .thenComparing(cricket -> cricket.fourWickets);
        return sort(compareByFourWickets);
    }

    public String getGreatBowlingAverageWithBestStrikeRateDataWiseSorted() {
        Comparator<IplSheetDAO> compareByBestAverage = Comparator.comparing(cricket -> cricket.bowlingAverage);
        Comparator<IplSheetDAO> compareByBestStrikeRate = compareByBestAverage
                                                          .thenComparing(cricket -> cricket.strikeRate);
        return sort(compareByBestStrikeRate);
    }

    public String getSortedDataOnWhoTookMaximumWickets() {
        Comparator<IplSheetDAO> compareByMaximumWickets = Comparator.comparing(cricket -> cricket.wickets);
        Comparator<IplSheetDAO> compareByBestBowlingAverage = compareByMaximumWickets
                                                          .thenComparing(cricket -> cricket.bowlingAverage);
        return sort(compareByBestBowlingAverage);
    }

    public String getSortedDataOnWhoBestBowlingAndBattingAverage() {
        Comparator<IplSheetDAO> compareByBattingAverage = Comparator.comparing(cricket -> cricket.battingAverage);
        Comparator<IplSheetDAO> compareByBowlingAverage = compareByBattingAverage
                                                            .thenComparing(cricket -> cricket.bowlingAverage);
        return sort(compareByBowlingAverage);
    }

    public String getSortedDataOnMostRunsAndMostWickets() {
        Comparator<IplSheetDAO> compareByMostRuns = Comparator.comparing(cricket -> cricket.battingRuns);
        Comparator<IplSheetDAO> compareByMostWickets = compareByMostRuns
                                                         .thenComparing(cricket -> cricket.wickets);
        return sort(compareByMostWickets);
    }

    private String sort(Comparator<IplSheetDAO> censusComparator) {
        List sortedData = iplRunSheetMap.values().stream().
                sorted(censusComparator).collect(Collectors.toList());
        return new Gson().toJson(sortedData);
    }

}
